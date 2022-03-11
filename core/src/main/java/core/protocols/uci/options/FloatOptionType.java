package core.protocols.uci.options;

public class FloatOptionType extends UCIOptionType<Float> {

    private Float minValue;
    private Float maxValue;

    public FloatOptionType(String name, Float defaultValue, Float minValue, Float maxValue) {
        super(name, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public FloatOptionType(String name, Float value, Float defaultValue, Float minValue, Float maxValue) {
        super(name, value, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public String getType() {
        return "spin";
    }

    @Override
    public String getValueString() {
        return String.valueOf(this.getValue());
    }

    @Override
    public void setValue(Float value) {
        if (value < this.minValue) {
            throw new IllegalArgumentException(String.format("'%s' value must be at least %f", this.getName(), this.getValue()));
        }
        if (value > this.maxValue) {
            throw new IllegalArgumentException(String.format("'%s' value must be at most %f", this.getName(), this.getValue()));
        }
        super.setValue(value);
    }
}
