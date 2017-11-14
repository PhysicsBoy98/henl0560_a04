package cp213;

import java.io.IOException;
import java.util.Arrays;

/**
 * Used to test the BST, AVL, PopularityTree, and DrawTree classes. Creates
 * simple trees and displays them with DrawTree.
 *
 * @author David Brown
 * @version 2017-11-06
 */
public class DrawTreeExample {

    /**
     * Test DrawTree. Tree file names must end with {@code .svg}.
     *
     * @param args
     *            Unused.
     * @throws IOException
     *             Failed attempt to write to a file.
     */
    public static void main(final String[] args) throws IOException {

	// Define Integer test data.
	final Integer[] integers = { 3, 7, 1, 9, 9, 9, 9, 9, 9, 9, 9, 2, 8, 5,
		4, 6 };
	// Create a tree.
	// final BST<Integer> integerTree = new BST<>();
	// final AVL<Integer> integerTree = new AVL<>();
	final PopularityTree<Integer> integerTree = new PopularityTree<>();

	// Add values to the tree.
	for (final Integer value : integers) {
	    integerTree.insert(value);
	}
	System.out.println("Valid: " + integerTree.valid());
	System.out.println("--------------------------------");
	System.out.println("Size: " + integerTree.getSize());
	System.out.println("--------------------------------");
	System.out.println("Data/Count Pairs");
	final DataCountPair<Integer>[] integerPairs = integerTree.toArray();
	// Sort the data count pairs for printing.
	Arrays.sort(integerPairs);

	for (final DataCountPair<Integer> a : integerPairs) {
	    System.out.println(a);
	}
	System.out.println("------------------------------------------");
	// Display the tree.
	final DrawTree<Integer> integerDt = new DrawTree<>(integerTree);

	// Write the tree to an SVG file.
	integerDt.save("integerTree.svg");

	// Define Character test data.
	// final Character[] chars = { 'M', 'F', 'T', 'C', 'J', 'P', 'W', 'A',
	// 'D',
	// 'H', 'K', 'N', 'R', 'U', 'Y', 'B', 'E', 'I', 'G', 'L', 'O', 'Q',
	// 'S', 'V', 'X', 'Z' };
	final Character[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'A', 'A',
		'A', 'B', 'C', 'C', 'C', 'C', 'E', 'F', 'F', 'F', 'F', 'F', 'G',
		'G', 'G', 'G', 'G', 'E', 'E', 'E', 'E', 'E', 'E', 'E' }; // Create
									 // a
									 // tree.
	// final BST<Character> characterTree = new BST<>();
	// final AVL<Character> characterTree = new AVL<>();
	final PopularityTree<Character> characterTree = new PopularityTree<>();

	// Add values to the tree.
	for (final Character value : chars) {
	    characterTree.insert(value);
	}
	System.out.println("Valid: " + characterTree.valid());
	System.out.println("--------------------------------");
	System.out.println("Size: " + characterTree.getSize());
	System.out.println("--------------------------------");
	System.out.println("Data/Count Pairs");
	final DataCountPair<Character>[] characterPairs = characterTree
		.toArray();

	Arrays.sort(characterPairs);

	for (final DataCountPair<Character> a : characterPairs) {
	    System.out.println(a);
	}
	// Display the tree.
	final DrawTree<Character> characterDt = new DrawTree<>(characterTree);

	// Write the tree to an SVG file.
	characterDt.save("characterTree.svg");
    }

}
