package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
		final String dash = "------------------------------\r\n";
		ArrayList<BST<Character>> trees = new ArrayList<BST<Character>>();
		trees.add(new BST<Character>());
		trees.add(new PopularityTree<Character>());
		trees.add(new AVL<Character>());
		final File decline = new File("src/cp213/decline.txt");
		final File miserables = new File("scr/cp213/miserables.txt");
		final File otoos610 = new File("scr/cp213/otoos610.txt");
		File training = decline;
		File comparisons;
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				training = decline;
				comparisons = miserables;
			} else if (i == 1) {
				training = miserables;
				comparisons = otoos610;
			} else {
				training = otoos610;
				comparisons = decline;
			}
			System.out.println("Traing File: " + training.getName());
			System.out.println("Comparisons File: " + comparisons.getName());
			System.out.println(dash);
			for (BST<Character> tree : trees) {
				train(tree, training);
			}
			characterTable(trees.get(2));
			for (BST<Character> tree : trees) {
				System.out.println(dash);
				System.out.println("Tree Type: " + tree.getClass());
				System.out.println("Valid: " + tree.valid());
				System.out.println("Height: " + tree.getHeight());
				System.out.println("Retriving...");
				System.out.println(retrieve(tree, comparisons));
				tree.resetComparisons();
			}
		}

	}

	/**
	 * Print a formatted table of character counts and percentages.
	 *
	 * @param tree
	 *            The BST to generate the table from.
	 */
	public static void characterTable(final BST<Character> tree) {
		final char[] alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		double total = 0;
		int count = 0;
		String output = null;
		DataCountPair<Character> dcp;
		System.out.println("Character Table for Training File\n");
		System.out.println("Char\tCount\tPercent\n");
		for (char c : alpha) {
			dcp = tree.retrieve(c);
			total += dcp.getCount();
		}
		for (char c : alpha) {
			dcp = tree.retrieve(c);
			count = dcp.getCount();
			output = String.format(c + "\t" + count + "\t" + "%.2f", ((double) (count / total) * 100));
			System.out.println(output);
			tree.resetComparisons();
		}
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
		Scanner s = new Scanner(file);
		String word = s.next().toUpperCase();
		while (s.hasNext()) {
			for (int i = 0; i < word.length(); i++) {
				tree.retrieve(word.charAt(i));
			}
			word = s.next().toUpperCase();
		}
		s.close();
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
		Scanner s = new Scanner(file);
		String word = s.next().toUpperCase();
		while (s.hasNext()) {
			for (int i = 0; i < word.length(); i++) {
				tree.insert(word.charAt(i));
			}
			word = s.next().toUpperCase();

		}
		s.close();
		return;
	}
}
