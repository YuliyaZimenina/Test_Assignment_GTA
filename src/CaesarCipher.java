/**
 * The CeaserCipher class implements the logic for encrypting
 * and decrypting text using the Ceaser Cipher.
 */
public class CaesarCipher {
    // Frequency tables for Russian and English alphabets.
    public static final double[] RUSSIAN_FREQ = {
            0.0798, 0.0159, 0.0454, 0.0170, 0.0298, 0.0084, 0.0004, 0.0094, 0.0165, 0.0738,
            0.0144, 0.0339, 0.0453, 0.0262, 0.0947, 0.0540, 0.0162, 0.0670, 0.0190, 0.0262,
            0.0003, 0.0062, 0.0073, 0.0035, 0.0063, 0.0033, 0.0002, 0.0213, 0.0175, 0.0074,
            0.0020, 0.0121, 0.0180
    };
    private static final double[] ENGLISH_FREQ = {
            0.0817, 0.0149, 0.0278, 0.0425, 0.1270, 0.0223, 0.0202, 0.0609, 0.0697, 0.0015,
            0.0077, 0.0403, 0.0241, 0.0675, 0.0751, 0.0193, 0.0009, 0.0599, 0.0633, 0.0906,
            0.0276, 0.0098, 0.0236, 0.0015, 0.0197, 0.0007
    };

    /**
     * A method for encrypting text with a given character shift.
     *
     * @param text  - text to encrypt
     * @param shift - the number of positions to shift letters in the alphabet.
     * @return - returns String — encrypted text.
     */
    public String textEncrypt(String text, int shift) {
        StringBuilder encryptionResult = new StringBuilder();
        // We use a for-each loop to iterate over each 'ch' character in the array.
        for (char ch : text.toCharArray()) {
            /*
            Determining the alphabet to which the character 'ch' belongs using the
            conditional operator.
             */
            if (Alphabet.isRussianLetter(ch)) {
                encryptionResult.append(Alphabet.letterShift(ch, shift, true));
            } else if (Alphabet.isLatinLetter(ch)) {
                encryptionResult.append(Alphabet.letterShift(ch, shift, false));
            } else {
                encryptionResult.append(ch); // Non-alphabetic characters remain unchanged.
            }
        }
        return encryptionResult.toString();
    }

    // A method for decrypting text with a given character shift.
    public String textDecrypt(String text, int shift) {
        // To decrypt the text, we use the method .textEncrypt with reverse shift.
        return textEncrypt(text, -shift);
    }

    // A method for decrypting text by trying all possible shifts.
    public String[] decryptWithoutShift(String text) {
        /*
        The conditional operator checks whether the input text is null or empty.
        If the condition is met, the conditional operator returns an array with
        one string containing the error message.
         */
        if (text == null || text.isEmpty()){
            return new String[] {"Error: Input text is empty or null"};
        }

        /**
         * Alphabet detection.
         * Checks the incoming text characters for compliance with the Russian alphabet
         * using the methods:
         * - text.chars() (stream of character code points)
         * - anyMatch with Alphabet.isRussianLetter check.
          */
        boolean isRussianAlphabet = text.chars().anyMatch(ch -> Alphabet.isRussianLetter( (char)ch));
        /*
        Selecting an alphabet:
        - if isRussianAlphabet is true, the Russian alphabet is taken (Alphabet.getRussianAlphabet()).
        - otherwise — the English alphabet (Alphabet.getLatinAlphabet()).
         */
        String alphabet = isRussianAlphabet ? Alphabet.getRussianAlphabet() : Alphabet.getLatinAlphabet();
        /*
        Select an array of frequencies:
         - RUSSIAN_FREQ for the Russian alphabet.
         - ENGLISH_FREQ for English.
         */
        double[] expectedFrequency = isRussianAlphabet ? RUSSIAN_FREQ : ENGLISH_FREQ;
        /* Save the size of the selected alphabet (33 for Russian, 26 for English)
         in the 'alphabetSize' variable.
         */
        int alphabetSize = alphabet.length();

        /*
        Create an array 'decryptionResult' to store the decrypted texts for each shift
        (plus one element for the best result) and an array 'scores' to store the chi-square
        values for each shift.
         */
        String[] decryptionResult = new String[alphabetSize + 1];
        double[] scores = new double[alphabetSize + 1];

        /*
        Checking text length.
        The conditional operator outputs a warning to the console about the unreliability
        of frequency analysis results if the text length is less than 20 characters.
         */
        if (text.length() < 20){
            System.err.println("WARNING: Text is too short for accurate decryption." +
                    "Result may be unreliable");
        }

        // A loop that iterates over all possible offsets from 0 to 'alphabetSize' (inclusive).
        for (int shift = 0; shift <= alphabetSize; shift++) {
            String decrypted = textDecrypt(text, shift);
            decryptionResult[shift] = decrypted; // Saving the decrypted text.

            /*
            Create an array 'observedFrequency' to store the observed frequencies of letters in
             the decrypted text and a variable 'totalLetters' to count the total number of letters.
             */
            double [] observedFrequency = new double[alphabetSize];
            int totalLetters = 0;

            /*
            Convert the decrypted text to lowercase (toLowerCase()), then to a character array,
            and iterate over each character 'ch'.
             */
            for (char ch : decrypted.toLowerCase().toCharArray()) {
                /*
                The conditional operator checks whether the character 'ch' is in the selected alphabet.
                If the character 'ch' is present, it increases the frequency counter for this letter
                (observedFrequency[index]) and the total number of letters (totalLetters).
                 */
                if (alphabet.indexOf(ch) >= 0) {
                    int index = alphabet.indexOf(ch);
                    observedFrequency[index]++;
                    totalLetters++;
                }
            }

            /*
            If letters of the alphabet are found (totalLetters > 0), the conditional operator (if-else)
            normalizes the frequencies by dividing each 'observedFrequency[i]' by the total number
            of letters to obtain relative frequencies.
            If there are no letters (totalLetters == 0), the conditional operator (if-else) sets the
             current shift to the maximum value of 'scores[shift]' (to exclude it from the top scores)
             and moves to the next shift.
             */
            if (totalLetters > 0) {
                for (int i = 0; i < observedFrequency.length; i++) {
                    observedFrequency[i] /= totalLetters;
                }
            } else {
                scores[shift] = Double.MAX_VALUE; // Если нет букв, даем максимальный счет
                continue;
            }

            /**
             * Calculate the chi-square statistic for the current shift. For each letter:
             * Calculate the difference between the observed (observedFrequency[i]) and expected
             * (expectedFrequency[i]) frequencies.
             * The square of the difference is divided by the expected frequency (with 0.0001 added
             * to avoid division by zero).
             * The result is summed up in the variable 'chiSquare'.
             */
            double chiSquare = 0.0;
            for (int i = 0; i < alphabetSize; i++) {
                double diff = observedFrequency[i] - expectedFrequency[i];
                chiSquare += (diff * diff) / (expectedFrequency[i] + 0.0001);
            }
            scores[shift] = chiSquare; // Save the chi-square value for the current shift.
        }

        /*
        Find the shift with the minimum chi-square value indicating the most likely correct shift.
        Save the index of this shift in the variable 'bestShift' and the value in the variable
        'minChiSquare'.
         */
        int bestShift = 0;
        double minChiSquare = Double.MAX_VALUE;
        for (int i = 0; i <= alphabetSize; i++) {
            if (scores[i] < minChiSquare) {
                minChiSquare = scores[i];
                bestShift = i;
            }
        }

        /*
        If a non-zero best shift is found, then the conditional operator (if):
        - Saves the best decrypted text to the 'bestResult' variable.
        - Shifts the elements of the decryptionResult array one position to the right
        (from 0 to bestShift).
        - Places the best result in the 'decryptionResult[0]' variable.
         */
        if (bestShift > 0) {
            String bestResult = decryptionResult[bestShift];
            System.arraycopy(decryptionResult, 0, decryptionResult, 1, bestShift);
            decryptionResult[0] = bestResult;
        }

        // The method ends by returning the best decryption result.
        return decryptionResult;
    }
}