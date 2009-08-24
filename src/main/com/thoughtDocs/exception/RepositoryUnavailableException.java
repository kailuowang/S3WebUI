package com.thoughtDocs.exception;

/**
 *
 */
public class RepositoryUnavailableException extends RuntimeException{
    public RepositoryUnavailableException() {
    }

    public RepositoryUnavailableException(String s) {
        super(s);
    }

    public RepositoryUnavailableException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RepositoryUnavailableException(Throwable throwable) {
        super(throwable);
    }
}
