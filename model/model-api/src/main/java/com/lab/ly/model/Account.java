package com.lab.ly.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by haswell on 1/5/15.
 */
@Entity
@XmlRootElement
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @XmlElement
    @Column(name = "name")
    private String name;

    public Account(String name) {
        this.name = name;
    }

    public Account() {
        this(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (name != null ? !name.equals(account.name) : account.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
