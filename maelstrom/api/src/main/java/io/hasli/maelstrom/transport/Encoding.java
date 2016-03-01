package io.hasli.maelstrom.transport;

import io.hasli.maelstrom.UnknownEncodingException;

/**
 * Created by haswell on 2/20/16.
 */
public enum Encoding {

    RAW(0),

    UTF8(1),

    UTF16(2);



    private final int value;

    Encoding(final int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }


    public static Encoding get(int value) {
        switch(value) {
            case 0: return RAW;
            case 1: return UTF8;
            case 2: return UTF16;
        }


        throw new UnknownEncodingException("Unknown encoding code-point: " + value);
    }


}
