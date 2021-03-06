package linkedLists;

import java.util.Iterator;
import java.util.NoSuchElementException;

import interfaces.LinkedList;
import interfaces.Node;
import linkedLists.SLFLList.SNode;

/* 
 * Jose Carlos Ventosa Rodriguez
 * 802-19-8619, CIIC4020 - 086/050L
 * jose.ventosa1@upr.edu
 */

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
			throw new NoSuchElementException("getFirstNode: The list is empty");
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0)
			throw new NoSuchElementException("getLastNode: The list is empty");
		return trailer.getPrev();
	}

	/*
	 * Create new DNode and set as target's.getnext() Simply return nextNode if it
	 * isn't null
	 */
	public Node<E> getNodeAfter(Node<E> target) {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		DNode<E> nextNode = ((DNode<E>) target).getNext();
		if (length == 0)
			throw new NoSuchElementException("getNodeAfter: The list is empty");
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
		if (length == 0)
			throw new NoSuchElementException("getNodeBefore: The list is empty");
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
		 * Make new DNode nodes, set as target doubly node, target's previous node and
		 * target's next node Afterwards, set target's previous node as next node's
		 * previous, and vice versa clean target to make it's reference and data to null
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
	 * If statement to get make sure list isn't empty, if not empty, remove the next
	 * node after header Run destroy() method to prepare every single note that also
	 * isn't a header/traile
	 */
	public void makeEmpty() {
		if (header.getNext() != trailer) {
			removeNode(header.getNext());
		}
		destroy();
	}

	@SuppressWarnings("unchecked")
	@Override
	public DLDHDTList<E> clone() throws CloneNotSupportedException {
		return (DLDHDTList<E>) super.clone(); // Uses the built-in .clone() method with super, making a deep clone of
												// the DLDHDTList
	}

	/*
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Iterable<Node<E>> nodes() {
		return new DLDHDTList.NodesIterable();
	}

	/*
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Iterator<E> iterator() {
		return new DLDHDTList.ElementsIterator();
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

	/*
	 * IDENTICAL ITERATOR ELEMENTS/NODES TO SLList.java Both work with each other as
	 * they are both Linked Lists SNodes changed into DNode, straight forward
	 * changes. All works the same
	 */
	private class ElementsIterator implements Iterator<E> {

		@SuppressWarnings("rawtypes")
		DLDHDTList.NodesIterator iterable = new DLDHDTList.NodesIterator();

		@Override
		public boolean hasNext() {
			return iterable.hasNext(); // Verify if iterable nodes has a next node
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			return (E) iterable.next().getElement(); // Gets the next element
		}

		public void remove() {
			iterable.remove(); // Removes the iterable node
		}
	}

	private class NodesIterable implements Iterable<Node<E>> {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Iterator<Node<E>> iterator() {
			return new DLDHDTList.NodesIterator();
		}
	}

	private class NodesIterator implements Iterator<Node<E>> {

		/*
		 * current node contains element, returns on next .next(); can the preceding
		 * node be removed? removable means .remove() can be used removable means
		 * .remove() can be used
		 */
		private DNode<E> current = header; // First in position
		private DNode<E> preced = null;
		private boolean removable = false;

		public boolean hasNext() {
			return current != null;
		}

		public DLDHDTList.DNode<E> next() {
			if (!hasNext())
				throw new NoSuchElementException("Iterator is completed.");

			if (removable)
				preced = (preced == null ? header : preced.getNext());

			removable = true;
			DNode<E> ntr = current;
			current = current.getNext();
			return ntr;
		}

		/*
		 * 2nd if - removes the first node if possible else - removes the node after
		 * preceding
		 */
		public void remove() {
			if (!removable)
				throw new IllegalStateException("Can't remove this.");
			if (preced == null)
				header = header.getNext();
			else
				preced.setNext(preced.getNext().getNext());
			length--;
			removable = false;
		}

	}

}
