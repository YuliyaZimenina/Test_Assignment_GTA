/**
 * The OutputHandler class implements data output.
 */
public class OutputHandler {
    // Method for displaying the main menu.
    public void printMainMenu() {
        System.out.println("Welcome to Gehtsoft Technical Assessment!");
        System.out.println("Select an option: ");
        System.out.println("1. Caesar encryption.");
        System.out.println("2. Caesar's decryption (with shift indication).");
        System.out.println("3. Caesar decryption (without specifying the shift).");
        System.out.println("4. Calculating an arithmetic expression.");
        System.out.println("5. Exit.");
    }

    // Method for outputting the result.
    public void printResult(String result) {
        System.out.println("Result: " + result);
    }

    // Method for outputting the result of an arithmetic expression.
    public void printResult(double result) {
        System.out.printf("Result: %.2f%n", result);
    }

    // Method for outputting decryption the result without shift.
    public void printDecryptionWithoutShiftResult(String[] results) {

        // Output the most suitable decryption result, which is in 'results[0]'.
        if (results[0] != null) {
            System.out.println("Best decryption result: " + results[0]);
        } else {
            System.out.println("No valid decryption result found.");
        }
    }

    // Method for displaying error message.
    public void printErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

}
