
public class binarysearchtree<T extends Comparable<T>> {
	//recursive BST
	protected Node<T> root;
	protected int numOfElements;
	private boolean found;

	public binarysearchtree() {
		this.root = null;
		numOfElements = 0;
	}
	public void add(T item) {
		if (item == null)
			return;
		root = add (root, item);
		
	}
	private Node<T> add(Node<T> node, T item) {
		if (node == null) { 
			numOfElements++;
			return new Node<T>(item);
		}
		if (node.data.compareTo(item) > 0)
			node.left = add(node.left, item);
		else if (node.data.compareTo(item) < 0)
			node.right = add(node.right, item);
		return node; 
	}
	public boolean remove(T target)
	{
		root = recRemove(target, root);
		return found;
	}
	private Node<T> recRemove(T target, Node<T> node)
	{
		if (node == null)
			found = false;
		else if (target.compareTo(node.data) < 0)
			node.left = recRemove(target, node.left);
		else if (target.compareTo(node.data) > 0)
			node.right = recRemove(target, node.right );
		else {
			node = removeNode(node);
			found = true;
		}
		return node;
	}
	private Node<T> removeNode(Node<T> node)
	{
		T data;
		if (node.left == null)
			return node.right ;
		else if (node.right  == null)
			return node.left;
		else {
			data = getPredecessor(node.left);
			node.data = data;
			node.left = recRemove(data, node.left);
			return node;
		}
	}
	private T getPredecessor(Node<T> subtree)
	{
		if (subtree==null) throw new NullPointerException("getPredecessor called with an empty subtree");
		Node<T> temp = subtree;
		while (temp.right  != null)
			temp = temp.right ;
		return temp.data;
	}
	public int size() {
		return numOfElements;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		inOrderPrint(root, s);
		return s.toString();
	}
	private void inOrderPrint(Node<T> tree, StringBuilder s) {
		if (tree != null) {
			inOrderPrint(tree.left, s);
			s.append(tree.data.toString() + "  ");
			inOrderPrint(tree.right , s);
		}
	}
	public String toStringTreeFormat() {

		StringBuilder s = new StringBuilder();

		preOrderPrint(root, 0, s);
		return s.toString();
	}
	private void preOrderPrint(Node<T> tree, int level, StringBuilder output) {
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.data);
			preOrderPrint(tree.left, level + 1, output);
			preOrderPrint(tree.right , level + 1, output);
		}
		else {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append("null");
		}
	}
	protected static class Node <T extends Comparable <T>> 
						implements Comparable <Node <T> > {
	
		protected Node <T> left;  //reference to the left subtree 
		protected Node <T> right; //reference to the right subtree
		protected T data;            //data item stored in the node

		protected int height; 
		protected Node(T data) {
			this.data = data;
			left = null;
			right = null;
			height = 0; 
		}
		@Override
		public int compareTo(Node<T> other) {
			return this.data.compareTo(other.data);
		} 
		@Override
		public String toString() {
			return data.toString();
		}
	}

}
