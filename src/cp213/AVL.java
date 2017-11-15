package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @version 2017-11-02
 *
 * @param <T>
 *            The data to store in the tree.
 */
public class AVL<T extends Comparable<T>> extends BST<T> {
	@Override
	public void insert(final T data) {
		if (this.root == null) {
			this.root = new TreeNode<T>(data);
			this.size++;
		} else {
			insert_aux(data, root);
		}
		checkTree();
	}

	@Override
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

	protected void checkTree() {
		int leftH = 0;
		int rightH = 0;
		if (this.root.getLeft() != null) {
			leftH = this.root.getLeft().getHeight();
		}
		if (this.root.getRight() != null) {
			rightH = this.root.getRight().getHeight();
		}
		if (Math.abs(rightH - leftH) > 0) {

		}

	}
}
