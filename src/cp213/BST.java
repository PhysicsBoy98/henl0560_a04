package cp213;

import java.lang.reflect.Array;
import java.util.Queue;

/**
 * Implements a Binary Search Tree.
 *
 * @author your name here
 * @version 2017-11-02
 *
 * @param <T>
 *            The data to store in the tree.
 */
public class BST<T extends Comparable<T>> {
	// Attributes.
	// comment add
	protected TreeNode<T> root = null;
	protected int size = 0;

	/**
	 * Determines if this BST contains key.
	 *
	 * @param key
	 *            The key to search for.
	 * @return true if this BST contains key, false otherwise.
	 */
	public boolean contains(final T key) {
		boolean contains = false;
		if (root.getData().equals(key)) {
			contains = true;
		} else {
			contains = this.containsAux(root.getLeft(), key);
			if (contains == false) {
				contains = this.containsAux(root.getRight(), key);
			}
		}
		return contains;
	}

	public boolean containsAux(final TreeNode<T> n, final T key) {
		boolean contains = false;
		if (n.getData().equals(key)) {
			return true;
		} else if (n.getLeft() == null && n.getRight() == null) {
			return false;
		} else {
			if (n.getLeft() != null) {
				contains = this.containsAux(n.getLeft(), key);
			}
			if (n.getRight() != null && contains == false) {
				contains = this.containsAux(n.getRight(), key);
			}
		}
		return contains;
	}

	/**
	 * Determines if this BST is empty.
	 *
	 * @return true if this BST is empty, false otherwise.
	 */
	public boolean empty() {

		if (root.getHeight() == 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns the height of the root node of this BST.
	 *
	 * @return height of root node, 0 if the root node is null.
	 */
	public int getHeight() {

		int high = root.getHeight();

		return high;

	}

	/**
	 * TESTING ONLY. Returns the root node of this BST. The tree nodes should not
	 * normally be visible outside the tree. Required for the {@code DrawTree}
	 * class.
	 *
	 * @return the root node.
	 */
	public TreeNode<T> getRoot() {
		return this.root;
	}

	/**
	 * Returns the number of nodes in the BST.
	 *
	 * @return size of this BST.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Determines whether two BSTs are identical.
	 *
	 * @param that
	 *            The BST to compare this BST against.
	 * @return true if this BST and that BST contain nodes that match in position,
	 *         value, count, and height, false otherwise.
	 */

	public boolean equals(final BST<T> that) {
		if (this.equals(that)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Inserts data into this BST.
	 * 
	 * @param data
	 *            Data to store.
	 */
	public void insert(final T data) {
		if (data.compareTo(this.root.getData()) < 0) {
			insert_aux(data, this.root.getLeft());
		}
		if (data.compareTo(this.root.getData()) < 0) {
			insert_aux(data, this.root.getLeft());
		}

	}

	public void insert_aux(final T data, TreeNode<T> node) {

	}

	/**
	 * Retrieves the data matching key and its count in this BST.
	 *
	 * @param key
	 *            The key to look for.
	 * @return A DataCountPair object that contains the data that matches key and
	 *         its count, null otherwise.
	 */
	public DataCountPair<T> retrieve(final T key) {
		DataCountPair<T> ret = null;

		return ret;
	}

	/**
	 * TESTING ONLY. Returns an array of data from a linked data structure. Not
	 * thread safe as it assumes contents of data structure are not changed by an
	 * external thread during the copy loop. If data elements are added or removed
	 * by an external thread while the data is being copied to the array, then the
	 * declared array size may no longer be valid. The array contents are in level
	 * order.
	 *
	 * @return an array of {@code DataCountPair} objects containing data of type
	 *         {@code T}. Returns null if the data structure is empty.
	 */
	@SuppressWarnings("unchecked")
	public final DataCountPair<T>[] toArray() {
		DataCountPair<T>[] array = null;

		if (this.root != null) {
			// Create an array of tree nodes based upon the class of an empty
			// DataCountPair object.
			array = (DataCountPair<T>[]) Array.newInstance(new DataCountPair<>().getClass(), this.size);
			int i = 0;
			TreeNode<T> node = null;

			final Queue<TreeNode<T>> nodes = new Queue<>();
			nodes.insert(this.root);

			while (!nodes.isEmpty()) {
				node = nodes.remove();
				array[i++] = node.getDataCountPair();

				if (node.getLeft() != null) {
					nodes.insert(node.getLeft());
				}
				if (node.getRight() != null) {
					nodes.insert(node.getRight());
				}
			}
		}
		return array;
	}

	/**
	 * Determines if this BST is a valid BST; i.e. a node's left child data is
	 * smaller than its data, and its right child data is greater than its data. The
	 * height of a node is equal to the maximum of the heights of its two children
	 * (missing child nodes have a height of 0), plus 1.
	 *
	 * @return true if this BST is a valid BST, false otherwise.
	 */
	public boolean valid() {

		boolean valid = true;

		if (root.getLeft().getData().compareTo(root.getData()) > 0 && root.getHeight() <= root.getLeft().getHeight()
				&& root.getHeight() <= root.getRight().getHeight()) {
			valid = false;

		} else if (root.getRight().getData().compareTo(root.getData()) < 0
				&& root.getHeight() <= root.getLeft().getHeight() && root.getHeight() <= root.getRight().getHeight()) {
			valid = false;
		} else {
			valid = valid_aux(root.getLeft());
			if (valid == true) {
				valid = valid_aux(root.getRight());
			}
		}
		return valid;

	}

	public boolean valid_aux(TreeNode<T> node) {
		boolean bool = true;
		if (node.getLeft().getData().compareTo(node.getData()) > 0) {
			bool = false;
		} else if (node.getRight().getData().compareTo(node.getData()) < 0) {
			bool = false;
		} else {
			if (node.getLeft() != null) {
				bool = valid_aux(node.getLeft());
			}
			if (node.getRight() != null) {
				bool = valid_aux(node.getRight());
			}
		}

		return bool;
	}

}
