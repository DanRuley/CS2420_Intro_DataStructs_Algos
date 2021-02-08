package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Represents a Generic Binary Search Tree. If items are added so that the tree
 * is balanced, it provides adds, searches, and removes in O(logN) time.
 * 
 * @author Dan Ruley
 * @version March 2019
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	private int size = 0;

	private TreeNode root;

	private ArrayList<T> nodeList = new ArrayList<>();

	public static void main(String[] args) {
		BinarySearchTree<Integer> b = new BinarySearchTree<>();
		b.add(5);
		b.add(6);
		b.add(2);
		b.add(3);
		b.add(4);
		b.add(1);
		b.add(15);
		b.remove(5);
		b.remove(6);
		b.remove(1);
		b.remove(15);
		b.remove(4);
		b.remove(3);
		b.remove(2);
		
		
	}
	
	public static void printTree (BinarySearchTree<Integer> tree) {
		for (Integer i: tree.toArrayList()) {
			System.out.println(i + " ");
		}
		System.out.println();
	}
	
	/**
	 * Constructor for the tree. Sets the root to null initially.
	 */
	public BinarySearchTree() {
		this.root = null;
	}

	@Override
	public boolean add(T item) {
		TreeNode toAdd = new TreeNode(item);
		return addNode(toAdd);
	}

	@Override
	public boolean addAll(Collection<? extends T> items) {
		boolean wasChanged = false;

		for (T toAdd : items) {
			wasChanged = add(toAdd) || wasChanged;
		}

		return wasChanged;
	}

	@Override
	public void clear() {
		this.root = null;
		this.size = 0;
	}

	@Override
	public boolean contains(T item) {

		TreeNode node = root;

		while (node != null) {

			int compare = item.compareTo(node.value);

			if (compare < 0)
				node = node.left;

			if (compare > 0)
				node = node.right;

			if (compare == 0)
				return true;
		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends T> items) {
		for (T value : items) {
			if (!contains(value))
				return false;
		}
		return true;
	}

	@Override
	public T first() throws NoSuchElementException {
		if (root == null)
			throw new NoSuchElementException();

		TreeNode temp = root;
		while (temp.left != null) {
			temp = temp.left;
		}

		return temp.value;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public T last() throws NoSuchElementException {
		if (root == null)
			throw new NoSuchElementException();

		TreeNode temp = root;
		while (temp.right != null) {
			temp = temp.right;
		}

		return temp.value;
	}

	@Override
	public boolean remove(T item) {

		TreeNode toRemove = getNode(item);

		return removeMainHelper(toRemove);

	}

	/**
	 * Helper method that does the work for the public remove method. Returns false
	 * if the node is missing. Calls removeLeaf() if the node found to be a leaf,
	 * otherwise it calls removeChild().
	 */
	private boolean removeMainHelper(TreeNode toRemove) {
		// case where node is not in tree
		if (toRemove == null)
			return false;

		// case where the item to remove is the only item in the tree
		if (toRemove.value.compareTo(root.value) == 0 && root.left == null && root.right == null) {
			root = null;
			size--;
			return true;
		}

		// case where node is a leaf
		if (toRemove.left == null && toRemove.right == null) {
			removeLeaf(toRemove);
			return true;
		}

		// case where node has only one child
		if (toRemove.left == null || toRemove.right == null) {
			removeNodeWithChildren(toRemove);
			return true;
		}

		// Case where node has two siblings, find the node with the lowest value in the
		// right subtree, swap values, and then remove it
		if (toRemove.left != null && toRemove.right != null) {
			TreeNode temp = toRemove.right;
			while (temp.left != null) {
				temp = temp.left;
			}

			//Case where lowest right side item is a leaf
			toRemove.value = temp.value;
			if (temp.left == null && temp.right == null) {
				removeLeaf(temp);
				return true;
			}
			removeNodeWithChildren(temp);
			return true;

		}

		return false;
	}

	/**
	 * Helper method that deletes a leaf node
	 * 
	 * @param node to be removed
	 */
	private void removeLeaf(TreeNode toRemove) {

		TreeNode parent = toRemove.parent;
			
		if (parent.left != null && parent.left.value.compareTo(toRemove.value) == 0)
			parent.left = null;
		else if (parent.right != null)
			parent.right = null;

		size--;
	}

	/**
	 * Helper method that deals with removes for nodes with children. Find the right
	 * child and link it with the parent of the node that is being removed.
	 * 
	 * @param toRemove
	 */
	private void removeNodeWithChildren(TreeNode toRemove) {

		TreeNode parent = toRemove.parent;
		TreeNode child;

		if (toRemove.left != null)
			child = toRemove.left;
		else
			child = toRemove.right;

		
		//parent adopts the node
		if (parent != null && parent.left.value.compareTo(toRemove.value) == 0)
			parent.left = child;
		else if (parent != null)
			parent.right = child;

		//node takes on new parent
		if (child.parent != null && parent != null)
			child.parent = parent;
		
		else {
			root = child;
			child.parent = null;
		}

		size--;
	}

	@Override
	public boolean removeAll(Collection<? extends T> items) {
		boolean wasChanged = false;
		for (T item : items) {
			wasChanged = remove(item) || wasChanged;
		}

		return wasChanged;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList<T> toArrayList() {
		nodeList = new ArrayList<>();

		if (this.root == null)
			return nodeList;

		inOrderTraversalAndAdd(root);

		return nodeList;
	}

	/**
	 * Performs an inorder traversal of the BST beginning at the input node and adds
	 * values to an ArrayList in sorted order.
	 */
	private void inOrderTraversalAndAdd(TreeNode node) {

		if (node.left != null)
			inOrderTraversalAndAdd(node.left);
		nodeList.add(node.value);
		if (node.right != null)
			inOrderTraversalAndAdd(node.right);
	}

	/**
	 * Given the input value, this method performs a search of the BST and finds the
	 * correct node which will be the parent of the node with the value.
	 * 
	 * @param value - value the search uses to find the right parent.
	 * @return parent - the correct parent for the given value.
	 */
	private TreeNode findParent(T value) {
		TreeNode node = root;
		TreeNode parent = null;

		while (node != null) {

			parent = node;
			int compare = value.compareTo(node.value);

			if (compare < 0)
				node = node.left;

			if (compare > 0)
				node = node.right;

			if (compare == 0)
				return node;
		}

		return parent;
	}

	/**
	 * Adds the input node into the BST. First finds the node with the correct value
	 * which will be the parent of the added node. Then set it to the proper side of
	 * the parent.
	 * 
	 * @param toAdd - node to be added
	 * @return - true if the node was added, false if it is a duplicate and was not
	 *         added
	 */
	boolean addNode(TreeNode toAdd) {

		TreeNode parent = findParent(toAdd.value);

		// tree is empty, set root to node.
		if (parent == null) {
			root = toAdd;
		}

		else {
			int compare = toAdd.value.compareTo(parent.value);

			// If node is smaller than parent, add to the left
			if (compare < 0) {
				parent.left = toAdd;

				// If node is smaller than parent, add to the right
			} else if (compare > 0) {
				parent.right = toAdd;

			}
			// If they are equal, return false as the value is in the tree already.
			else {
				return false;
			}

			toAdd.parent = parent;
		}

		size++;
		return true;
	}

	/**
	 * Finds and returns the TreeNode that contains the given value. Returns null if
	 * no such node exists.
	 * 
	 * @param value
	 * @return TreeNode
	 */
	private TreeNode getNode(T value) {

		TreeNode temp = root;

		while (temp != null) {
			int compare = value.compareTo(temp.value);

			if (compare < 0)
				temp = temp.left;

			if (compare > 0)
				temp = temp.right;

			if (compare == 0)
				return temp;
		}

		return null;
	}

	/**
	 * Represents a node in the BST.
	 * 
	 * @author Dan
	 *
	 * @param <T>
	 */
	private class TreeNode {

		/** Value of this node */
		public T value;
		
		/** LH child reference */
		public TreeNode left = null;
		
		/** RH child reference */
		public TreeNode right = null;
		
		/** Parent node reference */
		public TreeNode parent = null;

		/**
		 * Constructs a node with a given value, parent and left/right child.
		 */
		public TreeNode(T value, TreeNode left, TreeNode right, TreeNode parent) {
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

		/**
		 * Constructs a node with no family.
		 */
		public TreeNode(T value) {
			this(value, null, null, null);
		}

	}

}
