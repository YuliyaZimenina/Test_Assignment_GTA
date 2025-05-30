# TEST ASSIGNMENT (GTA)

## Table of Contents
1. [Description](#description)
2. [Technical Requirements](#technical-requirements)
3. [Installation and Launch](#installation-and-launch)
4. [Approach and Assumptions](#approach-and-assumptions)
5. [Implementation](#implementation)
6. [Author](#author)
7. [Usage Examples](#usage-examples)
8. [Screenshots](#screenshots)

## Description

A Java console application implementing the Caesar cipher and arithmetic 
expression calculator. 
Supports:

- Text encryption and decryption (Russian and English alphabets), taking 
into account case and non-alphabetic characters.
- Calculation of arithmetic expressions with support for:
- Operations: '+', '-', ' * ', ' / '
- Parentheses
- Decimal and negative numbers

To optimize the solution, fundamental algorithms (Caesar cipher, Shunting Yard algorithm, 
frequency analysis, and Chi-square analysis) were implemented in the application.

## Technical Requirements

- Programming language: **Java 21**
- Application type: **Console application**
- Architecture: **Clean, modular code with separate classes for each functionality**
- Error Handling: **Correct exception handling for invalid input**
- UI: **Simple console menu to choose between operations**

  ## Installation and Launch
1. Clone the repository:
   ```bash
   git clone https://github.com/YuliyaZimenina/Test_Assignment_GTA.git
   cd  Test_Assignment_GTA
   ```
2. Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, etc.)
3. Run the application (class: Main.java).
> **Note:** To read from a file, create a file `input.txt` in the root directory of the project with text in UTF-8 encoding.

## Approach and Assumptions

1. **Caesar Cipher:** Implemented using the Russian and English alphabets. Supports case preservation, non-alphabetic characters, and cyclic shifts. Decryption without a known shift utilizes frequency analysis and the chi-square test to select the best result.
2. **Expression Evaluator:** Uses the Shunting Yard algorithm to convert expressions to RPN and a stack for calculation. Supports PEMDAS, brackets, decimals, and negative numbers.
3. **Modularity:** The code is divided into classes for encryption, analysis, calculations, and input/output.
4. **Error Handling:** Handles invalid input, division by zero, file issues, and bracket mismatches.

## Implementation

1. **Caesar Cipher:**
- Supports Russian and English alphabets (Alphabet.java).
- Preserves the case of letters.
- Non-alphabetic characters remain unchanged.
- Processes cyclic shift and positive/negative shifts.
- Supports console and file input (InputHandler.java).
- Implemented decryption with and without specifying a shift.

2. **Arithmetic expression evaluator:**
- Parses and evaluates expressions taking into account PEMDAS/BODMAS (ExpressionParser.java, ExpressionEvaluator.java).
- Supports nested parentheses.
- Processes decimal and negative numbers.
- Properly handles division by zero.

## Author

[Yuliya Zimenina](https://github.com/YuliyaZimenina)

## Usage Examples

**Caesar Cipher**

**Input** ```Hello Java!```, shift: ```3``` -> **Output:** ```Khoor Mdyd!```
  
**Input** ```Привет, Джава!!```, shift: ```6``` -> **Output:** ```Хцозкш, Ймёзё!```

**Decoding the Caesar Cipher**
  
  **Input** ```Khoor Mdyd!```, shift: ```3``` -> **Output:** ```Hello Java!```

**Arithmetic Expression Evaluation**

**Input** ```7 + 9 * 2``` -> **Output:** ```25.00```
**Input** ```(4 + 10) / 2``` -> **Output:** ```7.00```
**Input** ```-23 + 13``` -> **Output:** ```-10.00```

## Screenshots

1. Application Launch:

   <img src="images/Application_start.png" alt="Application Start" width="500">

2. Caesar Cipher (English Alphabet):

   <img src="images/Caesar_cipher.png" alt="Caesar Cipher English" width="500">

3. Caesar Cipher (Russian Alphabet):

   <img src="images/Caesar_cipher_rus.png" alt="Caesar Cipher Russian" width="500">

4. Decryption (Russian Alphabet):

   <img src="images/Decrypt_rus.png" alt="Decryption Russian" width="500">

5. Decryption (English Alphabet):

   <img src="images/Decrypt_latin.png" alt="Decryption English" width="500">

6. Decryption Without Shift:

   <img src="images/Decrypt_without_shift.png" alt="Decryption Without Shift" width="500">

7. Arithmetic Expression:

   <img src="images/Arithmetic_expression.png" alt="Arithmetic Expression" width="500">




