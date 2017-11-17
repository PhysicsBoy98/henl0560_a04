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
		BST<Integer> bst_i2 = new BST<Integer>();
		BST<String> bst_s2 = new BST<String>();
		BST<Integer> bst_i3 = new BST<Integer>();
		BST<String> bst_s3 = new BST<String>();
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

		System.out.println("Testing equals on non-equivalent trees");
		bst_i2.insert(2);
		bst_s2.insert("two");
		boolean test_eq_i = bst_i.equals(bst_i2);
		boolean test_eq_s = bst_s.equals(bst_s2);
		System.out.println("String bst: " + test_eq_s);
		System.out.println("Integer bst: " + test_eq_i);
		System.out.println();

		System.out.println("Testing equals on equivalent trees");
		bst_i3.insert(1);
		bst_s3.insert("one");
		test_eq_i = bst_i.equals(bst_i3);
		test_eq_s = bst_s.equals(bst_s3);
		System.out.println("String bst: " + test_eq_s);
		System.out.println("Integer bst: " + test_eq_i);
		System.out.println();

		System.out.println("Testing retrieve on bst");
		DataCountPair<Integer> ret_i = bst_i.retrieve(1);
		DataCountPair<String> ret_s = bst_s.retrieve("one");
		System.out.println("String bst: " + ret_s);
		System.out.println("Integer bst: " + ret_i);
		System.out.println();

		System.out.println("Testing valid on valid bst");
		test_s = bst_s.valid();
		test_i = bst_i.valid();
		System.out.println("String bst: " + test_s);
		System.out.println("Integer bst: " + test_i);
		System.out.println();

	}
}
