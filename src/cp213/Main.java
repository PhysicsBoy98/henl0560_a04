package cp213;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("-----------------------------------");
		System.out.println("-----------Testing BST-------------");
		System.out.println("-----------------------------------");
		System.out.println();
		BST<Integer> bst_i = new BST<Integer>();
		BST<String> bst_s = new BST<String>();
		boolean test_i = bst_i.empty();
		boolean test_s = bst_s.empty();
		System.out.println("Testing empty on empty bst");
		System.out.println("String bst: " + test_s);
		System.out.println("Integer bst: " + test_i);
		System.out.println();

		System.out.println("Testing Insert");
		bst_s.insert("one");
		bst_i.insert(1);
		DrawTree<String> draw_s = new DrawTree<String>(bst_s);
		DrawTree<Integer> draw_i = new DrawTree<Integer>(bst_i);
		System.out.println();

		System.out.println("Testing empty on non-empty bst");
		test_s = bst_s.empty();
		test_i = bst_i.empty();
		System.out.println("String bst: " + test_s);
		System.out.println("Integer bst: " + test_i);
		System.out.println();

		System.out.println("Testing contains with a value in the bst");
		test_s = bst_s.contains("one");
		test_i = bst_i.contains(1);
		System.out.println("String bst: " + test_s);
		System.out.println("Integer bst: " + test_i);
		System.out.println();

		System.out.println("Testing contains with a value not in the bst");
		test_s = bst_s.contains("two");
		test_i = bst_i.contains(2);
		System.out.println("String bst: " + test_s);
		System.out.println("Integer bst: " + test_i);
		System.out.println();
	}
}
