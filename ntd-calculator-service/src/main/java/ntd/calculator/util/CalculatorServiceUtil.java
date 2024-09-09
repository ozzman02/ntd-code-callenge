package ntd.calculator.util;

public class CalculatorServiceUtil {

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
}
