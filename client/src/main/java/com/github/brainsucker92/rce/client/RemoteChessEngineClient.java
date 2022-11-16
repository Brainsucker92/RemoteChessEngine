package com.github.brainsucker92.rce.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.function.BooleanSupplier;

import core.CharacterStreamConnector;
import picocli.CommandLine;

public class RemoteChessEngineClient extends Thread {

    @CommandLine.Option(names = {"-p", "--port"}, description = "Server Port", arity = "1", defaultValue = "42042")
    int port;
    @CommandLine.Option(names = {"-a", "--address"}, description = "Server hostname or IP address", arity = "1", defaultValue = "localhost")
    String address;

    public static void main(String... args) {
        int exitCode = new CommandLine(new RemoteChessEngineClient()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        SocketAddress socketAddress = new InetSocketAddress(address, port);
        Socket socket = new Socket();
        try {
            socket.connect(socketAddress);
            InputStream clientInputStream = socket.getInputStream();
            OutputStream clientOutputStream = socket.getOutputStream();
            BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(clientInputStream));
            BufferedReader systemBufferedReader = new BufferedReader(new InputStreamReader(System.in));
            PrintStream clientPrintStream = new PrintStream(clientOutputStream, true);

            BooleanSupplier outputSupplier = () -> !socket.isOutputShutdown();
            Thread t1 = new CharacterStreamConnector(outputSupplier, clientPrintStream, systemBufferedReader);

            BooleanSupplier inputSupplier = () -> !socket.isInputShutdown();
            Thread t2 = new CharacterStreamConnector(inputSupplier, System.out, clientBufferedReader);

            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
