package com.lab.ly.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 6:51 PM
 */
@XmlRootElement(name = "headers")
public class HeaderSet {

    @XmlElement(name = "h")
    private Set<String> headers;

    public HeaderSet() {
        headers = new HashSet<>();
    }

    public Set<String> getHeaders() {
        return headers;
    }

    public void setHeaders(Set<String> headers) {
        this.headers = headers;
    }

    public void add(String name) {
        headers.add(name);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeaderSet)) return false;

        HeaderSet set = (HeaderSet) o;

        if (headers != null ? !headers.equals(set.headers) : set.headers != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return headers != null ? headers.hashCode() : 0;
    }
}
