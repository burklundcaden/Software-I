import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Java program that asks the user what constant μ should be approximated, and
 * then asks in turn for each of the four personal numbers w, x, y, and z. The
 * program should then calculate and report the values of the exponents a, b, c,
 * and d that bring the de Jager formula as close as possible to μ, as well as
 * the value of the formula and the relative error of the approximation to the
 * nearest hundredth of one percent
 *
 * @author Caden Burklund
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.print("Enter a positive real number: ");
        String userInput = in.nextLine();
        //Continue looping while not a positive real number
        while (!FormatChecker.canParseDouble(userInput)
                || Double.parseDouble(userInput) <= 0) {
            out.print("Not a positive real number, try again: ");
            userInput = in.nextLine();
        }
        return Double.parseDouble(userInput);
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.print("Enter a positive real number: ");
        String userInput = in.nextLine();
        //Continue looping while not a positive real number or equal to one
        while (!FormatChecker.canParseDouble(userInput)
                || Double.parseDouble(userInput) <= 0
                || Double.parseDouble(userInput) == 1) {
            out.print("Not a valid input, try again: ");
            userInput = in.nextLine();
        }
        return Double.parseDouble(userInput);
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
        //Get constant from user
        out.println("What constant should be approximated? ");
        double userDouble = getPositiveDouble(in, out);
        //Get four positive integers not equal to one from the user
        out.println(
                "Enter four positive integers of your choosing (cannot be 1): ");
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        //Define exponents in the yager formula
        double[] jagerNumbers = { -5, -4, -3, -2, -1, -1.0 / 2.0, -1.0 / 3.0,
                -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1, 2, 3, 4, 5 };
        //Initialize variables
        double relativeError = Double.MAX_VALUE;
        double bestError = Double.MAX_VALUE;
        double bestEstimate = 0;
        double bestA = 0.0;
        double bestB = 0.0;
        double bestC = 0.0;
        double bestD = 0.0;
        int jagerLength = jagerNumbers.length;
        //Iterate through every combination of exponents
        for (int a = 0; a < jagerLength; a++) {
            for (int b = 0; b < jagerLength; b++) {
                for (int c = 0; c < jagerLength; c++) {
                    for (int d = 0; d < jagerLength; d++) {
                        //Calculate the estimated value
                        double tempEstimate = Math.pow(w, jagerNumbers[a])
                                * Math.pow(x, jagerNumbers[b])
                                * Math.pow(y, jagerNumbers[c])
                                * Math.pow(z, jagerNumbers[d]);
                        //Calculate the relative error
                        relativeError = Math.abs((tempEstimate - userDouble))
                                / userDouble;
                        //Store the values that lead to smallest percent error
                        if (relativeError < bestError) {
                            bestError = relativeError;
                            bestEstimate = tempEstimate;
                            bestA = jagerNumbers[a];
                            bestB = jagerNumbers[b];
                            bestC = jagerNumbers[c];
                            bestD = jagerNumbers[d];

                        }
                    }
                }
            }
        }
        //Print results
        out.println("Your estimate is " + bestEstimate);
        out.print("Your relative error is ");
        out.println(bestError, 2, false);
        out.println("Your A is " + bestA);
        out.println("Your B is " + bestB);
        out.println("Your C is " + bestC);
        out.println("Your D is " + bestD);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
