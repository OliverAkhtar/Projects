/*Provided BinaryTree class from assignment description to be used for program.*/

import java.util.Iterator;
import java.util.LinkedList;

public class BinaryTree<AnyType> implements Iterable<AnyType>
{
	private static class Node<AnyType>
	{
		private AnyType element;
		private Node<AnyType> parent;
		private Node<AnyType> left;
		private Node<AnyType> right;
		
		public Node(AnyType e, Node<AnyType>p,Node<AnyType> l, Node<AnyType> r)
		{
			element = e;
			parent = p;
			left = l;
			right = r;
		}
		
		public AnyType getElement() { return element; }
		public Node<AnyType> getParent() { return parent; }
		public Node<AnyType> getLeft() { return left; }
		public Node<AnyType> getRight() { return right; }
		
		public void setElement(AnyType e) { element = e; }
		public void setParent(Node<AnyType> p) { parent = p; }
		public void setLeft(Node<AnyType> l) { left = l; }
		public void setRight(Node<AnyType> r) { right = r; }
	}
	
	private Node<AnyType> root;
	
	public BinaryTree() { makeEmpty(); }
	
	public BinaryTree(AnyType e) { setRoot(e); }
	
	public void makeEmpty() { root = null; }
		
	public void attach(BinaryTree<AnyType> t1, BinaryTree<AnyType> t2)
	{
		Node<AnyType> leftChild, rightChild;
		leftChild = t1.getRoot();
		rightChild = t2.getRoot();
		leftChild.setParent(root);
		rightChild.setParent(root);
		setLeftChild(leftChild);
		setRightChild(rightChild);
		t1.makeEmpty();
		t2.makeEmpty();
	}
	
	public Iterator<AnyType> iterator()
	{
		LinkedList<AnyType> snapshot = new LinkedList<>();
		postOrderTraversal(root, snapshot);
		return snapshot.iterator();
	}
	
	private Node<AnyType> createNode(AnyType e, Node<AnyType> p,Node<AnyType> l,
									 Node<AnyType> r)
	{
		return new Node<AnyType>(e, p, l, r);
	}
	
	private Node<AnyType> getRoot() { return root; }
	
	private Node<AnyType> getLeftChild() { return root.getLeft(); }
	
	private Node<AnyType> getRightChild() { return root.getRight(); }
	
	private void setRoot(AnyType e)
	{
		root = createNode(e, null, null, null);
	}
	
	private void setLeftChild(Node<AnyType> child)
	{
		root.setLeft(child);
	}
	
	private void setRightChild(Node<AnyType> child)
	{
		root.setRight(child);
	}
	
	private void postOrderTraversal(Node<AnyType> t, LinkedList<AnyType> l)
	{
		if (t != null)
		{
			postOrderTraversal(t.getLeft(), l);
			postOrderTraversal(t.getRight(), l);
			l.add(t.getElement());
		}
	}
}