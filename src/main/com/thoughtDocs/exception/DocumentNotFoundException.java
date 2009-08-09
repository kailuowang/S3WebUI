package com.thoughtDocs.exception;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 2, 2009
 * Time: 10:09:21 PM
 */
public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(String document) {
        super("Cannot find document - " + document );
    }
}
