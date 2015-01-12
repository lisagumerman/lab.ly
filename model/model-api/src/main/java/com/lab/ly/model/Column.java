package com.lab.ly.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haswell on 1/12/15.
 * TODO: create type-aware java type adapater
 */
@XmlRootElement
public class Column<T extends Serializable> {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private Integer index;


    @XmlElement(name = "e")
    private List<T> elements;

    @XmlAttribute
    private Class<T> type;

    public Column(
            final Integer index,
            final String name,
            final List<T> contents,
            final Class<T> type) {
        this.index = index;
        this.name = name;
        this.elements = contents;
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public Column(
            final Integer index,
            final String name,
            final List<T> contents) {
        this.index = index;
        this.name = name;
        this.elements = contents;
        this.type = (Class<T>) contents.get(0).getClass();
    }

    public Column() {

    }

    public T get(int index) {
        return elements.get(index);
    }


    public Column(Integer index,
                  String name,
                  T...elements) {
        this(index, name, new ArrayList<>(Arrays.asList(elements)));
    }

    public String getName() {
        return name;
    }

    public String getHeading() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Column column = (Column) o;

        return !(elements != null ?  !elements.equals(column.elements)
                : column.elements != null) &&
                !(index != null ? !index.equals(column.index) :
                        column.index != null) &&
                !(name != null ? !name.equals(column.name) :
                        column.name != null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (elements != null ? elements.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", elements=" + elements +
                '}';
    }
}
