package com.thoughtDocs.exception;

/**
 *
 */
public class InvalidBucketException extends RuntimeException{
    public InvalidBucketException() {
    }

    public InvalidBucketException(String s) {
        super(s);
    }

    public InvalidBucketException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidBucketException(Throwable throwable) {
        super(throwable);
    }
}
