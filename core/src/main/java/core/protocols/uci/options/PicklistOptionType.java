package core.protocols.uci.options;

import java.util.Arrays;

public class PicklistOptionType extends UCIOptionType<String> {

    private String[] allowedValues;

    public PicklistOptionType(String name, String defaultValue, String[] allowedValues) {
        super(name, defaultValue);
        this.allowedValues = allowedValues;
    }

    public PicklistOptionType(String name, String value, String defaultValue, String[] allowedValues) {
        super(name, value, defaultValue);
        this.allowedValues = allowedValues;
    }

    @Override
    public String getType() {
        return "string";
    }

    @Override
    public String getValueString() {
        return this.getValue();
    }

    @Override
    public void setValue(String value) {
        if (!Arrays.asList(allowedValues).contains(value)) {
            throw new IllegalArgumentException(String.format("Value '%s' is not allowed for option %s", value, this.getName()));
        }
        super.setValue(value);
    }
}
