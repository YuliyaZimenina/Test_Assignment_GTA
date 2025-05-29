/**
 * The CeaserCipher class implements the logic for encrypting
 * and decrypting text using the Ceaser Cipher.
 */
public class CaesarCipher {
    /**
     * A method for encrypting text with a given character shift.
     * @param text - text to encrypt
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
    public String textDecrypt(String text, int shift){
        // To decrypt the text, we use the method .textEncrypt with reverse shift.
        return textEncrypt(text, -shift);
    }

    // A method for decrypting text by trying all possible shifts.
    public String[] decryptWithoutShift(String text){
        /*
        Creating a string array to store the decryption result.
        The number 33 is chosen because the Russian alphabet contains 33 letters,
        and it is assumed that the text can be encrypted with any shift from 1 to 33.
         */
        String [] decryptionResult = new String[33];
        /*
        The loop iterates through shift values from 1 to 33 inclusive.
        Each shift will be used to try to decrypt the text.
         */
        for (int shift = 1; shift <= 33 ; shift++) {
            // Saving the results of text decoding to an array.
            decryptionResult[shift - 1] = textDecrypt(text, shift);
        }
        // Return an array decryptionResult containing all text decoding options for shifts from 1 to 33.
        return  decryptionResult;
    }
}
