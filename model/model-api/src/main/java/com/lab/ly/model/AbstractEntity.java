package com.lab.ly.model;

import java.io.Serializable;

/**
 * Created by haswell on 1/17/15.
 */
public abstract class AbstractEntity<ID extends Serializable,
        Key extends Serializable> implements Entity<ID, Key> {

    private ID id;
    private Key key;
    protected AbstractEntity(ID id, Key key) {
        this.id = id;
        this.key = key;
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
