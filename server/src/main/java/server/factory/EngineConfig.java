package server.factory;

/**
 * Provides information about the engine that is being created on the host system.
 */
public interface EngineConfig {

    /**
     * @return The command that is required to start the engine
     */
    String getEngineCommand();
}
