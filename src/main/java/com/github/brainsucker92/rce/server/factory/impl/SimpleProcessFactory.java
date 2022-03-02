package com.github.brainsucker92.rce.server.factory.impl;

import java.io.IOException;

import com.github.brainsucker92.rce.server.factory.EngineConfig;
import com.github.brainsucker92.rce.server.factory.ProcessFactory;

public class SimpleProcessFactory implements ProcessFactory {

    private final Runtime runtime;
    private EngineConfig engineConfig;

    public SimpleProcessFactory(EngineConfig engineConfig) {
        this.engineConfig = engineConfig;
        this.runtime = Runtime.getRuntime();
    }

    @Override
    public Process createProcess() {
        String engineCommand = engineConfig.getEngineCommand();
        try {
            return runtime.exec(engineCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Unable to create engine process");
    }

    public void setEngineConfig(EngineConfig engineConfig) {
        this.engineConfig = engineConfig;
    }
}
