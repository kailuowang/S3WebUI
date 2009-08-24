package com.thoughtDocs.model;

import java.io.IOException;
import java.util.Date;


public interface Document extends Item {

    /**
     * @return the name of the document that is unique under one repository
     */
    String getKey();


    String getSignedURL();

    byte[] getData() throws IOException;

    void setData(byte[] data);

    String getContentType();

    void setContentType(String contentType);

    String getPublicUrl();

    Date getLastModified();

    long getSize();

}
