package cp213;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The abstract base class for singly-linked data structures. Provides
 * attributes and implementations for getSize, peek, isEmpty, toArray, and
 * iterator methods. The <code>head</code> attribute is the first node in any
 * singly-linked list.
 *
 * @author David Brown
 * @version 2017-10-23
 * @param <T>
 *            data type for base data structure.
 */
public abstract class SingleLink<T> implements Iterable<T> {

    /**
     * Creates an Iterator for the outer class. An iterator allows a program to
     * walk through the data in a data structure by using the hasNext and next
     * methods. Typical code:
     *
     * <pre>
    Iterator&lt;T&gt; iter = dataStructureObject.iterator();

    while(iter.hasNext()){
        T data = iter.next();
        ...
    }
     * </pre>
     *
     * It also allows the user of the enhanced for loop:
     *
     * <pre>
    for(T data : dataStructureObject){
        ...
    }
     * </pre>
     *
     * (Replace T with a concrete class such as String or Integer.)
     */
    private class SingleLinkIterator implements Iterator<T> {
	// current is initialized to beginning of linked list.
	private Node<T> current = SingleLink.this.head;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
	    return this.current != null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
	    T result = null;

	    if (this.current == null) {
		throw new NoSuchElementException();
	    } else {
		result = this.current.getData();
		this.current = this.current.getNext();
	    }
	    return result;
	}
    }

    // First node of linked list.
    protected Node<T> head = null;
    // Number of elements currently stored in linked list.
    protected int size = 0;

    /**
     * Adds data to linked structure in order.
     * 
     * @param array
     *            Data to add.
     */
    public void addAllFront(final T[] array) {

	for (final T element : array) {
	    this.head = new Node<>(element, this.head);
	}
	return;
    }

    /**
     * Adds data to linked structure in reverse order.
     * 
     * @param array
     *            Data to add.
     */
    public void addAllRear(final T[] array) {

	for (int i = array.length - 1; i >= 0; i--) {
	    this.head = new Node<>(array[i], this.head);
	}
	return;
    }

    /**
     * Returns the current number of elements in the linked structure.
     *
     * @return the value of size.
     */
    public final int getSize() {
	return this.size;
    }

    /**
     * Determines whether the linked data structure is empty or not.
     *
     * @return true if data structure is empty, false otherwise.
     */
    public final boolean isEmpty() {
	return this.head == null;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public final Iterator<T> iterator() {
	return new SingleLinkIterator();
    }

    /**
     * Returns a reference to the first data of the linked structure without
     * removing that data from the structure.
     *
     * @return The data in the head of the structure.
     */
    public final T peek() {
	return this.head.getData();
    }

    /**
     * Returns an array of data from a linked data structure. Not thread safe as
     * it assumes contents of data structure are not changed by an external
     * thread during the copy loop. If data elements are added or removed by an
     * external thread while the data is being copied to the array, then the
     * declared array size may no longer be valid.
     *
     * @return an array of data of type T. Returns null if the data structure is
     *         empty.
     */
    @SuppressWarnings("unchecked")
    public final T[] toArray() {
	T[] a = null;

	if (this.head != null) {
	    // Create an array of data based upon the class of the head data.
	    a = (T[]) Array.newInstance(this.head.getData().getClass(),
		    this.size);
	    final Iterator<T> iter = this.iterator();

	    for (int i = 0; i < this.size; i++) {
		a[i] = iter.next();
	    }
	}
	return a;
    }
}
