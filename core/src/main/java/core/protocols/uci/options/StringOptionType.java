package core.protocols.uci.options;

public class StringOptionType extends UCIOptionType<String> {

    public StringOptionType(String name, String defaultValue) {
        super(name, defaultValue);
    }

    public StringOptionType(String name, String value, String defaultValue) {
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
