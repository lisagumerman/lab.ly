package com.lab.ly.model.io;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;

/**
 * User: haswell
 * Date: 11/23/14
 * Time: 4:48 PM
 */
public class UploadedFile {
    private String name;

    public UploadedFile() {
    }

    private byte[] data;

    public byte[] getData() {
        return data;
    }

    @FormParam("file")
    @PartType("application/octet-stream")
    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
