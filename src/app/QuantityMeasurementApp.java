package app;

public class QuantityMeasurementApp {

    // Enum for Units with conversion to FEET (base unit)
    enum Unit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeet;

        Unit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }
    }

    // Generic Quantity class (from UC3)
    static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        // Equality check (cross-unit comparison)
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    public static void main(String[] args) {

        // UC3 Test (Feet vs Inches)
        Quantity q1 = new Quantity(1.0, Unit.FEET);
        Quantity q2 = new Quantity(12.0, Unit.INCHES);

        // UC4 Tests (New Units)
        Quantity q3 = new Quantity(1.0, Unit.YARDS);
        Quantity q4 = new Quantity(3.0, Unit.FEET);

        Quantity q5 = new Quantity(1.0, Unit.CENTIMETERS);
        Quantity q6 = new Quantity(0.393701, Unit.INCHES);

        // Outputs
        System.out.println("Feet vs Inches: " + q1.equals(q2));
        System.out.println("Yard vs Feet: " + q3.equals(q4));
        System.out.println("CM vs Inches: " + q5.equals(q6));
    }
}