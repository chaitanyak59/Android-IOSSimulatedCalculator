package com.cpasnoor.humberfinalcalculator;

import static helpers.CalculationHelpers.div;
import static helpers.CalculationHelpers.getPICalculation;
import static helpers.CalculationHelpers.isValid;
import static helpers.CalculationHelpers.mod;
import static helpers.CalculationHelpers.mul;
import static helpers.CalculationHelpers.sub;
import static helpers.CalculationHelpers.sum;
import static helpers.CalculationHelpers.toBigDecimal;
import static helpers.CalculationHelpers.toDouble;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import helpers.CalculationHelpers;

public class Calculator {
    private final String DEFAULT_RESULT = "0";
    private String currentArithmeticOperation = "";
    private String operandA = "";
    private String operandB = "";
    private double MEMORY_VARIABLE = 0;

    public String getOperandA() {
        return operandA;
    }

    public void setOperandA(String operandA) {
        this.operandA = operandA;
    }

    public String getOperandB() {
        return operandB;
    }

    public void setOperandB(String operandB) {
        this.operandB = operandB;
    }

    public String getOperationStatus() {
        return currentArithmeticOperation;
    }

    public void setOperationStatus(String currentArithmeticOperation) {
        this.currentArithmeticOperation = currentArithmeticOperation;
    }

    public String performUnaryOperations(int ID, @NonNull String currValue) {
        try {
            switch (ID) {
                case R.string.btnAC:
                    //RESET ...
                    resetActivity();
                    return DEFAULT_RESULT;
                case R.string.btnSQRT:
                    BigDecimal sqrD = getFilteredInput(currValue);
                    Double res = CalculationHelpers.getSquareRootValue(sqrD.doubleValue());
                    return getStringifyResult(res);
                case R.string.btnFACT:
                    BigDecimal factD = getFilteredInput(currValue);
                    Double result = CalculationHelpers.getFactorialValue(factD.doubleValue());
                    return getStringifyResult(result);
                case R.string.btnSQR:
                    BigDecimal powD = getFilteredInput(currValue);
                    Double powD_res = CalculationHelpers.getSquarePow(powD.doubleValue());
                    return getStringifyResult(powD_res);
                case R.string.btnEXP:
                    BigDecimal expD = getFilteredInput(currValue);
                    Double exp_res = CalculationHelpers.getExponentialValue(expD.doubleValue());
                    return getStringifyResult(exp_res);
                case R.string.btnPI:
                    BigDecimal pi_input = getFilteredInput(currValue);
                    BigDecimal pi_res = getPICalculation(pi_input);
                    return getStringifyResult(pi_res.doubleValue());
                default:
                    return "0";
            }
        } catch (Exception e) {
            return "0";
        }
    }

    public String performArithOperations() {
        String operation = getOperationStatus();
        try {
            if ((isValid(getOperandA()) && isValid(getOperandB()))) {
                BigDecimal a = toBigDecimal(getOperandA());
                BigDecimal b = toBigDecimal(getOperandB());
                switch (operation) {
                    case "+": //ADD
                        BigDecimal result = sum(a, b);
                        return getStringifyResult(result.doubleValue());
                    case "-": //Subtract
                        BigDecimal result2 = sub(a, b);
                        return getStringifyResult(result2.doubleValue());
                    case "x": // Multiply
                        BigDecimal result3 = mul(a, b);
                        return getStringifyResult(result3.doubleValue());
                    case "/": //Divide
                        BigDecimal result4 = div(a, b);
                        return getStringifyResult(result4.doubleValue());
                    case "%": // Modulo
                        Double mod_a = toDouble(getOperandA());
                        Double mod_b = toDouble(getOperandB());
                        Double result5 = mod(mod_a, mod_b);
                        return getStringifyResult(result5);
                    default:
                        throw new IllegalStateException("Unexpected value: " + operation);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return "Operation Error"; // If No Operation Performed, Send the current value as it is
        }
    }

    public String performMemoryOperations(int resourceID, String currValue) {
        try {
            switch (resourceID) {
                case R.string.btnMC:
                    MEMORY_VARIABLE = 0;
                    return DEFAULT_RESULT;
                case R.string.btnMP:
                    MEMORY_VARIABLE += Double.parseDouble(currValue);
                    return DEFAULT_RESULT;
                case R.string.btnMM:
                    MEMORY_VARIABLE -= Double.parseDouble(currValue);
                    return DEFAULT_RESULT;
                default:
                    return getStringifyResult(MEMORY_VARIABLE);
            }
        } catch (Exception e) {
            return "Operation Error";
        }
    }

    public void updateOperandsByStatus(String value, Boolean forceUpdate) {
        if (forceUpdate || getOperandA().isEmpty()) {
            setOperandA(value);
        } else {
            setOperandB(value);
        }
    }

    private void resetActivity() {
        setOperationStatus("");
        setOperandA("");
        setOperandB("");
    }

    private BigDecimal getFilteredInput(String input) {
        return toBigDecimal(input).stripTrailingZeros();
    }

    private String getStringifyResult(Double input) {
        DecimalFormat format = new DecimalFormat("0.##########");
        return format.format(input);
    }
}
