package pacman.entries.pacman.behaviortree.helpers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a composite node, ie a node with multiple children such as
 * Selectors and Sequences.
 *
 * @author romsahel
 */
public abstract class Composite extends Node implements java.io.Serializable
{

	/**
	 * DEBUG constant can be used to output in the console the path taken within
	 * the tree.
	 */
	public static final boolean DEBUG = false;

	public int nbChildren;
	/**
	 * The list of children nodes (subnodes)
	 */
	ArrayList<Node> children = new ArrayList<>();
	/**
	 * Used to indent the debug output
	 */
	String prefix;

	private int maxDepth;

	public Composite()
	{
		this.prefix = "";
	}

	/**
	 * This constructor allows to give the depth of the node in the tree, which
	 * allows a cleaner and indented debug output
	 *
	 * @param depth
	 *              the depth of the node in the tree.
	 */
	public Composite(int depth)
	{
		if (!DEBUG)
			return;
		StringBuilder builder = new StringBuilder(depth);
		for (int i = 0; i < depth; i++)
			builder.append('\t');
		this.prefix = builder.toString();
	}

	/**
	 * Add children to the list
	 *
	 * @param nodes
	 *              children of the current node
	 * <p>
	 * @return this object, allows for method chaining
	 */
	public Composite addChildren(Node... nodes)
	{
		children.addAll(Arrays.asList(nodes));
		return this;
	}

	public int getChildrenCount()
	{
		return children.size();
	}

	public ArrayList<Node> getChildren()
	{
		return children;
	}

	protected void printDebug(Node currentNode, boolean beforeAction)
	{
		if (DEBUG)
			if ((currentNode instanceof Composite) == beforeAction)
				System.out.println(prefix + currentNode.getClass().getSimpleName());
	}

	public int getMaxDepth()
	{
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth)
	{
		this.maxDepth = maxDepth;
	}

	public Composite copy()
	{
		Composite newRoot = (this instanceof Selector) ? new Selector() : new Sequence();
		newRoot.maxDepth = maxDepth;
		newRoot.nbChildren = nbChildren;

		for (Node c : this.getChildren())
			if (c instanceof Composite)
				newRoot.addChildren(((Composite) c).copy());
			else
				newRoot.addChildren(c);

		return newRoot;
	}
}
