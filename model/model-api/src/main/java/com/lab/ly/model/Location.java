package com.lab.ly.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by haswell on 1/17/15.
 */
@XmlRootElement
public class Location {

    @XmlElement
    private String location;

    @XmlAttribute
    private Boolean absolute;

    public Boolean getAbsolute() {
        return absolute;
    }

    public void setAbsolute(Boolean absolute) {
        this.absolute = absolute;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location1 = (Location) o;

        if (location != null ? !
                location.equals(location1.location) :
                location1.location != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return location != null ? location.hashCode() : 0;
    }
}
