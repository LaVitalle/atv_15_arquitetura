public class ShippingCalculator {

    private ShippingStrategy strategy;

    public ShippingCalculator(ShippingStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(ShippingStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public double calculate(double weight) {
        return strategy.calculate(weight);
    }
}
