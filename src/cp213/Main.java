package cp213;

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
		pTree.insert("M");
		pTree.insert("K");
		pTree.insert("K");
		pTree.insert("O");
		pTree.insert("J");
		pTree.insert("L");

		DrawTree<String> d3 = new DrawTree<String>(pTree);
		pTree.insert("K");
		DrawTree<String> d4 = new DrawTree<String>(pTree);
		pTree.insert("O");
		pTree.insert("O");
		pTree.insert("O");
		DrawTree<String> d5 = new DrawTree<String>(pTree);
	}

}
