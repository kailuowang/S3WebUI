package com.thoughtDocs.exception;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 28, 2009
 * Time: 8:09:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotImplementedException extends UnsupportedOperationException{
    public NotImplementedException() {
        super("Not implemented");
    }
}
