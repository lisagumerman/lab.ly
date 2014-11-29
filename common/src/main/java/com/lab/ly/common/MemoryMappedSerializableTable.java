package com.lab.ly.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 4:23 PM
 */
@XmlRootElement
public class MemoryMappedSerializableTable implements Table {

    @XmlElement
    private Map<String, Collection<?>> columns;

    @Override
    public Table addColumn(Class<?> type, String name) {
        if(columns.containsKey(name)) {
            throw new DuplicateColumnException("Error.  There is already a column named '" + name + "' registed with this table!");
        }
        columns.put(name, new ArrayList<Object>());
        return this;
    }

    @Override
    public Map<String, Collection<?>> getRawValues() {
        return columns;
    }

    @Override
    public <T> Collection<?> getColumn(Class<T> type, String name) {
        return getColumn(name);
    }

    @Override
    public <T> boolean hasColumn(Class<T> type, String name) {
        return columns.containsKey(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Collection<T> getColumn(String name) {
        return (Collection<T>) columns.get(name);
    }

    @Override
    public <T> boolean hasColumn(String name) {
        return hasColumn(Object.class, name);
    }
}
