package com.lab.ly.model;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;

/**
 * Created by haswell on 1/5/15.
 */
@XmlRootElement
@Table(name = "lably_account")
@javax.persistence.Entity
public class Account implements Entity<Long, String> {

    @Id
    @XmlID
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic
    @XmlElement
    @Column(name = "name")
    private String name;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
        name = "account_users",
        joinColumns = @JoinColumn(
                name = "account_id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "user_id"
        )
    )
    @XmlElement(name = "user")
    @XmlElementWrapper(name = "users")
    @XmlInverseReference(mappedBy = "account")
    private Set<User> users = new LinkedHashSet<>();


    @OneToMany
    @XmlElement
    @JoinColumn(name = "account_id")
    @XmlElementWrapper(name = "datasets")
    private Set<DataSetDescriptor> datasets;



    public Account(String name) {
        this.name = name;
    }

    public Account() {
        this(null);
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public boolean isNew() {
        return getId() == null;
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

    public Set<User> getUsers() {
        return users;
    }

    public boolean addUser(User user) {
        if(user != null) {
            if(user.addAccount(this)) {
                return users.add(user);
            }
        }
        return false;
    }

    public boolean addDataSet(DataSetDescriptor dataSetDescriptor) {
        if(dataSetDescriptor != null) {
            dataSetDescriptor.setOwner(this);
            return datasets.add(dataSetDescriptor);
        }
        return false;
    }

    public void setUsers(Collection<User> users) {
        if(!(users == null || users.isEmpty())) {
            users.forEach(u -> addUser(u));
        }
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
    public EntityCoordinate<Long, String> getCoordinate() {
        return new EntityCoordinate<>(id, name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
