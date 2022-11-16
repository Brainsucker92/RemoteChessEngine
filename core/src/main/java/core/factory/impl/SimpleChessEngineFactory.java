package core.factory.impl;

import core.engine.ChessEngine;
import core.engine.impl.BasicChessEngine;
import core.factory.ChessEngineFactory;

public class SimpleChessEngineFactory implements ChessEngineFactory {

    private final Runtime runtime = Runtime.getRuntime();

    private final String engineCommand;

    public SimpleChessEngineFactory(String engineCommand) {
        this.engineCommand = engineCommand;
    }

    @Override
    public ChessEngine createEngine() {
        Process process;
        try {
            process = runtime.exec(engineCommand);
            return new BasicChessEngine(process);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create engine process", e);
        }
    }
}
