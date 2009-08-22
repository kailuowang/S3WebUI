package com.thoughtDocs.exception;

/**
 *
 */
public class FailedToLoadUsersException extends RuntimeException{
    public FailedToLoadUsersException() {
    }

    public FailedToLoadUsersException(String s) {
        super(s);
    }

    public FailedToLoadUsersException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FailedToLoadUsersException(Throwable throwable) {
        super(throwable);
    }
}
