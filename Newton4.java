import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/*
 * Calculate the square root of a number
 *
 * @author Caden Burklund
 *
 */
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /*
     * Computes estimate of square root of x to within relative error given by
     * the user
     *
     * @param x positive number to compute square root of
     *
     * @param e percentage or error (epsilon)
     *
     * @return estimate of square root
     */
    private static double sqrt(double x, double e) {
        //Create an initial guess of r and assign it to x
        double r = x;
        //Avoid divide by zero
        if (x != 0) {
            //Expression to find square root until accurate to some percent error
            while (Math.abs((r * r) - x) / x > (e * e)) {
                r = (r + x / r) / 2;
            }
        }
        //Return the estimated square root
        return r;
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        out.print("What is the value of epsilon? ");
        double epsilon = in.nextDouble();
        //Prompt user the number they'd like to calculate the square root of
        out.print("What number would you like to calculate? ");
        double x = in.nextDouble();
        //Only calculate if positive integer, else stop program
        while (x > 0) {
            //Call square root function
            double sqaureRoot = sqrt(x, epsilon);
            out.println("The square root is " + sqaureRoot);
            //Re-prompt user
            out.print("What number would you like to calculate? ");
            x = in.nextDouble();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
