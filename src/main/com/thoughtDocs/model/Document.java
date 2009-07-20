package com.thoughtDocs.model;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 9, 2009
 * Time: 10:50:33 PM
 */
public interface Document {
    public Repository getRepository();

    /**
     *
     * @return the name of the document that is unique under one repository
     */
    public String getName();
}
