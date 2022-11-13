package com.github.brainsucker92.rce.server.factory;

/**
 * Provides information about the engine that is being created on the host system.
 */
public interface EngineParameters {

    /**
     * @return The command that is required to start the engine
     */
    String getEngineCommand();
}
