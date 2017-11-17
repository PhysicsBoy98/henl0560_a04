package cp213;

import java.lang.reflect.Array;

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
	protected TreeNode<T> root = null;
	protected int size = 0;
	private int comparisons = 0;

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
			if (root.getLeft() != null) {
				contains = this.containsAux(root.getLeft(), key);
			}
			if (contains == false && root.getRight() != null) {
				contains = this.containsAux(root.getRight(), key);
			}
		}
		return contains;
	}

	protected boolean containsAux(final TreeNode<T> n, final T key) {
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
		boolean bool;
		if (root == null) {
			bool = true;
		} else {
			bool = false;
		}
		return bool;

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

		return bool;
	}

	/**
	 * Inserts data into this BST.
	 * 
	 * @param data
	 *            Data to store.
	 */
	public void insert(final T data) {
		if (this.root == null) {
			this.root = new TreeNode<T>(data);
			this.size++;
		} else {
			insert_aux(data, root);
		}
	}

	protected void insert_aux(final T data, TreeNode<T> parent) {
		int comp = parent.getData().compareTo(data);
		if (comp > 0) {
			if (parent.getLeft() != null) {
				insert_aux(data, parent.getLeft());
			} else {
				parent.setLeft(new TreeNode<T>(data));
				this.size++;
			}
		} else if (comp < 0) {
			if (parent.getRight() != null) {
				insert_aux(data, parent.getRight());
			} else {
				parent.setRight(new TreeNode<T>(data));
				this.size++;
			}
		} else {
			parent.incrementCount();
		}
		parent.updateHeight();
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
		TreeNode<T> node = root;
		DataCountPair<T> ret = null;
		while (node != null && ret == null) {
			if (key.compareTo(node.getData()) < 0) {
				node = node.getLeft();
			} else if (key.compareTo(node.getData()) > 0) {
				node = node.getRight();
			} else if (key.compareTo(node.getData()) == 0) {
				ret = new DataCountPair<T>(node.getData(), node.getCount());
			}
			comparisons++;
		}
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
			// edit this line

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
		boolean valid = valid_aux(root);
		return valid;
	}

	public boolean valid_aux(TreeNode<T> node) {
		boolean valid_left = true;
		boolean valid_right = true;
		boolean valid;
		if (node != null) {
			if (node.getLeft() != null) {
				if (node.getLeft().getData().compareTo(node.getData()) < 0) {
					valid_left = valid_aux(node.getLeft());
				} else {
					valid_left = false;
				}
			} else if (node.getRight() != null) {
				if (node.getRight().getData().compareTo(node.getData()) > 0) {
					valid_right = valid_aux(node.getRight());
				} else {
					valid_right = false;
				}
			}
		}
		if (valid_right == false) {
			valid = false;
		} else if (valid_left == false) {
			valid = false;
		} else {
			valid = true;
		}
		return valid;
	}

	public TreeNode<T> rightRotation(TreeNode<T> root) {
		TreeNode<T> pivot = root.getLeft();
		root.setLeft(pivot.getRight());
		root.updateHeight();
		pivot.setRight(root);
		return pivot;
	}

	public TreeNode<T> leftRotation(TreeNode<T> root) {
		TreeNode<T> pivot = root.getRight();
		root.setRight(pivot.getLeft());
		root.updateHeight();
		pivot.setLeft(root);
		return pivot;
	}

	/**
	 * Get number of comparisons executed by the {@code retrieve} method.
	 *
	 * @return comparisons
	 */
	public int getComparisons() {
		return comparisons;
	}

	/**
	 * Resets the comparison count to 0.
	 */
	public void resetComparisons() {
		comparisons = 0;
	}

}
