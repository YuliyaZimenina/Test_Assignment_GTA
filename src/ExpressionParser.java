import java.util.*;

/**
 * The ExpressionParser class implements the application logic for parsing
 * arithmetic expressions into Reverse Polish Notation (RPN).
 */
public class ExpressionParser {
    /**
     * @param Map<String, Integer> PRECEDENCE - Storing priorities of arithmetic operators
     * @param HashMap - used for quick access to priorities by string key
     */
    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();

    /**
     * The static initializer (static{}) fills the PRECEDENCE map with values:
     * '+' and '-' have priority 1 (lowest)
     * '*' and '/' have priority 2 (highest)
     */
    static {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
    }

    // A method that converts an arithmetic expression to RPN.
    public List<String> convertToRPN(String expression) {
        List<String> conversionResult = new ArrayList<>(); // List for storing results in RPN.
        // A stack for temporary storage of arithmetic operators and parentheses.
        Deque<String> operatorStack = new ArrayDeque<>();
        /*Creates a StringBuilder object named numbers for building numbers (including negative
         and decimal numbers) from characters.
         */
        StringBuilder numbers = new StringBuilder();
        expression = expression.replaceAll("\\s+", ""); // Removing spaces

        // A loop that iterates over each character of the input string 'expression' at index 'i'.
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            /**
             * Checks the incoming character for:
             * @param Character.isDigit(ch) - whether the character 'ch' is part of a number.
             * @param ch == '.' - whether the character 'ch' is '.' (for decimal numbers).
             * @param (ch == '-' && (i==0 || expression.charAt(i-1) == '(')) - whether the
             *                                                               character 'ch' is a minus,
             *                                                               denoting a negative number.
             *   NOTE: this is true if:
             *   - i == 0 (minus at the beginning of the expression, e.g. "-5").
             *   - expression.charAt(i-1) == '(' (minus after the opening parathesis, e.g. "(-5)").
             *   If the condition is met, the symbol is considered part of the number.
             */
            if (Character.isDigit(ch) || ch == '.' || (ch == '-' && (i == 0 || expression.charAt(i - 1) == '('))) {
                // If the character 'ch' is part of a number, appends it to the StringBuilder numbers.
                numbers.append(ch);
            /* If the character 'ch' isn't part of a number, processing of operators, parentheses,
            or other characters begins.
             */
            } else {
                // Check if a 'numbers' contains a number
                if (numbers.length() > 0) {
                    // //If the variable isn't empty, add the resulting number to the list conversionResult.
                    conversionResult.add(numbers.toString());
                    numbers.setLength(0); // Clearing StringBuilder for the next number.
                }
                // Checks if the character 'ch' is one of the operators: '+', '-', '*', '/'.
                if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    String arithmeticOperator = String.valueOf(ch);

                    /**
                     *  A loop that pops operators from the stack into convertResult if:
                     * @param (!operatorStack.isEmpty()) - the stack isn't empty.
                     * @param (!operatorStack.peek().equals("(")) - the top element of the stack isn't an
                     *                                            opening parenthesis.
                     * @param (PRECEDENCE.get(operatorStack.peek())) - is greater than or equal to the
                     *                                                 precedence of the current operator
                     *                                                 (PRECEDENCE.get(arithmeticOperator)).
                     */
                    while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(") &&
                            PRECEDENCE.get(operatorStack.peek()) >= PRECEDENCE.get(arithmeticOperator)) {
                        conversionResult.add(operatorStack.pop());
                    }
                    operatorStack.push(arithmeticOperator);
                } else if (ch == '(') {
                    /* If the character 'ch' is an opening parenthesis '(', converts it to a string and
                    pushes it onto the stack with operatorStack.
                     */
                    operatorStack.push(String.valueOf(ch));
                // If the character 'ch' is a closing bracket ')', the contents of the brackets are processed.
                } else if (ch == ')') {
                    /*
                    Pops all operators from the stack into 'conversionResult' until an opening parenthesis '(' is
                    encountered or the stack is empty.
                     */
                    while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                        conversionResult.add(operatorStack.pop());
                    }
                    /*If the stack isn't empty and the top element is an opening parenthesis '(',
                     pops it off the stack.
                     */
                    if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                        operatorStack.pop(); // The bracket is removed as it isn't needed in RPN.
                    /*
                    If the stack is empty or the top element isn't '(', an IllegalArgumentException is thrown
                     with a message about the mismatched parenthesis (e.g., an extra closing parenthesis.
                     */
                    } else {
                        throw new IllegalArgumentException("Parentheses mismatch");
                    }
                /*
                If the character 'ch' isn't a number, operator, or parenthesis, an exception is thrown
                indicating that the character is invalid.
                 */
                } else {
                    throw new IllegalArgumentException("Invalid character: " + ch);
                }
            }
        }

        /*
        After the loop completes, checks if there is an unsaved number left in numbers.
        If so, appends it to 'conversionResult' as a string.
         */
        if (numbers.length() > 0) {
            conversionResult.add(numbers.toString());
        }
        /*
        After processing all characters, pushes the remaining operators from the stack to
        'conversionResult'.
        'arithmeticOperator' is the next operator popped from the stack.
         */
        while (!operatorStack.isEmpty()) {
            String arithmeticOperator = operatorStack.pop();
            if (arithmeticOperator.equals("(")) {
                throw new IllegalArgumentException("Parentheses mismatch");
            }
            conversionResult.add(arithmeticOperator);
        }
        return conversionResult;
    }
}
