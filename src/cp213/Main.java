package cp213;

public class Main {

	public static void main(String[] args) {
		System.out.println("-----------------------------------");
		System.out.println("-----------Testing BST-------------");
		System.out.println("-----------------------------------");
		System.out.println("Testing Insert");
		BST<Integer> bst = new BST<Integer>();
		bst.insert(9);
		bst.insert(8);
		bst.insert(7);

		DrawTree<Integer> draw = new DrawTree<Integer>(bst);

	}

}
