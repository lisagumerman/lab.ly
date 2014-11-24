package com.lab.ly;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import java.util.Arrays;

/**
 * User: haswell
 * Date: 11/23/14
 * Time: 4:48 PM
 */
public class UploadedFile {
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
}
