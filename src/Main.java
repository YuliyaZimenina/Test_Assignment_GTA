import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creating objects of application classes.
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        CaesarCipher caesarCipher = new CaesarCipher();
        ExpressionParser expressionParser = new ExpressionParser();
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        /* Starts an infinite loop that continues until the user selects exit or
         terminates the program.
         */
        while (true) {
            outputHandler.printMainMenu();
            String userChoice = inputHandler.readMenuChoice();

            /*
            We set up a try block to catch possible exceptions
            (IllegalArgumentException or ArithmeticException) that occur when
             performing operations.
             */
            try {
                // Uses a switch statement to handle the user's choice based on the value of 'userChoice'.
                switch (userChoice) {
                    case "1": // Caesar encryption.
                        // Calls the readInputSource method to get the input source (1 - console, 2 - file)
                        String source = inputHandler.readInputSource();
                        /*
                        Uses a ternary operator to select the text input method:
                        - if a source is "1", calls inputHandler.readText() to read text from the console.
                        - otherwise, calls inputHandler.readFromFile("input.txt") to read text from the file
                         "input.txt".
                        The result is stored in the text variable (type String).
                         */
                        String text = source.equals("1") ? inputHandler.readTextFromConsole() :
                                inputHandler.readTextFromFile("src/input.txt");
                        /*
                        Calls the 'readShiftValue' method to read the shift value (an integer)
                        from the console. Stores the result in shift.
                         */
                        int shift = inputHandler.readShiftValue();
                        /*
                        Calls the encrypt method of the ceaserCipher object to encrypt the text with
                        the shift 'shift'. The result is stored in encryptedText.
                         */
                        String encryptedText = caesarCipher.textEncrypt(text, shift);
                        /*
                        Calls the printResult method of the outputHandler object to print the encrypted
                         text.
                         */
                        outputHandler.printResult(encryptedText);
                        break;

                    case "2": // Caesar's decryption (with shift indication).
                        source = inputHandler.readInputSource();
                        text = source.equals("1") ? inputHandler.readTextFromConsole() :
                               inputHandler.readTextFromFile("src/input.txt");
                        shift = inputHandler.readShiftValue();
                        String decryptedText = caesarCipher.textDecrypt(text, shift);
                        outputHandler.printResult(decryptedText);
                        break;

                    case "3": // Caesar's decryption (without specifying the shift).
                        source = inputHandler.readInputSource();
                        text = source.equals("1") ? inputHandler.readTextFromConsole() :
                                inputHandler.readTextFromFile("src/input.txt");
                        String[] decryptionResults = caesarCipher.decryptWithoutShift(text);
                        outputHandler.printDecryptionWithoutShiftResult(decryptionResults);
                        break;

                    case "4": // Calculating an arithmetic expression.
                        /*
                        Calls the readArithmeticExpression method to read an arithmetic expression
                        from the console. Stores the result in 'arithmeticExpression'.
                         */
                        String arithmeticExpression = inputHandler.readArithmeticExpression();
                        /*
                        Calls the convertToRPN method of the expressionParser object to convert the
                        expression to Reverse Polish Notation (RPN). The result, a list of tokens
                        is stored in 'rpn'.
                         */
                        List<String> rpn = expressionParser.convertToRPN(arithmeticExpression);
                        /*
                        Calls the evaluateRPN method of the expressionEvaluator object to evaluate the
                         expression in RPN. The result (a double) is stored in 'result'.
                         */
                        double result = expressionEvaluator.evaluateRPN(rpn);
                        outputHandler.printResult(result);
                        break;

                    case "5": // Exit
                        System.out.println("The program is complete.");
                        return;

                    default:
                        outputHandler.printErrorMessage("Incorrect selection. Try again.");
                }
            /*
            Catches IllegalArgumentException (e.g., invalid input) or ArithmeticException (e.g., division
            by zero) exceptions.
            Multiple catch (|) allows both exception types to be handled in the same way.
             */
            }catch (IllegalArgumentException | ArithmeticException exception){
                outputHandler.printErrorMessage(exception.getMessage());
            }

            /*
            Calls the readContinue method of the inputHandler object, which asks the user whether to
             continue (y/n). If the user entered something other than "y" (e.g. "n"), the method returns
             false, and the !false condition becomes true.
             */
            if(!inputHandler.readContinue()){
                System.out.println("The program is complete.");
                return;
            }
        }
    }
}
