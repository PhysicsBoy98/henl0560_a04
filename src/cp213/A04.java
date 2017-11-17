package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Process text files with three kinds of trees to determine their relative
 * efficiency.
 *
 * @author your name here
 * @version 2017-11-07
 */
public class A04 {

	/**
	 * Program for Assignment 4.
	 *
	 * @param args
	 *            unused
	 * @throws IOException
	 *             If error on files.
	 */
	public static void main(final String[] args) throws IOException {
		AVL<String> avl = new AVL<String>();
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		avl.insert("A");
		for (int i = 1; i < alpha.length(); i++) {
			avl.insert(alpha.substring(i - 1, i));
		}
		DrawTree<String> avlDraw = new DrawTree<String>(avl);
	}

	/**
	 * Print a formatted table of character counts and percentages.
	 *
	 * @param tree
	 *            The BST to generate the table from.
	 */
	public static void characterTable(final BST<Character> tree) {

	}

	/**
	 * Determine the number of comparisons to retrieve the contents of a file from a
	 * tree. Reset the number of comparisons, then attempt to retrieve every letter
	 * in the file from tree. All letters must be converted to upper case.
	 *
	 * @param tree
	 *            The BST to process.
	 * @param file
	 *            The file to process.
	 * @return The number of comparisons necessary to find every letter in file in
	 *         tree.
	 * @throws FileNotFoundException
	 *             Thrown if file not found.
	 */
	public static int retrieve(final BST<Character> tree, final File file) throws FileNotFoundException {
		// your code here

		return tree.getComparisons();
	}

	/**
	 * Train a tree by inserting all letters from a file into the tree. Letters must
	 * be converted to upper-case. Non-letters are ignored.
	 *
	 * @param tree
	 *            The BST to train.
	 * @param file
	 *            The file to read into the tree.
	 * @throws FileNotFoundException
	 *             Thrown if file not found.
	 */
	public static void train(final BST<Character> tree, final File file) throws FileNotFoundException {
		// your code here

		return;
	}
}
