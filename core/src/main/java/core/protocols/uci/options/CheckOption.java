package core.protocols.uci.options;

public class CheckOption extends UCIOption<Boolean> {

    public CheckOption(String name, Boolean defaultValue) {
        super(name, defaultValue);
    }

    public CheckOption(String name, Boolean value, Boolean defaultValue) {
        super(name, value, defaultValue);
    }

    @Override
    public String getType() {
        return "check";
    }

    @Override
    public String getValueString() {
        return this.getValue() ? "true" : "false";
    }
}
