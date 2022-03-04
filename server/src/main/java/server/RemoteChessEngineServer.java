package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;
import server.factory.EngineConfig;
import server.factory.ProcessFactory;
import server.factory.impl.SimpleEngineConfig;
import server.factory.impl.SimpleProcessFactory;

public class RemoteChessEngineServer implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();
    @CommandLine.Option(names = {"-c", "--command"}, arity = "1", required = true)
    String engineCommand;
    @CommandLine.Option(names = {"-p", "--port"}, arity = "1", defaultValue = "42042")
    int port;
    private final HashMap<Socket, SocketHandler> socketMap = new HashMap<>();
    private final Lock processFactoryLock = new ReentrantLock();
    private ProcessFactory processFactory;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new RemoteChessEngineServer()).execute(args);
        System.exit(exitCode);
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        processFactoryLock.lock();
        try {
            if (processFactory == null) {
                EngineConfig config = new SimpleEngineConfig(engineCommand);
                processFactory = new SimpleProcessFactory(config);
            }
        } finally {
            processFactoryLock.unlock();
        }

        try {
            serverSocket = new ServerSocket(port);
            LOGGER.info(String.format("Server has been started and accepting connections on port %d.", port));
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Process engineProcess;
                processFactoryLock.lock();
                try {
                    engineProcess = processFactory.createProcess();
                } finally {
                    processFactoryLock.unlock();
                }
                SocketHandler handler = new SocketHandler(clientSocket, engineProcess);
                handler.setDaemon(true);
                handler.start();
                socketMap.put(clientSocket, handler);
            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
            this.cleanup();
            LOGGER.info(String.format("Server on port %d has been stopped.", port));
        }
    }

    public void setProcessFactory(ProcessFactory processFactory) {
        processFactoryLock.lock();
        try {
            this.processFactory = processFactory;
        } finally {
            processFactoryLock.unlock();
        }
    }

    private void cleanup() {
        if (this.serverSocket != null) {
            if (!this.serverSocket.isClosed()) {
                try {
                    this.serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.socketMap.values().forEach(Thread::interrupt);
    }
}
