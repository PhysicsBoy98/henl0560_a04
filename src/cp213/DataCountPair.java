package cp213;

/**
 * Stores a combination of data and its count as stored in a BST. Objects of
 * this type are not stored in the tree as such, rather data can be returned
 * from the tree in this form as the result of a {@code retrieve} method. Data
 * and its count are stored as separate fields in a {@code TreeNode}. Requires
 * only getters as objects of this type provide information only, and should not
 * allow updating.
 * <p>
 * The {@code DataCountPair} objects themselves are comparable, based upon their
 * data and the count of that data.
 *
 * @author David Brown
 * @version 2017-11-02
 */
public class DataCountPair<T extends Comparable<T>> implements Comparable<DataCountPair<T>> {

	// Attributes.
	private T data = null; // TreeNode data
	private int count = 0; // TreeNode count for data.

	/**
	 * Empty constructor - required by tree {@code toArray} methods.
	 */
	public DataCountPair() {
	}

	/**
	 * Constructor.
	 *
	 * @param data
	 *            The data to count.
	 * @param count
	 *            The count of data.
	 */
	public DataCountPair(final T data, final int count) {
		this.data = data;
		this.count = count;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 *
	 * Compares first by data, then by count.
	 */
	@Override
	public int compareTo(final DataCountPair<T> that) {
		int result = this.data.compareTo(that.data);

		if (result == 0) {
			result = Integer.compare(this.count, that.count);
		}
		return result;
	}

	/**
	 * Returns this data count.
	 *
	 * @return count.
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * Returns this data.
	 *
	 * @return data.
	 */
	public T getData() {
		return this.data;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.data.toString() + ": " + this.count;
	}
}
