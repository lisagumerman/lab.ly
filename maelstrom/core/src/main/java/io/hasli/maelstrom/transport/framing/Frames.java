package io.hasli.maelstrom.transport.framing;

import io.hasli.maelstrom.FileMode;
import io.hasli.maelstrom.InvalidFrameException;
import io.hasli.maelstrom.transport.Encoding;

/**
 * Created by haswell on 2/21/16.
 */
public class Frames {


    public static final byte OFFSET_MASK    = 0b1111;
    public static final byte TYPE_MASK      = 0x40;
    public static final byte ENCODING_MASK  = 0x7f;



    public static Frame parse(byte[][] payload) {
        if(payload.length == 0) {
            throw new InvalidFrameException();
        }
        final byte[] header = payload[0];
        final Frame.Type type = parseType(header);
        switch(type) {
            case Stream:
                return new DefaultStreamFrame(
                        isOffsetPresent(header),
                        parseVersion(header),
                        parseFileMode(header),
                        isOffsetPresent(header) ?
                                parseOffset(payload) : -1,
                        parseEncoding(header)
                );
        }
        return null;
    }

    private static int parseOffset(byte[][] payload) {


    }


    public static Encoding parseEncoding(final byte[] data) {
        if(data.length < 0) {
            throw new InvalidFrameException(); } final byte fst = data[0];
        final int value = (fst >>> 1) & ENCODING_MASK;
        return Encoding.get(value);
    }


    public static Frame.Type parseType(byte[] data) {
        if(data.length < 0) {
            throw new InvalidFrameException();
        }

        final byte fst = data[0];
        final int value = ((fst << 6) & TYPE_MASK) >>> 6;
        switch(value) {
            case 0: return Frame.Type.Stream;
            case 1: return Frame.Type.Invocation;
        }
        throw new InvalidFrameException(
                "Unknown type with code-point: " +
                        value + " (should not have gotten here)");
    }


    static int parseVersion(byte[] data) {
        if(data.length < 3) {
            throw new InvalidFrameException();
        }

        final byte versionFst = data[1];
        final byte versionSnd = data[2];
        return (versionFst & ENCODING_MASK) +
                (versionSnd & ENCODING_MASK);
    }

    public static FileMode parseFileMode(byte[] frame) {
        if(frame.length < 4) {
            throw new InvalidFrameException();
        }
        final byte value = frame[3];
        return FileMode.getValue((value & OFFSET_MASK));
    }


    public static boolean isOffsetPresent(byte[] frame) {
        if(frame.length < 4) {
            throw new InvalidFrameException();
        }
        final byte value = frame[3];
        return value >>> 6 == 1;
    }
}
