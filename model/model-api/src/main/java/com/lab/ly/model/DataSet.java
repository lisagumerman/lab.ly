package com.lab.ly.model;

import com.lab.ly.io.parsers.DelimitedFileParser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
    private String name;

    @XmlElement
    private Map<String, Column<?>> columns;

    @XmlAttribute
    private Boolean jagged = false;

    @XmlElement(name = "header")
    @XmlElementWrapper(name = "headers")
    private Collection<String> headers;

    public DataSet() {
        this.id = UUID.randomUUID();
        this.columns = new LinkedHashMap<>();
        this.headers = columns.keySet();
    }

    public Integer getColumnCount() {
        return headers.size();
    }

    public Integer getRowCount() {
        return columns.entrySet().iterator().next().getValue().size();
    }


    public DataSet setHeaders(Collection<String> headers) {
        for (String header : headers) {
            columns.put(header, new Column<>(0, header));
        }
        return this;
    }

    public Collection<String> getHeaders() {
        return headers;
    }

    public <T extends Serializable>
    Column<T> getColumn(String name) {
        return findColumn(name);
    }

    @SuppressWarnings("unchecked")
    private <T extends Serializable> Column<T> findColumn(String name) {
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
        return name;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    @Override
    public EntityCoordinate<UUID, String> getCoordinate() {
        return new EntityCoordinate<>(id, getKey());
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

    public <T extends Serializable> DataSet addRow(Collection<T> elements) {
        if(elements.size() != headers.size()) {
            setJagged(true);
        }
        extractColumn(elements);
        return this;
    }

    private <T extends Serializable> void extractColumn(Collection<T> elements) {
        final Iterator<T> theirs = elements.iterator();
        for(String header : getHeaders()) {
            Column<T> column = findColumn(header);
            column.add(theirs.next());
        }
    }

    public void setJagged(Boolean jagged) {
        this.jagged = jagged;
    }

    public <T extends Serializable> DataSet
        setColumn(String name, T... elements) {
        return setColumn(name, Arrays.asList(elements));
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "id=" + id +
                ", columns=" + columns +
                ", jagged=" + jagged +
                ", headers=" + headers +
                '}';
    }
}
