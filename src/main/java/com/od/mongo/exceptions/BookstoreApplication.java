package com.od.mongo.exceptions;

public class BookstoreApplication extends RuntimeException {
    public BookstoreApplication() {
    }

    public BookstoreApplication(String message) {
        super(message);
    }

    public BookstoreApplication(String message, Throwable cause) {
        super(message, cause);
    }

}
