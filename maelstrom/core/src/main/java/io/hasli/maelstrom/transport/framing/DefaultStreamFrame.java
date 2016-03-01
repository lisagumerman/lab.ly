package io.hasli.maelstrom.transport.framing;

import io.hasli.maelstrom.FileMode;
import io.hasli.maelstrom.transport.Encoding;

/**
 * Created by haswell on 2/21/16.
 */
public final class DefaultStreamFrame implements StreamFrame {

    private byte[] payload;

    private boolean offset;

    private final int version;

    private final FileMode mode;


    private final int offsetLength;

    private final Encoding encoding;

    public DefaultStreamFrame(
            boolean offset,
            int version,
            FileMode mode,
            int offsetLength,
            Encoding encoding
    ) {
        this.mode = mode;
        this.offset = offset;
        this.version = version;
        this.encoding = encoding;
        this.offsetLength = offsetLength;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public boolean isOffset() {
        return offset;
    }

    public void setOffset(boolean offset) {
        this.offset = offset;
    }

    public int getVersion() {
        return version;
    }

    public FileMode getMode() {
        return mode;
    }

    public int getOffsetLength() {
        return offsetLength;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    @Override
    public Type getType() {
        return Type.Stream;
    }
}
