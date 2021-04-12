package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

import interfaces.LinkedList;
import interfaces.Node;
import linkedLists.SLList.SNode;

public class SLFLList<E> implements LinkedList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		// TODO Auto-generated method stub
		
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		// TODO Auto-generated method stub
		
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// TODO Auto-generated method stub
		
	}

	public Node<E> getFirstNode() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node<E> getLastNode() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node<E> getNodeAfter(Node<E> target) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node<E> getNodeBefore(Node<E> target) {
		// TODO Auto-generated method stub
		return null;
	}

	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void removeNode(Node<E> target) {
		// TODO Auto-generated method stub
		
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}


	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addLastNode(Node<E> newNode) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Iterable<Node<E>> nodes() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Class to represent a node of the type used in singly linked lists. 
	 * @author pedroirivera-vega
	 *
	 * @param <T> Data type of element in a node.
	 */
	protected static class SNode<T> implements Node<T> {
		private T element; 
		private SNode<T> next; 
		public SNode() { 
			element = null; 
			next = null; 
		}
		public SNode(T data, SNode<T> next) { 
			this.element = data; 
			this.next = next; 
		}
		public SNode(T data)  { 
			this.element = data; 
			next = null; 
		}
		public T getElement() {
			return element;
		}
		public void setElement(T data) {
			this.element = data;
		}
		public SNode<T> getNext() {
			return next;
		}
		public void setNext(SNode<T> next) {
			this.next = next;
		}
		public void clean() { 
			element = null; 
			next = null; 
		}
	}

}
