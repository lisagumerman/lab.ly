package com.lab.ly.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

import static java.lang.String.format;

/**
 * Created by haswell on 1/12/15.
 */
@XmlRootElement
public class DataSet implements Entity<UUID, String> {

    @XmlAttribute
    private UUID id;

    @XmlElement
    private Map<String, Column<?>> columns;

    @XmlElement
    private Collection<String> headers;

    public DataSet() {
        this.id = UUID.randomUUID();
        this.columns = new LinkedHashMap<>();
        this.headers = columns.keySet();
    }

    public Collection<String> getHeaders() {
        return headers;
    }

    @SuppressWarnings("unchecked")
    public <T extends Serializable>
    Column<T> getColumn(String name) {
        final Column<?> column = columns.get(name);
        if(column == null) {
            throw new NoSuchElementException(format("Error: Column named '%s' " +
                    "does not exist.  Existing names: %s", name, columns.keySet()));
        }
        return (Column<T>) column;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getKey() {
        return id.toString();
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public <T extends Serializable> DataSet setColumn(
            String name,
            Collection<T> elements) {
        final Column<T> column = new Column<>(0, name,
                new ArrayList<>(elements));
        columns.put(name, column);
        return this;
    }

    public <T extends Serializable> DataSet setColumn(Column<T> column) {
        columns.put(column.getHeading(), column);
        return this;
    }

    public <T extends Serializable> DataSet
        setColumn(String name, T... elements) {
        return setColumn(name, Arrays.asList(elements));
    }
}
