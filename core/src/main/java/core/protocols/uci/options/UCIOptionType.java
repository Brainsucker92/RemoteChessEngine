package core.protocols.uci.options;

import core.engine.ChessEngine;

public abstract class UCIOptionType<T> {

    private final String name;
    private final T defaultValue;
    private T value;

    public UCIOptionType(String name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public UCIOptionType(String name, T value, T defaultValue) {
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

    public void resetToDefault() {
        this.value = defaultValue;
    }

    public abstract String getType();

    public abstract String getValueString();

    public void applyOn(ChessEngine chessEngine) {
        String name = this.getName();
        String type = this.getType();
        String valStr = this.getValueString();
        chessEngine.sendCommand(String.format("setoption name %s type %s value %s", name, type, valStr));
    }
}
