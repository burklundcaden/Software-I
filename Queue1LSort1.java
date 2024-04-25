import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class Queue1LSort1 extends Queue1L<String> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort1() {
        super();
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

        Queue<String> temp = new Queue1L<>();
        String min = "";
        while (q.length() > 0) {
            String element = q.dequeue();
            if (order.compare(element, min) < 0 || min.isEmpty()) {
                if (!min.isEmpty()) {
                    temp.enqueue(min);
                }
                min = element;
            } else {
                temp.enqueue(element);
            }
        }
        q.transferFrom(temp);
        return min;
    }

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";
        Queue<String> temp = new Queue1L<>();
        while (this.length() > 0) {
            temp.enqueue(removeMin(this, order));
        }
        this.transferFrom(temp);

    }

}