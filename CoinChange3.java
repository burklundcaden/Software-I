import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/*
 * Program to calculate the least amount of coins needed to create change for
 * the user, given the total amount of cents
 *
 * @author Caden Burklund
 *
 */
public final class CoinChange3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange3() {
    }

    private static int[] coinCountsToMakeChange(int numCents, int[] coinDenom) {
        int denomLength = coinDenom.length;
        //Create integer array to store the amount of each coin type
        int[] coinCount = new int[denomLength];
        /*
         * Loop through each index of coinDenominations and store the amount of
         * each type of coin in corresponding coinCount index
         */
        for (int i = 0; i < denomLength; i++) {
            coinCount[i] = numCents / coinDenom[i];
            //Update remaining user amount
            numCents = numCents % coinDenom[i];
        }
        return coinCount;
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
        //Prompt user for the initial amount of change
        out.print("What amount (in cents) would you like change for? ");
        int userChange = Integer.parseInt(in.nextLine());
        //Array for each type of coin
        int[] coinDenomination = { 100, 60, 1 };
        //Call function that returns array of coins
        int[] coinCount = coinCountsToMakeChange(userChange, coinDenomination);

        //Corresponding array of coin names
        String[] coinNames = { "100(s)", "60(s)", "1(s)" };
        /*
         * Loop through each index of coinCount and print corresponding
         * denomination
         */
        for (int i = 0; i < coinCount.length; i++) {
            out.println(coinNames[i] + ": " + coinCount[i]);
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
