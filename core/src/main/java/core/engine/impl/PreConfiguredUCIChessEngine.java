package core.engine.impl;

/**
 *
 */
public class PreConfiguredUCIChessEngine extends BasicChessEngine {
    public PreConfiguredUCIChessEngine(Process chessEngineProcess) {
        super(chessEngineProcess);
    }

    @Override
    public void sendCommand(String command) {
        if (command.toLowerCase().startsWith("setoption")) {
            return;
        }
        super.sendCommand(command);
    }
}
