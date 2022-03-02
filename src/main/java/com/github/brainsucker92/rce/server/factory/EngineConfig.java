package com.github.brainsucker92.rce.server.factory;

/**
 * Provides information about the engine that is being created on the host system. For the time being this class only
 * requires
 */
public interface EngineConfig {

    /**
     * @return The command that is required to start the engine
     */
    String getEngineCommand();
}
