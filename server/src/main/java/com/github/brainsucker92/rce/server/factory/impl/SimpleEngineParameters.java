package com.github.brainsucker92.rce.server.factory.impl;

import com.github.brainsucker92.rce.server.factory.EngineParameters;

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
