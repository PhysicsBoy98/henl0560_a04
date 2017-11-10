package cp213;

/**
 * The individual node of a linked structure that stores <code>T</code> objects.
 * This is a singly linked node. The node link can be updated, but not the node
 * data, in order to avoid moving data between nodes. Data structures must be
 * reordered by moving nodes.
 *
 * @author David Brown
 * @version 2017-10-23
 */
public final class Node<T> {

    // The T data.
    private T data = null;
    // Link to the next Node.
    private Node<T> next = null;

    /**
     * Creates a new node with data and link to next node. Not copy safe as it
     * accepts a reference to the data rather than a copy of the data.
     *
     * @param data
     *            the data to store in the node.
     * @param next
     *            the next node to link to.
     */
    public Node(final T data, final Node<T> next) {
	this.data = data;
	this.next = next;
    }

    /**
     * Returns the node data. Not copy safe as it returns a reference to the
     * data, not a copy of the data.
     *
     * @return The data portion of the node.
     */
    public final T getData() {
	return this.data;
    }

    /**
     * Returns the next node in the linked structure.
     *
     * @return The node that follows this node.
     */
    public final Node<T> getNext() {
	return this.next;
    }

    /**
     * Links this node to the next node.
     *
     * @param next
     *            The new node to link to.
     */
    public final void setNext(final Node<T> next) {
	this.next = next;
    }
}
