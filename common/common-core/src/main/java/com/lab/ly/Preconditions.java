package com.lab.ly;

import com.lab.ly.exceptions.SystemException;

/**
 * Created by haswell on 1/13/15.
 */
public class Preconditions {

    private Preconditions() {
        throw new UnsupportedClassVersionError("No preconditions for you!");
    }

    public static <T> void requireNonNull(T item, String message) throws SystemException {
        if(item == null) {
            throw new SystemException(message);
        }
    }

    public static <T> void requireNonNull(T item, String messageTemplate, Object...args) {
        requireNonNull(item, String.format(messageTemplate, args));
    }
}
