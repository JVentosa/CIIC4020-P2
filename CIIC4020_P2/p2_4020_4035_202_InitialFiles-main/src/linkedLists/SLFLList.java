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

/* 
 * Jose Carlos Ventosa Rodriguez
 * 802-19-8619, CIIC4020 - 086/050L
 * jose.ventosa1@upr.edu
 */

public class SLFLList<E> implements LinkedList<E> {
	private SNode<E> first, last; // reference to the first node and to the last node
	int length;

	public SLFLList() { // to create an empty list instance
		first = last = null;
		length = 0;
	}

	/*
	 * First sets the new node into the Linked List, then sets the first = to the
	 * new node length increases by 1
	 */
	public void addFirstNode(Node<E> nuevo) {
		((SNode<E>) nuevo).setNext(first);
		first = ((SNode<E>) nuevo);
		length++;

	}

	/*
	 * Works by finding new, getting target's next node as the new one Then actually
	 * setting target's next node as the new one length increases by 1
	 */
	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext());
		((SNode<E>) target).setNext((SNode<E>) nuevo);
		length++;
	}

	/*
	 * If the target is the first node Directly add the node at the beginning of the
	 * list Makes the first = to the new node Otherwise, get the previous node to
	 * the target, and set the node as the next one from the previous
	 */
	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if (target == first) {
			this.addFirstNode(nuevo);
			first = ((SNode<E>) nuevo);
		} else {
			Node<E> prev = getNodeBefore(target);
			this.addNodeAfter(prev, nuevo);
		}
	}

	/*
	 * Getter for the first node in the Linked List
	 */
	public Node<E> getFirstNode() {
		if (first == null)
			return null;
		else
			return first;
	}

	/*
	 * Getter for the last node in the Linked List
	 */
	public Node<E> getLastNode() {
		if (first == null)
			return null;
		else {

			/*
			 * Goes through every single Node in the linked list, if current == null, it
			 * will return the last one (current), otherwise, continue iterating through the
			 * LList
			 */
			SNode<E> current = first;
			while (((SNode<E>) current).getNext() != null)
				current = current.getNext();
			return current;
		}

	}

	/*
	 * Creates a new node is the next of the target, if next is nothing, then return null,
	 * otherwise, returns the new node that is the next one
	 */
	public Node<E> getNodeAfter(Node<E> target) {
		SNode<E> nextNode = ((SNode<E>) target).getNext();
		if (nextNode == null)
			return null;
		else
			return nextNode;
	}
/*
 * Checks if the target is the first one, if so, return null (first doesn't have a preceding node)
 * Otherwise, runs through every single Node in the list until it finds the target, 
 * return target
 */
	public Node<E> getNodeBefore(Node<E> target) {
		if (target == first)
			return null;
		else {
			SNode<E> prevNode = first;
			while (prevNode != null && prevNode.getNext() != target)
				prevNode = prevNode.getNext();
			return prevNode;
		}

	}

	public int length() {
		return this.length;
	}
	/*
	 * Checks if first Node is the target, if so, puts both next and element to null (.clean())
	 * else if last, then gets the node previous to last and .clean() last
	 * else, makes a new node and checks the node before the target,
	 *  sets the next as the target and .clean() it
	 */
	public void removeNode(Node<E> target) {
		if (target == first)
			first = first.getNext();
		else if (target == last) {
			Node<E> prevNode = getNodeBefore(last);
			last = (SNode<E>) prevNode;
		} else {
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target);
			prevNode.setNext(((SNode<E>) target).getNext());
		}
		((SNode<E>) target).clean();
		length--;
	}

	public Node<E> createNewNode() {
		return new SNode<E>();
	}

	@Override
	public Iterator<E> iterator() {
		return this.iterator();
	}
	/*
	 * Goes to last node and sets a new Node after it
	 * last becomes new Node
	 * length increases by 1
	 */
	@Override
	public void addLastNode(Node<E> newNode) {
		((SNode<E>) newNode).setNext(last);
		last = (SNode<E>) newNode;
		length++;
	}
	/*
	 * I have 0 clue what to do here, thank you for coming to my Ted-Talk
	 */
	@Override
	public Iterable<Node<E>> nodes() {
		// TODO Auto-generated method stub
		return null;
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

}
