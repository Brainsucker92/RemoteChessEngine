package core.protocols.uci.options;

public class CheckOptionType extends UCIOptionType<Boolean> {

    public CheckOptionType(String name, Boolean defaultValue) {
        super(name, defaultValue);
    }

    public CheckOptionType(String name, Boolean value, Boolean defaultValue) {
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
