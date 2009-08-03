package com.thoughtDocs.exception;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 2, 2009
 * Time: 12:37:53 PM
 */
public class InvalidConfigurationException  extends UnsupportedOperationException{

    public InvalidConfigurationException() {
    }

    public InvalidConfigurationException(String msg) {
        super(msg);
    }
}
