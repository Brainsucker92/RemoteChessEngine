package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.function.BooleanSupplier;

import core.CharacterStreamConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SocketHandler extends Thread {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Socket clientSocket;
    private final Process process;

    public SocketHandler(final Socket clientSocket, final Process process) {
        super();
        this.clientSocket = clientSocket;
        this.process = process;
    }

    @Override
    public void run() {
        InputStream engineProcessInputStream = process.getInputStream();
        OutputStream engineProcessOutputStream = process.getOutputStream();
        BufferedReader engineBufferedReader = new BufferedReader(new InputStreamReader(engineProcessInputStream));
        PrintStream enginePrintStream = new PrintStream(engineProcessOutputStream, true);
        try {
            InputStream clientSocketInputStream = clientSocket.getInputStream();
            OutputStream clientSocketOutputStream = clientSocket.getOutputStream();
            BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(clientSocketInputStream));
            PrintStream clientPrintStream = new PrintStream(clientSocketOutputStream, true);
            BooleanSupplier booleanSupplier = () -> !clientSocket.isInputShutdown() && process.isAlive();
            Thread t1 = new CharacterStreamConnector(booleanSupplier, enginePrintStream, clientBufferedReader);
            // t1.setName("Client Reader Thread");
            t1.setDaemon(true);

            BooleanSupplier outputSupplier = () -> !clientSocket.isOutputShutdown() && process.isAlive();
            Thread t2 = new CharacterStreamConnector(outputSupplier, clientPrintStream, engineBufferedReader);
            // t2.setName("Process Reader Thread");
            t2.setDaemon(true);
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            String clientIP = clientSocket.getInetAddress().getHostAddress();
            int port = clientSocket.getPort();
            LOGGER.info(String.format("Terminating connection to client %s:%d normally.", clientIP, port));

            clientBufferedReader.close();
            engineBufferedReader.close();
            clientPrintStream.close();
            enginePrintStream.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.cleanup();
            String clientIp = this.clientSocket.getInetAddress().getHostAddress();
            int port = this.clientSocket.getPort();
            LOGGER.info(String.format("Connection to client %s:%d has been terminated abnormally", clientIp, port));
        }
    }

    private void cleanup() {
        if (process.isAlive()) {
            process.destroy();
        }
        if (!clientSocket.isClosed()) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}