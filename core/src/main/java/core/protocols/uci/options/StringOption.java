package core.protocols.uci.options;

public class StringOption extends UCIOption<String> {

    public StringOption(String name, String defaultValue) {
        super(name, defaultValue);
    }

    public StringOption(String name, String value, String defaultValue) {
        super(name, value, defaultValue);
    }

    @Override
    public String getType() {
        return "string";
    }

    @Override
    public String getValueString() {
        return this.getValue();
    }
}
