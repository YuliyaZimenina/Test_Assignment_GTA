/**
 * The OutputHandler class implements data output.
 */
public class OutputHandler {
    // Method for displaying the main menu.
    public void printMainMenu(){
        System.out.println("Welcome to Gehtsoft Technical Assessment!");
        System.out.println("Select an option: ");
        System.out.println("1. Caesar encryption.");
        System.out.println("2. Caesar's decryption (with shift indication).");
        System.out.println("3. Caesar decryption (without specifying the shift).");
        System.out.println("4. Calculating an arithmetic expression.");
        System.out.println("5. Exit.");
    }

    // Method for outputting the result.
    public void printResult(String result){
        System.out.println("Result: " + result);
    }

    // Method for outputting the result of an arithmetic expression.
    public void printResult(double result){
        System.out.printf("Result: %.2f%n", result);
    }

    // Method for outputting decryption the result without shift.
    public void printDecryptionWithoutShiftResult(String[] results){
        /* A for loop that iterates through the indices of results array from
         0 to results.length - 1.
         */
        for (int i = 0; i < results.length; i++) {
            /*
            For each element of the results array, outputs a line like "Shift X: <result>", where:
            i + 1 is the shift number (starts with 1, not 0, for the user's convenience).
            results[i] is the decoding result for the current shift.
             */
            System.out.println("Shift " + (i + 1) + ": " + results[i]);
        }
    }

    // Method for displaying error message.
    public void printErrorMessage(String message){
        System.err.println("Error: " + message);
    }

}
