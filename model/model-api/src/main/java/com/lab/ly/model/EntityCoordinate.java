package com.lab.ly.model;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by haswell on 1/17/15.
 */
public class EntityCoordinate<
        ID extends Serializable,
        Name extends Serializable
        > {

    @XmlAttribute
    private ID id;

    @XmlElement
    private Name name;

    public EntityCoordinate() {

    }


    public EntityCoordinate(ID id, Name key) {
        this.id = id;
        this.name = key;
    }


    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityCoordinate that = (EntityCoordinate) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EntityCoordinate{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
