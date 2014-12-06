package com.lab.ly.model;

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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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

    private final Set<Class<?>> clazzes;

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    protected SerializationTestCase(Class<?>... clazzes) {
        this.clazzes = new HashSet<>(Arrays.asList(clazzes));
        try {
            final JAXBContext context = JAXBContext.newInstance(clazzes);
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
