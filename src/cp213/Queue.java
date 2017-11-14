package cp213;

/**
 * A simple linked queue structure of <code>Node T</code> objects. Only the
 * <code>T</code> data contained in the queue is visible through the standard
 * stack methods. Extends the <code>SingleLink</code> class, which already
 * defines the head node, size, peek, isEmpty, and iterator.
 *
 * @author your name here
 * @author David Brown
 * @version 2017-10-06
 * @param <T>
 *            the Queue data type.
 */
public class Queue<T> extends SingleLink<T> {

	// Pointer to the rear of the queue.
	private Node<T> rear = null;

	/**
	 * Combines the contents of the left and right Queues into the current Queue.
	 * Moves nodes only - does not move data or call the high-level methods insert
	 * or remove. left and right Queues are empty when done. Nodes are moved
	 * alternately from left and right to this Queue.
	 *
	 * @param left
	 *            The first Queue to extract nodes from.
	 * @param right
	 *            The second Queue to extract nodes from.
	 */
	public void combine(final Queue<T> left, final Queue<T> right) {

		while (left.head != null && right.head != null) {
			this.moveFront(left);
			this.moveFront(right);
		}
		while (left.head != null) {
			this.moveFront(left);
		}
		while (right.head != null) {
			this.moveFront(right);
		}
		return;
	}

	/**
	 * Adds data to the rear of the queue. Increments the queue size.
	 *
	 * @param data
	 *            The data to added to the rear of the queue.
	 */
	public void insert(final T data) {
		final Node<T> node = new Node<>(data, null);
		if (this.head == null) {
			this.head = node;
		} else {
			this.rear.setNext(node);
		}
		this.rear = node;
		this.size++;
		return;
	}

	/**
	 * Returns the front data of the queue and removes that data from the queue. The
	 * next node in the queue becomes the new first node. Decrements the queue size.
	 *
	 * @return The data at the front of the queue.
	 */
	public T remove() {
		final T data = this.head.getData();
		this.head = this.head.getNext();
		this.size--;

		if (this.head == null) {
			this.rear = null;
		}
		return data;
	}

	/**
	 * Splits the contents of the current Queue into the left and right Queues.
	 * Moves nodes only - does not move data or call the high-level methods insert
	 * or remove. this Queue is empty when done. Nodes are moved alternately from
	 * this Queue to left and right.
	 *
	 * @param left
	 *            The first Queue to move nodes to.
	 * @param right
	 *            The second Queue to move nodes to.
	 */
	public void split(final Queue<T> left, final Queue<T> right) {
		int i = 0;

		while (this.head != null) {

			if (i % 2 == 0) {
				left.moveFront(this);
			} else {
				right.moveFront(this);
			}
			i++;
		}
		return;
	}

	/**
	 * Helper method to move the front node from that Queue to the rear of this
	 * Queue.
	 *
	 * @param that
	 */
	private void moveFront(final Queue<T> that) {
		// Extract the first node of that Queue.
		final Node<T> node = that.head;
		that.head = that.head.getNext();
		node.setNext(null);

		if (that.head == null) {
			that.rear = null;
		}
		// Update this Queue.
		if (this.head == null) {
			this.head = node;
		} else {
			this.rear.setNext(node);
		}
		this.rear = node;
		// Update the Queue sizes.
		this.size--;
		that.size++;
	}
}
