package com.lab.ly.model;

import org.eclipse.persistence.jaxb.JAXBContextProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 6:35 PM
 */


public class SerializationTestCase {


    static final Logger logger = Logger.getLogger(
            SerializationTestCase.class.getName());

    private SerializationStrategy serializationStrategy;
    private final Set<Class<?>> classes;

    public SerializationTestCase( Format format, Class<?>...classes) {

        this.classes = new HashSet<>(Arrays.asList(classes));
        this.serializationStrategy = new SerializationStrategy(format, classes);
    }

    public void setFormat(Format format) {
        this.serializationStrategy =
                new SerializationStrategy(format, classes.toArray(new Class<?>[]{}));
    }

    public <T> String marshal(T t) {
        return serializationStrategy.serialize(t);
    }

    public <T> T unmarshal(String i) {
        return serializationStrategy.deserialize(i);
    }

    public <T> T copy(T i) {
        return serializationStrategy.copy(i);
    }

    public <T> String marshal(T t, Format format) {
        setFormat(format);
        return serializationStrategy.serialize(t);
    }

    public <T> T unmarshal(String i, Format format) {
        setFormat(format);
        return serializationStrategy.deserialize(i);
    }

    public <T> T copy(T i, Format format) {
        setFormat(format);
        return serializationStrategy.copy(i);
    }




    public enum Format {
        Xml("application/xml"),
        Json("application/json");
        final String mediatype;
        private Format(String mediatype) {
            this.mediatype = mediatype;
        }

        public String getMediatype() {
            return mediatype;
        }
    }

    public static final class SerializationStrategy {
        private final Format format;

        private final Set<Class<?>> clazzes;

        private final Marshaller marshaller;
        private final Unmarshaller unmarshaller;
        SerializationStrategy(final Format format, Class<?> ...classes) {
            this.format = format;
            this.clazzes = new HashSet<>(Arrays.asList(classes));

            try {
                final Map<String, Object> properties = new HashMap<>();
                properties.put(JAXBContextProperties.MEDIA_TYPE, format.getMediatype());
                final JAXBContext context = JAXBContext.newInstance(this.clazzes.
                        toArray(new Class[]{}), properties);
                this.marshaller = context.createMarshaller();
                this.unmarshaller = context.createUnmarshaller();
                logger.log(Level.INFO, "Using context " + context);
            } catch(JAXBException ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }



        protected <T> String serialize(T input) {
            final Writer writer = new StringWriter();
            try {
                marshaller.marshal(input, writer);
                return writer.toString();
            } catch(JAXBException ex) {
                throw new RuntimeException(ex);
            }
        }


        @SuppressWarnings("unchecked")
        protected <T> T deserialize(String input) {
            try {
                return (T) unmarshaller.unmarshal(new StringReader(input));
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }

        }

        protected <T> T copy(T input) {
            return deserialize(serialize(input));
        }

    }

}
