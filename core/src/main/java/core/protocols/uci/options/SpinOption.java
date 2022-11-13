package core.protocols.uci.options;

public class SpinOption extends UCIOption<Integer> {

    private final Integer minValue;
    private final Integer maxValue;

    public SpinOption(String name, Integer defaultValue, Integer minValue, Integer maxValue) {
        super(name, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public SpinOption(String name, Integer value, Integer defaultValue, Integer minValue, Integer maxValue) {
        super(name, value, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public Integer getMinValue() {
        return minValue;
    }

    @Override
    public void setValue(Integer value) {
        if (value < this.getMinValue()) {
            throw new IllegalArgumentException(String.format("'%s' value must be at least %d", this.getName(), this.getMinValue()));
        }
        if (value > this.getMaxValue()) {
            throw new IllegalArgumentException(String.format("'%s' value must be at most %d", this.getName(), this.getMaxValue()));
        }
        super.setValue(value);
    }

    @Override
    public String getType() {
        return "spin";
    }

    @Override
    public String getValueString() {
        return String.valueOf(this.getValue());
    }
}
