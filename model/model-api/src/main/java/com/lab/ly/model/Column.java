package com.lab.ly.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by haswell on 1/12/15.
 * TODO: create type-aware java type adapater
 */
@XmlRootElement
public class Column<T extends Serializable> implements Iterable<T> {

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

    public Column<T> setData(Collection<T> elements) {
        this.elements.clear();
        this.elements.addAll(elements);
        return this;
    }

    @SuppressWarnings("unchecked")
    public Column(
            final Integer index,
            final String name,
            final List<T> contents) {
        this.index = index;
        this.name = name;
        this.elements = contents;
        if(!(contents == null || contents.isEmpty())) {
            this.type = (Class<T>) contents.get(0).getClass();
        } else {
            this.type = null;
        }
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

    public Integer size() {
        return elements.size();
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

    public Column<T> add(T element) {
        this.elements.add(element);
        return this;
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

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
}
