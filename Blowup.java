// Import
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
* This is the public class.
*
* @author Adrijan
* @version 1.0
* @since   2023-04-10
*/

public class Blowup {

    /**
     * This is a private constructor used to satisfy the style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private Blowup() {
        throw new IllegalStateException("Utility class.");
    }

    /**
    * This is the MaxRun method.
    *
    * @param str2 //
    * @return MaxRunLength
    */
    public static int MaxRun(String str2) {

        // In case of an empty string
        if (str2.length() == 0) {
            return 0;
        }

        // Set the max run length and the current run length
        int maxRunLength = 1;
        int currentRunLength = 1;

        // Go through each character in the string starting from the second one
        for (int counter = 1; counter < str2.length(); counter++) {

            // If the current character is the same as the previous one
            // add one to the current run length
            if (str2.charAt(counter) == str2.charAt(counter - 1)) {
                currentRunLength++;
            } else {

                // update the max run length and reset the current run length
                if (currentRunLength > maxRunLength) {
                    maxRunLength = currentRunLength;
                }
                currentRunLength = 1;
            }
        }

        // Update the maximum run length one last time (in case the string ended with a run)
        if (currentRunLength > maxRunLength) {
            maxRunLength = currentRunLength;
        }

        // Return the maximum run length
        return maxRunLength;
    }


    /**
    * This is the blowup method.
    *
    * @param str //
    * @return result
    */
    public static String Blowup(String str) {

        // create an array to store the result
        // (maximum length is 10 times the input string length)
        char[] result = new char[str.length() * 10];

        // initialize an index variable to keep
        // track of the current position in the result array
        int index = 0;
        
        // Go over each character in the input string.
        for (int counter1 = 0; counter1 < str.length(); counter1++) {

            // get the current character
            char character = str.charAt(counter1);

            // Check if the current character is a digit.
            if (character >= '0' && character <= '9') {

                // Check if the digit is followed by another character.
                if (counter1 + 1 < str.length()) {

                    // Get the next character.
                    char nextChar = str.charAt(counter1 + 1);

                    // Get the repeat count.
                    int repeatCount = character - '0';

                    // Repeat the next character as many times as the digit value.
                    for (int counter2 = 0; counter2 < repeatCount; counter2++) {

                        // Store the next character in the result array.
                        result[index] = nextChar;

                        // Add one to the index.
                        index++;
                    }
                }
            } else {

                // If the current character is not a digit
                // store it in the result array.
                result[index] = character;

                // Add one to the index.
                index++;
            }
        }

        // Convert the result array to a String and return it.
        return new String(result, 0, index);
    }

    /**
    * This is the main method.
    *
    * @param args //
    * @throws Exception //
    */
    public static void main(String[] args) throws IOException {
    
        // File path is passed as parameter.
        final File inputFile = new File("input.txt");
        final File outputFile = new File("output.txt");
        final Scanner sc = new Scanner(inputFile);
        final FileWriter output = new FileWriter(outputFile);

        String str = sc.nextLine();
        String str2 = sc.nextLine();

        // Call the blowup function with the input string.
        final String blowupResult = Blowup(str);
        final int maxRunResult = MaxRun(str2);

        // Write the output strings to the output file, separated by a newline character.
        output.write(blowupResult + "\n");
        output.write(Integer.toString(maxRunResult));

        // Close the output file.
        output.close();
    }
}
