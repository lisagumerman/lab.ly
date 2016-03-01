package io.hasli.maelstrom;

/**
 * Created by haswell on 2/20/16.
 */
public class MaelstromException extends RuntimeException {

    public MaelstromException() {

    }

    public MaelstromException(String message) {
        super(message);
    }

    public MaelstromException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaelstromException(Throwable cause) {
        super(cause);
    }

    public MaelstromException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
