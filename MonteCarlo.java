import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        //Initialize variable
        boolean isPointInCircle = (xCoord - 1) * (xCoord - 1)
                + (yCoord - 1) * (yCoord - 1) < 1;
        //Return boolean
        return isPointInCircle;
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        /*
         * Declare counters and initialize them
         */
        int ptsInSquare = 0, ptsInCircle = 0;
        /*
         * Create pseudo-random number generator
         */
        Random rnd = new Random1L();
        /*
         * Generate points and count how many fall in [0.0,2.0) interval
         */
        while (ptsInSquare < n) {
            /*
             * Generate two pseudo-random numbers in [0.0,2.0) interval
             */
            double x = 2 * rnd.nextDouble();
            double y = 2 * rnd.nextDouble();
            /*
             * Increment total number of generated points
             */
            ptsInSquare++;
            /*
             * Check if point is in within the circles radius interval and
             * increment counter if it is
             */
            if (pointIsInCircle(x, y)) {
                ptsInCircle++;
            }
        }
        return ptsInCircle;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        //Call function to calculate number of points in circle
        int numPointsInCircle = numberOfPointsInCircle(n);
        /*
         * Estimate percentage of points generated in circle with radius 1,
         * inside of 2 dimensional square
         */
        double estimate = (4.0 * numPointsInCircle) / n;
        output.println("Area of Circle Estimate: " + estimate);
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}