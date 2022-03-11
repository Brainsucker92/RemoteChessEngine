package core.engine.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import core.engine.ChessEngine;
import core.engine.OutputListener;

public class BasicChessEngine implements ChessEngine {

    private final Process chessEngineProcess;
    private Set<OutputListener> outputListenerSet;
    private BufferedReader engineInputReader;
    private PrintStream enginePrintStream;
    private Thread engineReaderThread;

    public BasicChessEngine(Process chessEngineProcess) {
        this.chessEngineProcess = chessEngineProcess;
        init();
    }

    private void init() {
        outputListenerSet = new HashSet<>();
        InputStream processInputStream = chessEngineProcess.getInputStream();
        OutputStream processOutputStream = chessEngineProcess.getOutputStream();
        engineInputReader = new BufferedReader(new InputStreamReader(processInputStream));
        enginePrintStream = new PrintStream(processOutputStream, true);

        engineReaderThread = new Thread(() -> {
            while (chessEngineProcess.isAlive()) {
                try {
                    String line = engineInputReader.readLine();
                    outputListenerSet.forEach(outputListener -> outputListener.onOutput(line));
                    if (line == null) {
                        break;
                    }
                } catch (IOException e) {
                    break;
                }
            }
        });
        engineReaderThread.setDaemon(true);
        engineReaderThread.start();
    }

    protected PrintStream getEnginePrintStream() {
        return this.enginePrintStream;
    }

    @Override
    public void sendCommand(String command) {
        enginePrintStream.println(command);
    }

    @Override
    public void addOutputListener(OutputListener outputListener) {
        outputListenerSet.add(outputListener);
    }

    @Override
    public void removeOutputListener(OutputListener outputListener) {
        outputListenerSet.remove(outputListener);
    }

    @Override
    public boolean hasOutputListener(OutputListener outputListener) {
        return outputListenerSet.contains(outputListener);
    }

    public void shutdown() {
        chessEngineProcess.destroy();
    }
}
