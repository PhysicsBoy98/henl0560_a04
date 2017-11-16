package cp213;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println("-----------------------------------");
		System.out.println("-----------Testing BST-------------");
		System.out.println("-----------------------------------");
		System.out.println("Testing Insert");
		BST<Integer> bst = new BST<Integer>();
		bst.insert(9);
		// DrawTree<Integer> draw = new DrawTree<Integer>(bst);
		boolean bool = bst.contains(9);
		System.out.println(bool);
		bst.insert(8);
		bst.insert(10);
		bst.insert(1);
		bst.insert(9);
		bool = bst.valid();
		System.out.println(bool);
		bool = bst.contains(1);
		System.out.println(bool);

		// DrawTree<Integer> draw2 = new DrawTree<Integer>(bst);

		PopularityTree<String> pTree = new PopularityTree<String>();
		pTree.insert("M");
		pTree.insert("K");
		pTree.insert("O");
		pTree.insert("J");
		pTree.insert("L");
		pTree.insert("K");
		pTree.insert("O");
		pTree.insert("O");
		// DrawTree<String> d6 = new DrawTree<String>(pTree);

		AVL<Integer> avl = new AVL<Integer>();
		ArrayList<DrawTree<Integer>> windows = new ArrayList<DrawTree<Integer>>();
		avl.insert(6);
		windows.add(new DrawTree<Integer>(avl));
		int[] numList = { 7, 8, 9, 11, 12, 15, 18 };
		for (int i : numList) {
			if (i == 11) {
				System.out.println(i);
			}
			avl.insert(i);
			windows.add(new DrawTree<Integer>(avl));
		}
	}

}
