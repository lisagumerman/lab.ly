package io.hasli.maelstrom;

/**
 * Created by haswell on 2/20/16.
 */
public class InvalidFrameException extends MaelstromException {
    public InvalidFrameException(String s) {
        super(s);
    }

    public InvalidFrameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFrameException(Throwable cause) {
        super(cause);
    }

    public InvalidFrameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidFrameException() {
    }
}
