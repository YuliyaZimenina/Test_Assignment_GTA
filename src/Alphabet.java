/**
 * The Alphabet class implements the logic of the application working with alphabets:
 * - English Alphabet.
 * - Russian Alphabet.
 */
public class Alphabet {
    // Constants for alphabets.
    private static final String LATIN_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    // Method to check if a character 'ch' is a Latin letter.
    public static boolean isLatinLetter(char ch) {
          /*
        .indexOf(...) method returns the index of a character 'ch' in a string, or -1 if the character 'ch'
         doesn't exist.
         If the index isn't -1, the character 'ch' is found, and the method returns true (the character 'ch'
         is a Latin letter). Otherwise, it returns false.
         */
        return LATIN_ALPHABET.indexOf(Character.toLowerCase(ch)) != -1;
    }

    // Method to check if a character 'ch' is a Russian letter.
    public static boolean isRussianLetter(char ch) {
        /*
        .indexOf(...) method returns the index of a character 'ch' in a string, or -1 if the character 'ch'
         doesn't exist.
         If the index isn't -1, the character 'ch' is found, and the method returns true (the character 'ch'
         is a Russian letter). Otherwise, it returns false.
         */
        return RUSSIAN_ALPHABET.indexOf(Character.toLowerCase(ch)) != -1;
    }

    /**
     * A method that implements a character shift by a specified number of positions.
     *
     * @param ch               - character for shift.
     * @param shiftLetter      - number of positions to shift (positive - forward, negative - backward).
     * @param isRussianLetters - specifies whether to use the Russian (true) or Latin (false) alphabet.
     */
    public static char letterShift(char ch, int shiftLetter, boolean isRussianLetters) {
        /* Uses a ternary operator to select the alphabet:
           if isRussianLetters == true, RUSSIAN_ALPHABET is selected, otherwise LATIN_ALPHABET
         */
        String alphabet = isRussianLetters ? RUSSIAN_ALPHABET : LATIN_ALPHABET;
        int alphabetSize = alphabet.length(); // The variable stores the length of the selected alphabet.
        boolean isUpper = Character.isUpperCase(ch); // Checks whether the input character 'ch' is capitalized.
        /*
        Convert the input character 'ch' to lowercase to work with lowercase letters
        (since constants contain only lowercase letters of the alphabet).
         */
        ch = Character.toLowerCase(ch);
        // Finds the index of the character 'ch' (lowercase) in the selected alphabet.
        int index = alphabet.indexOf(ch);
        /*
        Checks if the character 'ch' is found in the alphabet (index == -1 means
        the character 'ch' isn't found).
        If the character 'ch' isn't a letter of the alphabet returns it unchanged
        (e.g., spaces, numbers, punctuation marks are left as is).
         */
        if (index == -1) {
            return ch;
        }

        // Implementation of cyclic character shift with support for negative values.
        int newIndexLetter = (index + shiftLetter % alphabetSize + alphabetSize) % alphabetSize;
        char shiftResult = alphabet.charAt(newIndexLetter);
        return isUpper ? Character.toUpperCase(shiftResult) : shiftResult;
    }
}
