package com.github.brainsucker92.rce.server.factory.impl;

import com.github.brainsucker92.rce.server.factory.EngineConfig;

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
