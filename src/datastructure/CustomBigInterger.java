package datastructure;

import java.math.BigInteger;

public class CustomBigInterger {
    private static class CustomBigInteger {
        private final BigInteger value;

        public CustomBigInteger(int value) {
            this.value = new BigInteger(String.valueOf(value));
        }


        public CustomBigInteger(String value) {
            this.value = new BigInteger(value);
        }

        public CustomBigInteger(long value) {
            this.value = new BigInteger(String.valueOf(value));
        }

        public CustomBigInteger(CustomBigInteger value) {
            this.value = new BigInteger(value.toString());
        }

        public CustomBigInteger(BigInteger value) {
            this.value = new BigInteger(value.toString());
        }

        @Override
        public String toString() {
            return this.value.toString();
        }

        public int toInt() {
            return Integer.parseInt(this.toString());
        }

        public boolean lessThan(CustomBigInteger value) {
            return this.value.compareTo(value.value) < 0;
        }

        public boolean equal(CustomBigInteger value) {
            return this.value.compareTo(value.value) == 0;
        }

        public boolean greaterThan(CustomBigInteger value) {
            return this.value.compareTo(value.value) > 0;
        }

        public boolean greaterThanOrEqual(CustomBigInteger value) {
            return this.greaterThan(value) || this.equal(value);
        }

        public boolean lessThanOrEqual(CustomBigInteger value) {
            return this.lessThan(value) || this.equal(value);
        }

        public static CustomBigInteger max(CustomBigInteger a, CustomBigInteger b) {
            if (a.greaterThan(b))
                return a;
            return b;
        }

        public static CustomBigInteger min(CustomBigInteger a, CustomBigInteger b) {
            if (a.lessThan(b))
                return a;
            return b;
        }

        public CustomBigInteger add(String value) {
            return new CustomBigInteger(
                    this.value.add(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger add(int value) {
            return new CustomBigInteger(
                    this.value.add(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger add(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.add(
                            value.value
                    )
            );
        }

        public CustomBigInteger add(long value) {
            return new CustomBigInteger(
                    this.value.add(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger subtract(String value) {
            return new CustomBigInteger(
                    this.value.subtract(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger subtract(int value) {
            return new CustomBigInteger(
                    this.value.subtract(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger subtract(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.subtract(
                            value.value
                    )
            );
        }

        public CustomBigInteger subtract(long value) {
            return new CustomBigInteger(
                    this.value.subtract(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger mod(String value) {
            return new CustomBigInteger(
                    this.value.mod(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger mod(int value) {
            return new CustomBigInteger(
                    this.value.mod(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger mod(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.mod(
                            value.value
                    )
            );
        }

        public CustomBigInteger mod(long value) {
            return new CustomBigInteger(
                    this.value.mod(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger mul(String value) {
            return new CustomBigInteger(
                    this.value.multiply(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger mul(int value) {
            return new CustomBigInteger(
                    this.value.multiply(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger mul(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.multiply(
                            value.value
                    )
            );
        }

        public CustomBigInteger mul(long value) {
            return new CustomBigInteger(
                    this.value.multiply(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }


        public CustomBigInteger div(String value) {
            return new CustomBigInteger(
                    this.value.divide(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger div(int value) {
            return new CustomBigInteger(
                    this.value.divide(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger div(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.divide(
                            value.value
                    )
            );
        }

        public CustomBigInteger div(long value) {
            return new CustomBigInteger(
                    this.value.divide(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }
    }
}
