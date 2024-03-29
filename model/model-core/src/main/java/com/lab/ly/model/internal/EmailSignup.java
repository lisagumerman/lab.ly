package com.lab.ly.model.internal;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@javax.persistence.Table(
        name = "email_signup"
)
@XmlRootElement
public class EmailSignup {

    @Id
    @XmlAttribute
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @XmlElement
    @Column(name = "email_address")
    private String emailAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        EmailSignup that = (EmailSignup) o;

        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return emailAddress != null ? emailAddress.hashCode() : 0;
    }
}
