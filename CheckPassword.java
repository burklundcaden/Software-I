import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Checks whether the user's password is strong based on a series of criteria.
 *
 * @author Caden Burklund
 *
 */
public final class CheckPassword {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate,
            SimpleWriter out) {
        //Variable to store the amount of requirements not met
        int strengthCheck = 0;
        //Check for sufficient length
        if (passwordCandidate.length() < 8) {
            out.println("Password should be at least 8 characters!");
        }
        //Check for upper case
        if (!containsUpperCaseLetter(passwordCandidate)) {
            out.println("Password should contain an uppercase letter!");
            //Increment variable if password fails a strength check
            strengthCheck += 1;
        }
        //Check for lower case
        if (!containsLowerCaseLetter(passwordCandidate)) {
            out.println("Password should contain a lowercase letter!");
            //Increment variable if password fails a strength check
            strengthCheck += 1;
        }
        //Check for digit
        if (!containsDigit(passwordCandidate)) {
            out.println("Password should contain a digit!");
            //Increment variable if password fails a strength check
            strengthCheck += 1;
        }
        //Check for special character
        if (!containsSpecialChar(passwordCandidate)) {
            out.println("Password should contain a special character!");
            //Increment variable if password fails a strength check
            strengthCheck += 1;
        }
        //If doesn't meet at least 3/4 of the requirements then reject
        if (strengthCheck > 1) {
            out.println("This password is very weak!");
        }
        //If password has all requirements
        if (strengthCheck == 0 && passwordCandidate.length() >= 8) {
            out.println("This password is strong!");
        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        //Initialize boolean
        boolean upperCase = false;
        //Store length string so you don't have to recall it in the loop
        int length = str.length();
        int i = 0;
        //Increment through all of the indices until an upper case is found
        while (i < length && !upperCase) {
            upperCase = Character.isUpperCase(str.charAt(i));
            i++;
        }
        return upperCase;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        //Initialize boolean
        boolean lowerCase = false;
        //Store length string so you don't have to recall it in the loop
        int length = str.length();
        int i = 0;
        //Increment through all of the indices until a lower case is found
        while (i < length && !lowerCase) {
            lowerCase = Character.isLowerCase(str.charAt(i));
            i++;
        }
        return lowerCase;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param str
     *            the String to check
     * @return true if str contains a digit, false otherwise
     */
    private static boolean containsDigit(String str) {
        //Initialize boolean
        boolean digit = false;
        //Store length string so you don't have to recall it in the loop
        int length = str.length();
        int i = 0;
        //Increment through all of the indices until a digit is found
        while (i < length && !digit) {
            digit = Character.isDigit(str.charAt(i));
            i++;
        }
        return digit;
    }

    /**
     * Checks if the given String contains a special character.
     *
     * @param str
     *            the String to check
     * @return true if str contains a special character, false otherwise
     */
    private static boolean containsSpecialChar(String str) {
        //Store all valid special characters
        String specialChar = "!@#$%^&*()_-+={}[]:;,.?";
        int specialLength = specialChar.length();
        //Initialize boolean
        boolean special = false;
        //Store length string so you don't have to recall it in the loop
        int length = str.length();
        //Increment through all of the indices until a special character is found
        int i = 0;
        while (i < length && !special) {
            int j = 0;
            while (j < specialLength && !special) {
                special = (str.charAt(i) == specialChar.charAt(j));
                j++;
            }
            i++;
        }
        return special;
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
        //Prompt user for initial password
        out.print("Enter your potential password for review: ");
        String userPassword = in.nextLine();
        //While password is not an empty string re-loop
        while (userPassword.length() > 0) {
            //Call method
            checkPassword(userPassword, out);
            //Re-prompt user
            out.print("\nEnter your potential password for review: ");
            userPassword = in.nextLine();
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
