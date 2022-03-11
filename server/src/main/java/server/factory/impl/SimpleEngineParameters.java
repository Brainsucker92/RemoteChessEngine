package server.factory.impl;

import server.factory.EngineParameters;

public class SimpleEngineParameters implements EngineParameters {

    private final String engineCommand;

    public SimpleEngineParameters(String engineCommand) {
        this.engineCommand = engineCommand;
    }

    @Override
    public String getEngineCommand() {
        return engineCommand;
    }
}
