package io.hasli.maelstrom.zmq;

import org.zeromq.ZFrame;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

/**
 * Created by haswell on 2/20/16.
 */
public class MessageTestHarness {

    private Thread clientThread;
    private Thread serverThread;
    private volatile boolean running;
    private ZMQ.Socket requestor;
    private ZMQ.Socket responder;

    private final int port;
    private Server server;
    private Client client;
    private final ZMQ.Context context;
    private volatile boolean sending;


    protected MessageTestHarness(final int port) {
        this(port, 1);
    }

    protected MessageTestHarness(final int port, final int iothreads) {
        this.port = port;
        this.context = ZMQ.context(iothreads);
    }


    public void setUp() {
        System.out.println("Starting...");
        clientThread = new Thread(client = new Client(context, port));
        serverThread = new Thread(server = new Server(context, port));
        start();
        System.out.println("Started...");
    }


    public void tearDown() {
        System.out.println("Stopping...");
        try {
            client.close();
            server.close();
            context.term();
            System.out.println("Stopped...");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }


    protected void start() {
        if (!running) {
            serverThread.start();
            clientThread.start();
            running = true;
        }
    }


    protected void stop() {
        running = false;
        context.term();
    }


    protected byte[][] send(byte[]... data) {
        return sendClientToServer(data);
    }

    private byte[][] sendClientToServer(byte[][] data) {
        final ZMsg message = new ZMsg();
        for (int i = 0; i < data.length; ++i) {
            message.add(data[i]);
        }
        message.send(client.socket);
        final ZMsg frames = ZMsg.recvMsg(server.socket);
        final byte[][] results = new byte[data.length][];
        int count = 0;
        for (ZFrame frame : frames) {
            results[count++] = frame.getData();
        }
        return results;
    }


    protected ZMQ.Socket create(int type) {
        if (context == null) {
            throw new IllegalStateException("Harness not started!");
        }
        return context.socket(type);
    }

    protected static class Server implements Runnable, AutoCloseable {
        final int port;
        final ZMQ.Socket socket;
        final ZMQ.Context context;

        public Server(ZMQ.Context context, int port) {
            this.port = port;
            this.context = context;
            this.socket = context.socket(ZMQ.REP);
        }

        @Override
        public void run() {
            socket.bind(String.format("tcp://*:%s", port));
        }

        public byte[] receive(int flags) {
            return socket.recv(flags);
        }

        public void send(byte[] data, int flags) {
            socket.send(data, flags);
        }


        @Override
        public void close() throws Exception {
            this.socket.close();
        }
    }


    protected static class Client implements Runnable, AutoCloseable {
        final int port;
        final ZMQ.Socket socket;
        final ZMQ.Context context;

        public Client(ZMQ.Context context, int port) {
            this.port = port;
            this.context = context;
            this.socket = context.socket(ZMQ.REQ);
        }

        public byte[] receive(int flags) {
            return socket.recv(flags);
        }

        public void send(byte[] data, int flags) {
            socket.send(data, flags);
        }

        @Override
        public void run() {
            socket.connect(String.format("tcp://localhost:%s", port));
        }

        @Override
        public void close() throws Exception {
            socket.close();
        }
    }
}
