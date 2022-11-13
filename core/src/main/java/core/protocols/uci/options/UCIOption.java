package core.protocols.uci.options;

import core.engine.ChessEngine;
import core.protocols.uci.impl.UCIProtocol;

public abstract class UCIOption<T> {

    private final String name;
    private final T defaultValue;
    private T value;

    public UCIOption(String name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public UCIOption(String name, T value, T defaultValue) {
        this.name = name;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getDefaultValue() {
        return defaultValue;
    }


    public void reset() {
        this.value = defaultValue;
    }

    public abstract String getType();

    public abstract String getValueString();

    public void applyOn(ChessEngine chessEngine) {
        String name = this.getName();
        String valStr = this.getValueString();
        String command = UCIProtocol.setoption(name, valStr);
        chessEngine.sendCommand(command);
    }
}
