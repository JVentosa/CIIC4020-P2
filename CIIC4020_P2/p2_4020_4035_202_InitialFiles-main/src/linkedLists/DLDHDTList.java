package linkedLists;

import java.util.Iterator;
import java.util.NoSuchElementException;

import interfaces.LinkedList;
import interfaces.Node;

public class DLDHDTList<E> implements LinkedList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		// ADD CODE HERE to generate empty linked list of this type 
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// ADD CODE HERE
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			return null; 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			return null; 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target) {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		return null; 
	}

	public Node<E> getNodeBefore(Node<E> target) {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		return null; 
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		// ADD CODE HERE to disconnect target from the linked list, reduce lent, clean target...
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		// TODO
	}

	@Override
	public Iterable<Node<E>> nodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
		
	
	/**
	 * Class to represent a node of the type used in doubly linked lists. 
	 * @author pedroirivera-vega
	 *
	 * @param <T> Data type of element in a node.
	 */
	protected static class DNode<T> implements Node<T> {
		private T element; 
		private DNode<T> prev, next; 

		// Constructors
		public DNode() {
			this(null, null, null); 
		}
		
		public DNode(T e) { 
			this(e, null, null); 
		}
		
		public DNode(T e, DNode<T> p, DNode<T> n) { 
			element = e; 
			prev = p; 
			next = n; 
		}
		
		// Methods
		public DNode<T> getPrev() {
			return prev;
		}
		public void setPrev(DNode<T> prev) {
			this.prev = prev;
		}
		public DNode<T> getNext() {
			return next;
		}
		public void setNext(DNode<T> next) {
			this.next = next;
		}
		public T getElement() {
			return element; 
		}

		public void setElement(T data) {
			element = data; 
		} 
		
		/**
		 * Just set all fields to null. 
		 */
		public void clean() { 
			element = null; 
			prev = next = null; 
		}
		
	}


}
