package com.github.brainsucker92.rce.server.factory.impl;

import com.github.brainsucker92.rce.server.factory.ChessEngineFactory;
import com.github.brainsucker92.rce.server.factory.EngineParameters;
import core.engine.ChessEngine;
import core.engine.impl.BasicChessEngine;

public class SimpleChessEngineFactory implements ChessEngineFactory {

    private final Runtime runtime = Runtime.getRuntime();

    private final String engineCommand;

    public SimpleChessEngineFactory(String engineCommand) {
        this.engineCommand = engineCommand;
    }

    public SimpleChessEngineFactory(EngineParameters engineParameters) {
        this.engineCommand = engineParameters.getEngineCommand();
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
