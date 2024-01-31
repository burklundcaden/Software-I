import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * For this lab, you will load an XMLTree object from an XML URL; then, from
 * this object, you will extract various pieces of information using the XMLTree
 * methods and you will output that information to the console. Make sure you
 * successfully and correctly complete each task before moving on to the next.
 *
 * @author Caden Burklund
 *
 */
public final class XMLTreeExploration {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int middleChild = xt.numberOfChildren() / 2;
        out.println("The label of the middle child: "
                + xt.child(middleChild).label());
        if (xt.child(middleChild).isTag()) {
            out.println("The middle child is a tag.");
            out.println("The number of the middle child's children: "
                    + xt.child(middleChild).numberOfChildren());
        } else {
            out.println("This is text");
        }
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
         * Put your main program code here
         */

        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");

        out.println(xml.toString());
        if (xml.isTag()) {
            out.println("This is a tag");
        } else {
            out.println("This is text");
        }
        out.println("The label of xml: " + xml.label());

        //Opens the xml tree display
        xml.display();

        XMLTree results = xml.child(0);
        XMLTree channel = results.child(0);
        out.println("Number of children is: " + channel.numberOfChildren());
        XMLTree title = channel.child(1);
        XMLTree titleText = title.child(0);

        out.println("The label of titleText: " + titleText.label());

        out.println("The label of titleText: "
                + xml.child(0).child(0).child(1).child(0));

        XMLTree astronomy = channel.child(10);
        out.println("Contain's attribute sunset: "
                + astronomy.hasAttribute("sunset"));
        out.println("Contain's attribute midday: "
                + astronomy.hasAttribute("midday"));
        out.println("Value of sunrise: " + astronomy.attributeValue("sunrise"));
        out.println("Value of sunset: " + astronomy.attributeValue("sunset"));

        printMiddleNode(channel, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
