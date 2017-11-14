package cp213;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;

/**
 * Displays the contents of a BST as a {@code Graphics2D} diagram. Can be saved
 * to an SVG file.
 *
 * @author David Brown
 * @version 2017-11-06
 * @param <T>
 *            The data type of the tree to display.
 */
@SuppressWarnings("serial")
public class DrawTree<T extends Comparable<T>> extends JFrame {

    /**
     * Creates a graphics component to draw a tree in.
     */
    private class DrawPanel extends JComponent {

	// Define border constants.
	static final int BORDER_SIZE = 4;
	private final Insets BORDER_INSETS = new Insets(BORDER_SIZE,
		BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
	private final Border BORDER = new EmptyBorder(this.BORDER_INSETS);

	/**
	 * Initialize the component with a layout and borders.
	 */
	public DrawPanel() {
	    this.setLayout(new BorderLayout());
	    this.setBorder(this.BORDER);
	}

	@Override
	public void paintComponent(final Graphics g) {
	    super.paintComponent(g);
	    // Draw the tree.
	    final Graphics2D g2d = (Graphics2D) g;
	    g2d.setStroke(new BasicStroke(2));
	    g2d.setColor(Color.BLACK);
	    DrawTree.this.drawNode(g2d, DrawTree.this.tree.getRoot(), 0, 0);
	}
    }

    // Define display fonts constants.
    private static final Font FONT_COUNT = new Font("SansSerif", Font.BOLD, 14);
    private static final Font FONT_HEIGHT = new Font("SansSerif", Font.BOLD,
	    16);
    private static final Font FONT_VALUE = new Font("SansSerif", Font.BOLD, 20);
    // Define node and line display constants.
    private static final double CIRCLE_SIZE = 36;
    private static final double X_DIFF = 24;
    private static final double Y_DIFF = 64;

    /**
     * Simple integer power calculator.
     *
     * @param base
     *            Base value.
     * @param power
     *            Power value.
     * @return a^b;
     */
    private static int power(int base, int power) {
	int result = 1;

	while (power > 0) {
	    if ((power & 1) == 1) {
		result *= base;
	    }
	    power >>= 1;
	    base *= base;
	}
	return result;
    }

    // Attributes
    private BST<T> tree = null; // The BST to process.
    private double xOffSet = 0; // Offset in pixels to the centre of the tree.
    private int height = 0; // Height of the diagram in pixels.
    private int width = 0; // Width of the diagram in pixels.

    /**
     * Creates a DrawTree object.
     *
     * @param <T>
     *            Type of data stored in tree.
     *
     * @param tree
     *            Tree to draw.
     */
    public DrawTree(final BST<T> tree) {
	// Initialize the drawing panel.
	final DrawPanel drawPanel = new DrawPanel();

	this.tree = tree;

	// 'splay' is the measure of how far a tree extends to the left and
	// right. Depends on the number of levels in the tree and its maximum
	// and minimum values.
	final int splayLeft = this.getSplayLeft(this.tree.getRoot(), 1);
	final int splayRight = this.getSplayRight(this.tree.getRoot(), 1);

	this.xOffSet = splayLeft * X_DIFF + X_DIFF;
	// Determine the width and height of the tree in pixels.
	this.width = (int) (this.xOffSet + splayRight * X_DIFF + X_DIFF);
	this.height = (int) (this.tree.getHeight() * DrawTree.Y_DIFF
		+ DrawTree.Y_DIFF);

	drawPanel.setPreferredSize(new Dimension(this.width, this.height));
	// Add scroll bars to the drawing panel.
	final JScrollPane scroll = new JScrollPane(drawPanel);
	// Display the tree frame.
	this.setContentPane(scroll);
	this.setSize(600, 600);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);

	return;
    }

    /**
     * Write the tree diagram to an SVG file.
     *
     * @param filename
     *            Name of the file to write to.
     * @throws IOException
     *             if filename cannot be opened.
     */
    public void save(final String filename) throws IOException {
	final File fv = new File(filename);
	// Create a SVG graphics object, and draw the tree to it.
	final SVGGraphics2D svg = new SVGGraphics2D(this.width, this.height);
	this.drawNode(svg, this.tree.getRoot(), 0, 0);
	// Write the resulting tree to an SVG file.
	SVGUtils.writeToSVG(fv, svg.getSVGElement());
	return;
    }

    /**
     * Draw a string centred in the bounding box of a Shape.
     *
     * @param g2d
     *            Graphics2D object to draw to.
     * @param text
     *            The string to print.
     * @param shape
     *            The Shape whose bounding box describes the limits of text.
     * @param font
     *            The font to draw the text in.
     */
    private void drawCenteredString(final Graphics2D g2d, final String text,
	    final Shape shape, final Font font) {
	// Get the FontMetrics for the desired font.
	final FontMetrics metrics = g2d.getFontMetrics(font);
	// Determine the X coordinate for the text.
	final Rectangle rect = shape.getBounds();
	final int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	// Determine the Y coordinate for the text.
	final int y = rect.y + (rect.height - metrics.getHeight()) / 2
		+ metrics.getAscent();
	g2d.setFont(font);
	g2d.drawString(text, x, y);
    }

    /**
     * Draws a BST node and the lines connecting it to its children, if any.
     * Recursively draws children.
     *
     * @param g2d
     *            Graphics2D object to draw to.
     * @param node
     *            The TreeNode to draw.
     * @param splay
     *            The horizontal splay distance from the centre of the tree.
     * @param level
     *            The vertical distance from the tree root.
     */
    private void drawNode(final Graphics2D g2d, final TreeNode<T> node,
	    final int splay, final int level) {

	if (node != null) {
	    // Calculate the x and y position of this node.
	    final double x1 = this.xOffSet + splay * X_DIFF;
	    final double y1 = Y_DIFF + level * Y_DIFF;
	    final Point2D point = new Point2D.Double(x1, y1);
	    // Calculate the horizontal splay distance to node's children.
	    final int splayDiff = power(2, this.tree.getHeight() - 2 - level);
	    // Calculate the vertical distance to the node's children.
	    final double y2 = y1 + Y_DIFF;

	    g2d.setColor(Color.BLACK);

	    if (node.getLeft() != null) {
		// Draw the line to the left child, if any.
		final double x2 = x1 - splayDiff * X_DIFF;
		final Point2D pointLeft = new Point2D.Double(x2, y2);

		final Line2D line = new Line2D.Double(point, pointLeft);
		g2d.draw(line);
		// Recursively draw the left child.
		this.drawNode(g2d, node.getLeft(), splay - splayDiff,
			level + 1);
	    }
	    if (node.getRight() != null) {
		// Draw the line to the right child, if any.
		final double x2 = x1 + splayDiff * X_DIFF;
		final Point2D pointRight = new Point2D.Double(x2, y2);
		final Line2D line = new Line2D.Double(point, pointRight);
		g2d.draw(line);
		// Recursively draw the right child.
		this.drawNode(g2d, node.getRight(), splay + splayDiff,
			level + 1);
	    }
	    // Draw the node circle.
	    final Shape circle = new Ellipse2D.Double(x1 - CIRCLE_SIZE / 2,
		    y1 - CIRCLE_SIZE / 2, CIRCLE_SIZE, CIRCLE_SIZE);
	    g2d.setColor(Color.WHITE);
	    g2d.fill(circle);
	    g2d.setColor(Color.BLACK);
	    g2d.draw(circle);
	    // Draw the node value in the node circle.
	    this.drawCenteredString(g2d, node.getData().toString(), circle,
		    FONT_VALUE);
	    // Draw the node height above the node circle.
	    Rectangle2D rect = new Rectangle2D.Double(x1 - CIRCLE_SIZE / 2,
		    y1 - CIRCLE_SIZE, CIRCLE_SIZE, CIRCLE_SIZE / 2);
	    this.drawCenteredString(g2d, Integer.toString(node.getHeight()),
		    rect, FONT_HEIGHT);
	    // Draw the node count below the node circle.
	    rect = new Rectangle2D.Double(x1 - CIRCLE_SIZE / 2,
		    y1 + CIRCLE_SIZE / 2, CIRCLE_SIZE, CIRCLE_SIZE / 2);
	    this.drawCenteredString(g2d, Integer.toString(node.getCount()),
		    rect, FONT_COUNT);
	}
	return;
    }

    /**
     * Determines the maximum splay of the left of the tree.
     *
     * @param node
     *            The tree node to evaluate.
     * @param level
     *            The level in the tree of node.
     * @return The splay value of node.
     */
    private int getSplayLeft(final TreeNode<T> node, final int level) {
	int splay = 0;

	if (node != null) {
	    splay = power(2, this.tree.getHeight() - (level + 1))
		    + this.getSplayLeft(node.getLeft(), level + 1);
	}
	return splay;
    }

    /**
     * Determines the maximum splay of the right of the tree.
     *
     * @param node
     *            The tree node to evaluate.
     * @param level
     *            The level in the tree of node.
     * @return The splay value of node.
     */
    private int getSplayRight(final TreeNode<T> node, final int level) {
	int splay = 0;

	if (node != null) {
	    splay = power(2, this.tree.getHeight() - (level + 1))
		    + this.getSplayRight(node.getRight(), level + 1);
	}
	return splay;
    }

}
