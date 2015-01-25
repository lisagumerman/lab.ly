package com.lab.ly.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by haswell on 1/17/15.
 */
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable,
        Key extends Serializable> implements Entity<ID, Key> {


    @Id
    @XmlAttribute
    private ID id;

    @Basic
    @XmlElement
    @Column(name = "entity_key")
    private Key key;

    protected AbstractEntity(ID id, Key key) {
        this.id = id;
        this.key = key;
    }

    protected AbstractEntity() {

    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public Key getKey() {
        return key;
    }

    @Override
    public boolean isNew() {
        return getId() == null;
    }


    @Override
    public EntityCoordinate<ID, Key> getCoordinate() {
        return new EntityCoordinate<>(id, key);
    }
}
