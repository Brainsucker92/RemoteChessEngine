package core.engine;

public interface ChessEngine {

    void sendCommand(String command);

    void addOutputListener(OutputListener outputListener);

    void removeOutputListener(OutputListener outputListener);

    boolean hasOutputListener(OutputListener outputListener);

    void shutdown();
}
