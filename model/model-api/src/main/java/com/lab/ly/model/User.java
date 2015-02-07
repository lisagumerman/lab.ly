package com.lab.ly.model;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;

/**
 * Created by haswell on 1/10/15.
 */
@XmlRootElement
@Table(name = "lably_user")
@javax.persistence.Entity
public class User implements Entity<Long, String> {

    @Id
    @XmlID
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "email_address")
    private String emailAddress;

    @ManyToMany(mappedBy = "users")
    @XmlInverseReference(mappedBy = "users")
    private Set<Account> accounts = new LinkedHashSet<>();



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getKey() {
        return emailAddress;
    }

    @Override
    public boolean isNew() {
        return getId() != null;
    }

    @Override
    public EntityCoordinate<Long, String> getCoordinate() {
        return new EntityCoordinate<>(id, getKey());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public boolean addAccount(Account account) {
        if(account != null) {
            return accounts.add(account);
        }
        return false;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (emailAddress != null ? !emailAddress.equals(user.emailAddress) : user.emailAddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return emailAddress != null ? emailAddress.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
