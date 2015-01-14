package com.lab.ly.model;

import com.lab.ly.exceptions.SystemException;

/**
 * Created by haswell on 1/13/15.
 */
public class InvalidDataSetException extends SystemException {

    public InvalidDataSetException() {
    }

    public InvalidDataSetException(String message) {
        super(message);
    }

    public InvalidDataSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataSetException(Throwable cause) {
        super(cause);
    }

    public InvalidDataSetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
