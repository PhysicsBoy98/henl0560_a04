package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @version 2017-11-02
 *
 * @param <T>
 *            The data to store in the tree.
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {
	public void leftLeftRotation(TreeNode<T> parent) {
		TreeNode<T> root = parent.getLeft();
		TreeNode<T> pivot = root.getLeft();
		root.setLeft(pivot.getRight());
		pivot.setRight(root);
		parent.setLeft(pivot);
	}

	public void rightLeftRotation(TreeNode<T> parent) {
		TreeNode<T> root = parent.getRight();
		TreeNode<T> pivot = root.getLeft();
		root.setLeft(pivot.getRight());
		pivot.setRight(root);
		parent.setRight(pivot);
	}

	public void leftRightRotation(TreeNode<T> parent) {
		TreeNode<T> root = parent.getLeft();
		TreeNode<T> pivot = root.getRight();
		root.setRight(pivot.getLeft());
		pivot.setLeft(root);
		parent.setLeft(pivot);
	}

	public void rightRightRotation(TreeNode<T> parent) {
		TreeNode<T> root = parent.getRight();
		TreeNode<T> pivot = root.getRight();
		root.setRight(pivot.getLeft());
		pivot.setLeft(root);
		parent.setRight(pivot);
	}

	public void leftRotation() {
		TreeNode<T> root = this.root;
		TreeNode<T> pivot = root.getLeft();
		root.setLeft(pivot.getRight());
		pivot.setRight(root);
		this.root = pivot;
	}

	public void rightRotation() {
		TreeNode<T> root = this.root;
		TreeNode<T> pivot = root.getRight();
		root.setRight(pivot.getLeft());
		pivot.setLeft(root);
		this.root = pivot;
	}

	@Override
	public void insert(final T data) {
		if (this.root == null) {
			this.root = new TreeNode<T>(data);
			this.size++;
		} else {
			insert_aux(data, root);
		}
	}

	@Override
	public void insert_aux(final T data, TreeNode<T> parent) {
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
}
