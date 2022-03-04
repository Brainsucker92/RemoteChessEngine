package server.factory.impl;

import server.factory.EngineConfig;

public class SimpleEngineConfig implements EngineConfig {

    private final String engineCommand;

    public SimpleEngineConfig(String engineCommand) {
        this.engineCommand = engineCommand;
    }

    @Override
    public String getEngineCommand() {
        return engineCommand;
    }
}
