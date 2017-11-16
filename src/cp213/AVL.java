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

	private void checkTree() {
		int rootBN = balanceNumber(root);
		if (Math.abs(rootBN) > 1) {
			if (rootBN > 1) {
				int rootLeftBN = 0;
				if (root.getLeft() != null) {
					rootLeftBN = balanceNumber(root.getLeft());
				}
				if (rootLeftBN < 0) {
					// look at bohr, avl, left right
				} else {
					TreeNode<T> pivot = leftRotation(this.root);
					this.root = pivot;
					root.updateHeight();
				}
			} else {
				int rootRightBN = 0;
				if (root.getRight() != null) {
					rootRightBN = balanceNumber(root.getRight());
				}
				if (rootRightBN > 0) {
					// look at bohr, avl, right left
				} else {
					TreeNode<T> pivot = rightRotation(this.root);
					this.root = pivot;
					root.updateHeight();
				}
			}
		} else {
			checkTree_aux(root);
		}
	}

	// fix checkTree, apply fixes to checkTree_aux after
	private void checkTree_aux(TreeNode<T> parent) {
		int leftBN = balanceNumber(parent.getLeft());
		int rightBN = balanceNumber(parent.getRight());
		if (parent.getLeft() != null) {
			if (parent.getLeft().getLeft() != null) {
				leftH = parent.getLeft().getLeft().getHeight();
			}
			if (parent.getLeft().getRight() != null) {
				rightH = parent.getLeft().getRight().getHeight();
			}
			if (Math.abs(rightH - leftH) > 1) {
				if (leftH > rightH) {
					leftLeftRotation(parent);
				} else {
					leftRightRotation(parent);
				}
			} else {
				checkTree_aux(parent.getLeft());
			}
		}
		if (parent.getRight() != null) {
			if (parent.getRight().getLeft() != null) {
				leftH = parent.getRight().getLeft().getHeight();
			}
			if (parent.getRight().getRight() != null) {
				rightH = parent.getRight().getRight().getHeight();
			}
			if (Math.abs(rightH - leftH) > 1) {
				if (leftH > rightH) {
					rightLeftRotation(parent);
				} else {
					rightRightRotation(parent);
				}
			} else {
				checkTree_aux(parent.getRight());
			}
		}
		parent.updateHeight();
	}

	private int balanceNumber(TreeNode<T> node) {
		int leftH = 0;
		if (node.getLeft() != null) {
			leftH = node.getLeft().getHeight();
		}
		int rightH = 0;
		if (node.getRight() != null) {
			rightH = node.getRight().getHeight();
		}
		return leftH - rightH;
	}
}
