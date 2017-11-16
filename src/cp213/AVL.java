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
		TreeNode<T> pivot;
		int rootBN = balanceNumber(this.root);
		if (rootBN > 1) {
			if (rootBN > 1) {
				int rootLeftBN = 0;
				if (this.root.getLeft() != null) {
					rootLeftBN = balanceNumber(this.root.getLeft());
				}
				if (rootLeftBN < 0) {
					pivot = leftRotation(this.root.getLeft());
					this.root.setLeft(pivot);
					this.root.getLeft().updateHeight();
					pivot = rightRotation(this.root);
					this.root = pivot;
					this.root.updateHeight();
				} else if (rootLeftBN == 1) {
					pivot = rightRotation(this.root);
					this.root = pivot;
					this.root.updateHeight();
				} else {
					checkTree_aux(this.root);
				}
			}
		} else if (rootBN < -1) {
			int rootRightBN = 0;
			if (this.root.getRight() != null) {
				rootRightBN = balanceNumber(this.root.getRight());
			}
			if (rootRightBN > 0) {
				pivot = rightRotation(this.root.getRight());
				this.root.setRight(pivot);
				this.root.getLeft().updateHeight();
				pivot = leftRotation(this.root);
				this.root = pivot;
				this.root.updateHeight();
			} else if (rootRightBN == -1) {
				pivot = leftRotation(this.root);
				this.root = pivot;
				this.root.updateHeight();
			} else {
				checkTree_aux(this.root);
			}
		} else {
			checkTree_aux(this.root);
		}
	}

	// fix checkTree, apply fixes to checkTree_aux after
	private void checkTree_aux(TreeNode<T> parent) {
		TreeNode<T> pivot;
		if (parent.getLeft() != null) {
			int parentLeftBN = balanceNumber(parent.getLeft());
			if (parentLeftBN > 1) {
				if (parentLeftBN > 1) {
					int leftLeftBN = 0;
					if (parent.getLeft().getLeft() != null) {
						leftLeftBN = balanceNumber(parent.getLeft().getLeft());
					}
					if (leftLeftBN < 0) {
						pivot = leftRotation(parent.getLeft().getLeft());
						parent.getLeft().setLeft(pivot);
						parent.getLeft().getLeft().updateHeight();
						pivot = rightRotation(parent.getLeft());
						parent.setLeft(pivot);
						parent.getLeft().updateHeight();
					} else if (leftLeftBN == 1) {
						pivot = rightRotation(parent.getLeft());
						parent.setLeft(pivot);
						parent.getLeft().updateHeight();
					} else {
						checkTree_aux(parent.getLeft());
					}
				}
			} else if (parentLeftBN < -1) {
				int leftRightBN = 0;
				if (parent.getLeft().getRight() != null) {
					leftRightBN = balanceNumber(parent.getLeft().getRight());
				}
				if (leftRightBN > 0) {
					pivot = rightRotation(parent.getLeft().getRight());
					parent.getLeft().setRight(pivot);
					parent.getLeft().getRight().updateHeight();
					pivot = leftRotation(parent.getLeft());
					parent.setLeft(pivot);
					parent.getLeft().updateHeight();
				} else if (leftRightBN == -1) {
					pivot = leftRotation(parent.getLeft());
					parent.setLeft(pivot);
					parent.getLeft().updateHeight();
				} else {
					checkTree_aux(parent.getLeft());
				}
			} else {
				checkTree_aux(parent.getLeft());
			}
		}
		if (parent.getRight() != null) {
			int parentRightBN = balanceNumber(parent.getRight());
			if (parentRightBN > 1) {
				if (parentRightBN > 1) {
					int rightLeftBN = 0;
					if (parent.getRight().getLeft() != null) {
						rightLeftBN = balanceNumber(parent.getRight().getLeft());
					}
					if (rightLeftBN < 0) {
						pivot = leftRotation(parent.getRight().getLeft());
						parent.getRight().setLeft(pivot);
						parent.getRight().getLeft().updateHeight();
						pivot = rightRotation(parent.getRight());
						parent.setRight(pivot);
						parent.getRight().updateHeight();
					} else if (rightLeftBN == 1) {
						pivot = rightRotation(parent.getRight());
						parent.setRight(pivot);
						parent.getRight().updateHeight();
					} else {
						checkTree_aux(parent.getRight());
					}
				}
			} else if (parentRightBN < -1) {
				int rightRightBN = 0;
				if (parent.getRight().getRight() != null) {
					rightRightBN = balanceNumber(parent.getRight().getRight());
				}
				if (rightRightBN > 0) {
					pivot = rightRotation(parent.getRight().getRight());
					parent.getRight().setRight(pivot);
					parent.getRight().getLeft().updateHeight();
					pivot = leftRotation(parent.getRight());
					parent.setRight(pivot);
					parent.getRight().updateHeight();
				} else if (rightRightBN == -1) {
					pivot = leftRotation(parent.getRight());
					parent.setRight(pivot);
					parent.getRight().updateHeight();
				} else {
					checkTree_aux(parent.getRight());
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
