package server.factory.impl;

import java.io.IOException;

import server.factory.EngineParameters;
import server.factory.ProcessFactory;

public class SimpleProcessFactory implements ProcessFactory {

    private final Runtime runtime;
    private EngineParameters engineParameters;

    public SimpleProcessFactory(EngineParameters engineParameters) {
        this.engineParameters = engineParameters;
        this.runtime = Runtime.getRuntime();
    }

    @Override
    public Process createProcess() {
        String engineCommand = engineParameters.getEngineCommand();
        try {
            return runtime.exec(engineCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Unable to create engine process");
    }

    public void setEngineConfig(EngineParameters engineParameters) {
        this.engineParameters = engineParameters;
    }
}
