import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * The ExpressionEvaluator implements the application logic for
 * evaluating an arithmetic expression using Reverse Polish Notation (RPN).
 */
public class ExpressionEvaluator {
    // A method for computing the result of an expression represented in RPN.
    public double evaluateRPN(List<String> rpn) {
        // Create a stack to store numbers (operands) during calculation.
        Deque<Double> numbersStack = new ArrayDeque<>();
        // The for-each loop iterates over each token (string) from the rpn list.
        for (String token : rpn) {
            // Checks whether the current token is a number using the isNumber method.
            if (isNumber(token)) {
                /*
                If a token is a number, convert it from a string to a double using Double.parseDouble(token).
                Adds a number to the stack using push.
                 */
                numbersStack.push(Double.parseDouble(token));

                //If the token isn't a number, it's assumed to be an operator and processing begins.
            } else {
                /*
                Checks if there are at least two numbers (operands) on the stack that are required to
                perform the operation. If there are less than two elements on the stack,
                an IllegalArgumentException is thrown with the message "Invalid expression"
                because the operator requires two operands.
                 */
                if (numbersStack.size() < 2) {
                    throw new IllegalArgumentException("Incorrect expression.");
                }
                /**
                 * Pops two numbers from the stack:
                 * @param secondOperand is the top number (the second operand).
                 * @param firstOperand is the next number (the first operand).
                 * The order is important because in RPN, operands are processed in the order they appear,
                 * but for operations like subtraction (firstOperand - secondOperand) or division
                 * (firstOperand / secondOperand), the first operand (firstOperand) must be popped second.
                 */
                double secondOperand = numbersStack.pop();
                double firstOperand = numbersStack.pop();

                switch (token) {
                    case "+":
                        numbersStack.push(firstOperand + secondOperand);
                        break;
                    case "-":
                        numbersStack.push(firstOperand - secondOperand);
                        break;
                    case "*":
                        numbersStack.push(firstOperand * secondOperand);
                        break;
                    case "/":
                        if (secondOperand == 0) {
                            throw new ArithmeticException("Division by zero!");
                        }
                        numbersStack.push(firstOperand / secondOperand);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator:" + token);
                }
            }
        }

        /*
        After processing all tokens, checks whether there is exactly one element left on the stack
        (the result of the calculation). If there is more or less than one element on the stack,
         the expression is considered invalid and an IllegalArgumentException is thrown.
         */
        if (numbersStack.size() != 1) {
            throw new IllegalArgumentException("Incorrect expression.");
        }
        // Pops the only element from the stack (the result of the computation) and returns it as a double.
        return numbersStack.pop();
    }

    // A method that checks whether a token is a number.
    private boolean isNumber(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
