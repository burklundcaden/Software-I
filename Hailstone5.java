import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone5() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        //Initialize variables
        int lengthSeries = 1;
        int maxValue = n;
        out.print(n + " ");
        //Whilst series is not yet converged
        while (n != 1) {
            //If even then divide by 2
            if (n % 2 == 0) {
                n = n / 2;
                //If odd then multiply by 3 and add 1
            } else {
                n = 3 * n + 1;
            }
            //Display values
            out.print(n + " ");
            //Increment length after each run
            lengthSeries += 1;
            //Store the max value by comparing it the existing max value
            if (n > maxValue) {
                maxValue = n;
            }
        }
        //Display data
        out.print("\nLength of Series: " + lengthSeries);
        out.print("\nMax Value: " + maxValue);
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        //Initialize variable w/ -1 to enter the while loop
        int userInt = -1;
        //Get user input
        out.print("Enter a positive integer with a value greater than 0: ");
        String userString = in.nextLine();
        //Check if integer
        boolean isPosInt = FormatChecker.canParseInt(userString);
        //If not a positive integer then run loop
        while (isPosInt == false || userInt <= 0) {
            //Re-prompt user
            out.print("Not the correct input type!\n");
            out.print("Enter a positive integer with a value greater than 0: ");
            userString = in.nextLine();
            isPosInt = FormatChecker.canParseInt(userString);
            userInt = Integer.parseInt(userString);
        }
        //Return correct input type
        return userInt;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //Get user's starting value by calling function
        int userInt = getPositiveInteger(in, out);
        //Generate and print the series, length of series, and max value
        generateSeries(userInt, out);
        //Prompt the user to run again again
        out.print("\nIf you would like to run again, type [y]: ");
        String runAgain = in.nextLine();
        //If user selects to run again then re-loop through the program
        while (runAgain.charAt(0) == 'y') {
            userInt = getPositiveInteger(in, out);
            generateSeries(userInt, out);
            out.print("\nIf you would like to run again, type [y]: ");
            runAgain = in.nextLine();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
