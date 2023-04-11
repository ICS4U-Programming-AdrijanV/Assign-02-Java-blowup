// Import
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
* This is the public class.
*
* @author Adrijan
* @version 1.0
* @since   2023-04-10
*/

public final class Blowup {

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
    public static int maxRun(String str2) {

        // In case of an empty string
        if (str2.length() == 0) {
            return 0;
        }

        // Convert the string to an array of characters
        char[] charArray = new char[str2.length()];
        for (int counter = 0; counter < str2.length(); counter++) {
            charArray[counter] = str2.charAt(counter);
        }

        // Set the max run length and the current run length
        int maxRunLength = 1;
        int currentRunLength = 1;

        // Go through each character in the string starting from the second one
        for (int counter = 1; counter < charArray.length; counter++) {

            // If the current character is the same as the previous one
            // add one to the current run length
            if (charArray[counter] == charArray[counter - 1]) {
                currentRunLength++;
            } else {

                // update the max run length and reset the current run length
                if (currentRunLength > maxRunLength) {
                    maxRunLength = currentRunLength;
                }
                currentRunLength = 1;
            }
        }

        // Update the max run length one last time
        // in case the string ended with a run
        if (currentRunLength > maxRunLength) {
            maxRunLength = currentRunLength;
        }

        // Return the max run length
        return maxRunLength;
    }

    /**
    * This is the blowup method.
    *
    * @param str //
    * @return result
    */
    public static String blowup(String str) {
        // Check if string is empty
        if (str.length() == 0) {
            return "";
        }

        // create an array to store the result
        final char[] result = new char[str.length() * 10];

        // initialize an index variable to keep
        int index = 0;

        // Go over each character in the input string.
        for (int counter1 = 0; counter1 < str.length(); counter1++) {

            // get the current character
            final char character = str.charAt(counter1);

            // Check if the current character is a digit.
            if (character >= '0' && character <= '9') {

                // Check if the digit is followed by another character.
                if (counter1 + 1 < str.length()) {

                    // Get the next character.
                    final char nextChar = str.charAt(counter1 + 1);

                    // Get the repeat count.
                    final int repeatCount = character - '0';

                    // Repeat the next character as many
                    // times as the number value.
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
    * This is the Shrink method.
    *
    * @param str3 //
    * @return outputArray
    */
    public static String shrink(String str3) {

        // Check if string is empty
        if (str3.length() == 0) {
            return "";
        }

        // Set an output array for the final string
        final char[] outputArray = new char[str3.length() * 2];

        // Set count and index
        int count = 1;
        int index = 0;

        // Go through the characters of the string
        // But start from the second character
        for (int counter = 1; counter < str3.length(); counter++) {

            // Get the current character
            final char current = str3.charAt(counter);

            // If the current character is the same as the previous character
            // add to the count.
            if (current == str3.charAt(counter - 1)) {
                count++;

            } else {

                // If the current character is different
                // than the character before it
                // Store the count and previous character in the output array
                outputArray[index++] = (char) ('0' + count);
                outputArray[index++] = str3.charAt(counter - 1);

                // Reset the count to 1
                count = 1;
            }
        }

        // Store the final count and character in the output array
        outputArray[index] = (char) ('0' + count);

        // Add to the index
        index++;

        outputArray[index] = str3.charAt(str3.length() - 1);

        // Add to the index
        index++;

        // Create a new string using the output array.
        return new String(outputArray, 0, index);
    }

    /**
    * This is the main method.
    *
    * @param args //
    * @throws Exception //
    */
    public static void main(String[] args) throws Exception {

        // File path is passed as parameter.
        final File inputFile = new File("input.txt");
        final File outputFile = new File("output.txt");
        final Scanner sc = new Scanner(inputFile);
        final FileWriter output = new FileWriter(outputFile);

        final String str = sc.nextLine();
        final String str2 = sc.nextLine();
        final String str3 = sc.nextLine();

        // Call the functions and assign to a variable.
        final String blowupResult = blowup(str);
        final int maxRunResult = maxRun(str2);
        final String shrinkResult = shrink(str3);

        // Write the strings to the output file
        output.write(blowupResult + "\n");
        output.write(Integer.toString(maxRunResult) + "\n");
        output.write(shrinkResult);

        // Close the output file.
        output.close();
    }
}
