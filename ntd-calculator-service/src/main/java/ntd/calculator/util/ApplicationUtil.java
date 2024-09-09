package ntd.calculator.util;

import java.util.Random;

import static ntd.calculator.constants.ApplicationConstants.ARITHMETIC_OPERATORS;

public class ApplicationUtil {

    public static boolean isValidMathematicalExpression(String mathematicalExpression) {
        return !mathematicalExpression.contains("cos") && !mathematicalExpression.contains("log")
                && !mathematicalExpression.contains("sin") && !mathematicalExpression.contains("avg")
                && !mathematicalExpression.contains("`") && !mathematicalExpression.contains("#")
                && !mathematicalExpression.contains("%") && !mathematicalExpression.contains("~")
                && !mathematicalExpression.contains("^") && !mathematicalExpression.contains("$")
                && !mathematicalExpression.contains("{") && !mathematicalExpression.contains("}")
                && !mathematicalExpression.contains("[") && !mathematicalExpression.contains("]")
                && !mathematicalExpression.contains("pi") && !mathematicalExpression.contains("power")
                && !mathematicalExpression.contains("max") && !mathematicalExpression.contains("min");
    }

    public static String getRandomArithmeticOperator() {
        Random random = new Random();
        int index = random.nextInt(ARITHMETIC_OPERATORS.length);
        return ARITHMETIC_OPERATORS[index];
    }

}
