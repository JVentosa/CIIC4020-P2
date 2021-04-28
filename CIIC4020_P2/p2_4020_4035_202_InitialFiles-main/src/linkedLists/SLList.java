/**
 * 
 */
package linkedLists;

import java.util.Iterator;
import java.util.NoSuchElementException;

import interfaces.LinkedList;
import interfaces.Node;
import linkedLists.SLFLList.SNode;

/**
 * @author pirvos
 *
 */
@SuppressWarnings("unused")
public class SLList<E> implements LinkedList<E> {
	private SNode<E> first;
	private int length;

	public SLList() { // to create an empty list instance
		first = null;
		length = 0;
	}

	public void addFirstNode(Node<E> nuevo) {
		// Pre: nuevo is not a node in the list
		((SNode<E>) nuevo).setNext(first);
		first = (SNode<E>) nuevo;
		length++;
	}

	public void addLastNode(Node<E> nuevo) {
		SNode<E> sNuevo = (SNode<E>) nuevo;
		sNuevo.setNext(null);
		if (length == 0)
			first = sNuevo;
		else { // find current last node and add the new one after that last node
			SNode<E> p = first;
			while (p.getNext() != null)
				p = p.getNext();
			p.setNext(sNuevo);
		}
		length++;
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext());
		((SNode<E>) target).setNext((SNode<E>) nuevo);
		length++;
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list

		if (target == first)
			this.addFirstNode(nuevo);
		else {
			Node<E> prevNode = findNodePrevTo(target);
			this.addNodeAfter(prevNode, nuevo);
		}
	}

	private Node<E> findNodePrevTo(Node<E> target) {
		// Pre: target is a node in the list
		if (target == first) // the list is empty
			return null;
		else {
			SNode<E> prev = first;
			while (prev != null && prev.getNext() != target)
				prev = prev.getNext();
			return prev;
		}
	}

	public Node<E> getLastNode() {
		if (first == null) // the list is empty
			return null;
		else {
			SNode<E> curr = first;
			while (((SNode<E>) curr).getNext() != null)
				curr = curr.getNext();
			return curr;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public SLList<E> clone() throws CloneNotSupportedException {
		return (SLList<E>) super.clone(); // Uses the built-in .clone() method with super, making a deep clone of the
											// SLList

	}

	public Node<E> getNodeAfter(Node<E> target) {
		// Pre: target is a node in the list
		SNode<E> aNode = ((SNode<E>) target).getNext();
		if (aNode == null)
			return null;
		else
			return aNode;
	}

	public Node<E> getNodeBefore(Node<E> target) {
		// Pre: target is a node in the list
		if (target == first)
			return null;
		else
			return findNodePrevTo(target);
	}

	public int length() {
		return this.length;
	}

	public void removeNode(Node<E> target) {
		// Pre: target is a node in the list; hence, the list is not empty

		if (target == first)
			first = first.getNext();
		else {
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target);
			prevNode.setNext(((SNode<E>) target).getNext());
		}
		((SNode<E>) target).clean(); // clear all references from target
		length--;
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (first == null)
			throw new NoSuchElementException("getFirstNode() : linked list is empty...");

		// the linked list is not empty....
		return first;
	}

	/**
	 * Prepares every node so that the garbage collector can free its memory space,
	 * at least from the point of view of the list. This method is supposed to be
	 * used whenever the list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any) from the linked list
	 */
	private void destroy() {
		while (first != null) {
			SNode<E> nnode = first.getNext();
			first.setElement(null);
			first.setNext(null);
			first = nnode;
		}
	}

	/**
	 * The execution of this method removes all the data nodes from the current
	 * instance of the list. The list becomes an empty list. Notice that in general
	 * this is not the same as destroy(). However, in this type of list it can be
	 * done by first invoking the destroy method and then make length = 0.
	 * 
	 * NOTE: For other types of list, this strategy may not be correct as it is
	 * here.
	 */
	public void makeEmpty() {
		destroy();
		length = 0;
	}

	public Node<E> createNewNode() {
		return new SNode<E>();
	}

	@Override
	public Iterable<Node<E>> nodes() {
		return new NodesIterable();
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementsIterator();
	}

	/**
	 * Class to represent a node of the type used in singly linked lists.
	 * 
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

		public SNode(T data) {
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

	private class ElementsIterator implements Iterator<E> {

		NodesIterator iterable = new NodesIterator();

		@Override
		public boolean hasNext() {
			return iterable.hasNext(); // Verify if iterable nodes has a next node
		}

		@Override
		public E next() {
			return iterable.next().getElement(); // Gets the next element
		}

		public void remove() {
			iterable.remove(); // Removes the iterable node
		}
	}

	private class NodesIterable implements Iterable<Node<E>> {

		@Override
		public Iterator<Node<E>> iterator() {
			return new NodesIterator();
		}

	}

	private class NodesIterator implements Iterator<Node<E>> {

		/*
		 * current node contains element, returns on next .next(); can the preceding
		 * node be removed? removable means .remove() can be used removable means
		 * .remove() can be used
		 */
		private SNode<E> current = first; // First in position
		private SNode<E> preced = null;
		private boolean removable = false;

		public boolean hasNext() {
			return current != null;
		}

		public SNode<E> next() {
			if (!hasNext())
				throw new NoSuchElementException("Iterator is completed.");

			if (removable)
				preced = (preced == null ? first : preced.getNext());

			removable = true;
			SNode<E> ntr = current;
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
				first = first.getNext();
			else
				preced.setNext(preced.getNext().getNext());
			length--;
			removable = false;
		}

	}

}
