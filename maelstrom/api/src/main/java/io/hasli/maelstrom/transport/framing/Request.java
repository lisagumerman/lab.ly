package io.hasli.maelstrom.transport.framing;

/**
 * Created by haswell on 2/20/16.
 *
 *
 * A request frame is as follows:
 *
 *
 *
 * 0: encoding and type are packed into 1st byte
 *    (encoding is first 7 bits, type (stream|invocation) is 8th bit)
 * 1: client version (2 bytes)
 * total = 3 bytes
 *
 * if type is Stream:
 * 2: Operation type (read|write) and isOffset? are packed into byte[4]
 * 3: request segment size (4 bytes)
 * 4: if a stream offset is included (3), then the next 8 bytes are the offset.
 *
 * If the type is Stream, we will expect another message part that is the logical file path
 *
 */
public final class Request {




}
