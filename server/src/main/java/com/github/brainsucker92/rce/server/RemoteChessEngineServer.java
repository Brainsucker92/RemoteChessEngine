package com.github.brainsucker92.rce.server;

import core.factory.ChessEngineFactory;
import core.factory.impl.SimpleChessEngineFactory;
import core.engine.ChessEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RemoteChessEngineServer implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();
    @CommandLine.Option(names = {"-c", "--command"}, arity = "1", required = true)
    String engineCommand;
    @CommandLine.Option(names = {"-p", "--port"}, arity = "1", defaultValue = "42042")
    int port;
    private final HashMap<Socket, SocketHandler> socketMap = new HashMap<>();
    private final Lock engineFactoryLock = new ReentrantLock();
    private ChessEngineFactory chessEngineFactory;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new RemoteChessEngineServer()).execute(args);
        System.exit(exitCode);
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        engineFactoryLock.lock();
        try {
            if (chessEngineFactory == null) {
                chessEngineFactory = new SimpleChessEngineFactory(engineCommand);
            }
        } finally {
            engineFactoryLock.unlock();
        }

        try {
            serverSocket = new ServerSocket(port);
            LOGGER.info(String.format("Server has been started and accepting connections on port %d.", port));
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ChessEngine chessEngine = chessEngineFactory.createEngine();
                SocketHandler handler = new SocketHandler(clientSocket, chessEngine);
                handler.setDaemon(true);
                handler.start();
                socketMap.put(clientSocket, handler);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.cleanup();
            LOGGER.info(String.format("Server on port %d has been stopped.", port));
        }
    }

    public void setChessEngineFactory(ChessEngineFactory chessEngineFactory) {
        engineFactoryLock.lock();
        try {
            this.chessEngineFactory = chessEngineFactory;
        } finally {
            engineFactoryLock.unlock();
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
