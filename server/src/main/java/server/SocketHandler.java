package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import core.engine.ChessEngine;
import core.engine.OutputListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SocketHandler extends Thread {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Socket clientSocket;
    private final ChessEngine chessEngine;

    public SocketHandler(final Socket clientSocket, final ChessEngine engine) {
        super();
        this.clientSocket = clientSocket;
        this.chessEngine = engine;
    }

    @Override
    public void run() {
        try {
            InputStream clientSocketInputStream = clientSocket.getInputStream();
            OutputStream clientSocketOutputStream = clientSocket.getOutputStream();
            BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(clientSocketInputStream));
            PrintStream clientPrintStream = new PrintStream(clientSocketOutputStream, true);
            Thread t1 = new Thread(() -> {
                while (!clientSocket.isInputShutdown()) {
                    try {
                        String line = clientBufferedReader.readLine();
                        chessEngine.sendCommand(line);
                        if (line == null) {
                            break;
                        }
                    } catch (IOException e) {
                        break;
                    }
                }
            });
            t1.setDaemon(true);

            t1.start();
            t1.join();

            OutputListener engineOutputListener = clientPrintStream::println;
            chessEngine.addOutputListener(engineOutputListener);

            String clientIP = clientSocket.getInetAddress().getHostAddress();
            int port = clientSocket.getPort();
            LOGGER.info(String.format("Terminating connection to client %s:%d normally.", clientIP, port));

            clientBufferedReader.close();
            clientPrintStream.close();
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
        if (!clientSocket.isClosed()) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
