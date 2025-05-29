import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The InputHandler class implements the logic for processing data
 * input into the application.
 */
public class InputHandler {
    private final Scanner scanner;

    // Constructor of the InputHandler class.
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    // Method for reading data from the console.
    public String readTextFromConsole() {
        System.out.println("Enter text: ");
        return scanner.nextLine();
    }

    // Method for reading data from a file.
    public String readTextFromFile(String filePath) {
        try {
            /**
             * @param Path.get(filePath) is used to create a Path object representing the path
             *                           to the file specified by filePath.
             * @param File.readString reads the entire contents of a file into a string.
             *                        If the read is successful, returns the contents of the file
             *                        as a string.
             */
            return Files.readString(Paths.get(filePath));

        /*
          Handles an IOException that may occur when reading a file (for example, the file doesn't
          exist or is inaccessible).
          Throws a new IllegalArgumentException with a message that includes the error text from
          the IOException (exception.getMessage()).
         */
        } catch (IOException exception) {
            throw new IllegalArgumentException("Error reading file: " + exception.getMessage());
        }
    }

    // Method for reading the shift value for a Caesar cipher.
    public int readShiftValue() {
        System.out.println("Enter the shift value (integer): ");
        try {
            /*
            Reads a line from the console using scanner.nextLine().
            Attempts to convert the string to an integer using Integer.parseInt.
            If the conversion is successful, returns the resulting number.
             */
            return Integer.parseInt(scanner.nextLine());
        /*
        Handles the NumberFormatException that occurs when the input string isn't an integer (e.g. "abc").
        Throws an IllegalArgumentException with the message "The shift must be an integer."
         */
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("The shift must be an integer.");
        }
    }

    // Method for reading an arithmetic expression.
    public String readArithmeticExpression() {
        System.out.println("Enter the arithmetic expression: ");
        return scanner.nextLine();
    }

    // Method for reading the input source selection.
    public String readInputSource() {
        System.out.println("Select input source (1 - console, 2 - file): ");
        return scanner.nextLine();
    }

    // Method for reading the choice to continue running the application.
    public boolean readContinue() {
        System.out.println("Continue? (y/n):");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    // Method for reading menu option selections.
    public String readMenuChoice() {
        System.out.println("Enter your choice (1-4): ");
        return scanner.nextLine();
    }


}
