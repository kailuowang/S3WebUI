package com.thoughtDocs.model;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 9, 2009
 * Time: 10:50:33 PM
 */
public interface Document {
    Repository getRepository();

    /**
     * @return the name of the document that is unique under one repository
     */
    String getName();

    void delete() throws IOException;

    String getSignedURL();

    byte[] getData();

    void setData(byte[] data);

    String getContentType();

    void setContentType(String contentType);

    String getPassword() throws IOException;

    void setPassword(String password) throws IOException;

    void upload(Repository repo) throws IOException;
}
