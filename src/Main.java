public class Main {

    public static void main(String[] args) {
        double weight = 3.0;
        ShippingCalculator calculator = new ShippingCalculator(new StandardShipping());

        print("Standard", calculator.calculate(weight));

        calculator.setStrategy(new ExpressShipping());
        print("Express", calculator.calculate(weight));

        calculator.setStrategy(new StorePickup());
        print("Pickup", calculator.calculate(weight));

        calculator.setStrategy(new EconomyShipping());
        print("Economy", calculator.calculate(weight));
    }

    private static void print(String name, double value) {
        System.out.println(String.format("%s: R$ %.2f", name, value));
    }
}
