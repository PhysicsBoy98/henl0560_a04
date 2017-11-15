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
	@Override
	public void insert(final T data) {
		if (this.root == null) {
			this.root = new TreeNode<T>(data);
			this.size++;
		} else if (this.contains(data) == false) {
			insert_aux(data, root);
		} else {
			checkTree();
		}
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
		}
		parent.updateHeight();
	}

	@Override
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

	@Override
	protected boolean containsAux(final TreeNode<T> n, final T key) {
		boolean contains = false;
		if (n.getData().equals(key)) {
			n.incrementCount();
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

	private void checkTree() {
		if (this.root.getLeft() != null) {
			if (this.root.getCount() < this.root.getLeft().getCount()) {
				TreeNode<T> pivot = leftRotation(this.root);
				this.root = pivot;
				root.updateHeight();
			}
		}
		if (this.root.getRight() != null) {
			if (this.root.getCount() < this.root.getRight().getCount()) {
				TreeNode<T> pivot = rightRotation(this.root);
				this.root = pivot;
				root.updateHeight();
			}
		}
		checkTree_aux(root);
	}

	private void checkTree_aux(TreeNode<T> parent) {
		TreeNode<T> left = parent.getLeft();
		TreeNode<T> right = parent.getRight();
		if (left != null) {
			if (left.getLeft() != null && left.getCount() < left.getLeft().getCount()) {
				leftLeftRotation(parent);
			} else if (left.getRight() != null && left.getCount() < left.getRight().getCount()) {
				leftRightRotation(parent);
			} else {
				checkTree_aux(left);
			}
		}
		if (right != null) {
			if (right.getLeft() != null && right.getCount() < right.getLeft().getCount()) {
				rightLeftRotation(parent);
			} else if (right.getRight() != null && right.getCount() < right.getRight().getCount()) {
				rightRightRotation(parent);
			} else {
				checkTree_aux(right);
			}
		}
		parent.updateHeight();
	}
}
