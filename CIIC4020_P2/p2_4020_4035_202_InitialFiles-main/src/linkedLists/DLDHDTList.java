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
		/*
		 * Create both header/trailer as an empty dummy nodes, Set them both to each
		 * other, one after the other
		 */
		header = new DNode<>();
		trailer = new DNode<>();
		header.setNext(trailer);
		trailer.setPrev(header);
		length = 0;
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

	/*
	 * Creates new DNode for new node, also add target = dAfter for it to be there
	 * After the new node Set dBefore as next of the target, to have a connection
	 * for the target before and after
	 * 
	 * setters implement this 100% dNode, set dAfter(target) as next and set dBefore
	 * as previous
	 */
	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// ADD CODE HERE
		DNode<E> dNode = (DNode<E>) nuevo;
		DNode<E> dBefore = (DNode<E>) target;
		DNode<E> dAfter = dBefore.getNext();
		dAfter.setNext(dBefore);
		dBefore.setPrev(dNode);
		dNode.setNext(dAfter);
		dNode.setPrev(dBefore);
		length++;

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

	/*
	 * Create new DNode and set as target's.getnext() Simply return nextNode if it
	 * isn't null
	 */
	public Node<E> getNodeAfter(Node<E> target) {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		DNode<E> nextNode = ((DNode<E>) target).getNext();
		if (nextNode == null)
			return null;
		else
			return nextNode;
	}

	/*
	 * Create new DNode and set as target's.getprev() Simply return prevNode if it
	 * isn't null
	 */
	public Node<E> getNodeBefore(Node<E> target) {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		DNode<E> prevNode = ((DNode<E>) target).getPrev();
		if (prevNode == null)
			return null;
		else {
			return prevNode;
		}
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		// ADD CODE HERE to disconnect target from the linked list, reduce lent, clean
		// target...

		/*
		 * Target can't be a dummy, it's always a data note, therefore, no if/else is
		 * required to differentiate if dummy or not
		 * 
		 * Make new DNode nodes, set as target doubly node, target's previous node and target's next node
		 * Afterwards, set target's previous node as next node's previous, and vice versa
		 * clean target to make it's reference and data to null
		 * reduce LList length by 1
		 */
		DNode<E> dTarget = (DNode<E>) target; // Create a casted (Doubly Node) version of the target
		DNode<E> prevNode = dTarget.getPrev(); // Gets the previous node of the target
		DNode<E> nextNode = dTarget.getNext(); // Gets the next node of the target

		prevNode.setNext(nextNode);// Sets the previous node's next node to the one after the target
		nextNode.setPrev(prevNode);// Sets the next node's previous node to the one before the target

		dTarget.clean();
		length--;
	}

	/**
	 * Prepares every node so that the garbage collector can free its memory space,
	 * at least from the point of view of the list. This method is supposed to be
	 * used whenever the list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any) from the linked list
	 */
	private void destroy() {
		while (header != null) {
			DNode<E> nnode = header.getNext();
			header.clean();
			header = nnode;
		}
	}

	/**
	 * The execution of this method removes all the data nodes from the current
	 * instance of the list, leaving it as a valid empty doubly linked list with
	 * dummy header and dummy trailer nodes.
	 * 
	 * 
	 * If statement to get make sure list isn't empty, if not empty, remove the next node after header
	 * Run destroy() method to prepare every single note that also isn't a header/trailer
	 */
	public void makeEmpty() {
		if (header.getNext() != trailer) {
			removeNode(header.getNext());
		}
		destroy();
	}
/*
 * HELP ME IDK WHAT TO DO HERE
 */
	@Override
	public Iterable<Node<E>> nodes() {
		// TODO Auto-generated method stub
		return null;
	}
/*
 * TODAVIA ME FALTA ESTO TAMBIEN :^)
 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Class to represent a node of the type used in doubly linked lists.
	 * 
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
