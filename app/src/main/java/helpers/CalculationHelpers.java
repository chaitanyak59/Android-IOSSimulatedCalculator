package helpers;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public class CalculationHelpers {
    private CalculationHelpers() {
    }

    public static double getFactorialValue(double d) {
        double r = d - Math.floor(d) + 1;
        for (; d > 1; d -= 1) {
            r *= d;
        }
        return r;
    }

    public static double getSquareRootValue(double input) {
        return Math.sqrt(input);
    }

    public static double getSquarePow(double input) {
        return Math.pow(input, 2);
    }

    public static double getExponentialValue(double input) {
        return Math.exp(input);
    }

    public static BigDecimal toBigDecimal(String input) {
        return new BigDecimal(input);
    }

    @NonNull
    public static Double toDouble(String input) {
        return Double.valueOf(input);
    }

    public static BigDecimal sum(BigDecimal opr_a, BigDecimal opr_b) {
        return opr_a.add(opr_b);
    }

    public static Boolean isValid(String str) {
        return !str.isEmpty();
    }
    public static BigDecimal sub(BigDecimal opr_a, BigDecimal opr_b) {
        return opr_a.subtract(opr_b);
    }

    public static BigDecimal mul(BigDecimal opr_a, BigDecimal opr_b) {
        return opr_a.multiply(opr_b);
    }

    public static BigDecimal div(@NonNull BigDecimal opr_a, BigDecimal opr_b) {
        return opr_a.divide(opr_b);
    }

    public static Double mod(Double opr_a, Double opr_b) {
        return opr_a % opr_b;
    }
}
