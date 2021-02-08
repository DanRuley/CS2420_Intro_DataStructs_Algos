package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class defines a generic singly linked list object and contains public
 * methods to add, remove, and retrieve elements, as well as determine aspects
 * of the list's current state.
 * 
 * @authors Aric Woodliff, Dan Ruley
 * @version February 2019
 */
public class SinglyLinkedList<E> implements List<E> {

	// Represents the number of items in the list.
	private int elementCount;
	// The start of the linked list.
	private Node<E> head;

	/**
	 * The Node class defines generic Node objects. These Node objects represent the
	 * various elements contained in the linked list.
	 */
	private class Node<T> {

		// This node's next
		public Node<T> next;
		// This node's value
		public T value;

		/**
		 * Constructs a node in the linked list based on the input values and next Node.
		 */
		public Node(T value, Node<T> next) {
			this.next = next;
			this.value = value;
		}
	}

	/**
	 * The constructor for the list. Initially, head is null and elementCount is 0.
	 */
	public SinglyLinkedList() {
		head = null;
		elementCount = 0;
	}

	@Override
	public void addFirst(E element) {
		elementCount++;
		head = new Node<E>(element, head);
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {

		// call addFirst if index = 0
		if (index == 0) {
			addFirst(element);
			return;
		}

		elementCount++;

		if (index >= elementCount || index < 0)
			throw new IndexOutOfBoundsException();

		// find node just prior to where we want the added node to be. Then set the
		// prior node's next to the added node.
		Node<E> prev = getPrevNode(index);
		prev.next = new Node<E>(element, prev.next);
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (elementCount == 0) {
			throw new NoSuchElementException();
		}
		return (E) head.value;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index >= elementCount || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		Node<E> temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}

		return temp.value;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if (elementCount == 0) {
			throw new NoSuchElementException();
		}
		E temp = head.value;
		head = head.next;
		elementCount--;
		return temp;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index >= elementCount || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		// edge case where index is 0: call removeFirst
		if (index == 0) {
			return removeFirst();
		}

		// find node before the node to be removed. set prev.next
		Node<E> temp = getPrevNode(index);
		Node<E> removed = temp.next;
		temp.next = temp.next.next;
		elementCount--;

		return removed.value;
	}

	@Override
	public int indexOf(E element) {

		int index = 0;
		Node<E> temp = head;

		// Iterate through the list, checking whether the value at current node is equal
		// to element and return the index if it is.
		while (index < elementCount) {
			if (temp.value.equals(element)) {
				return index;
			}
			index++;
			temp = temp.next;
		}

		// If there's no match, return -1.
		return -1;
	}

	@Override
	public int size() {

		return elementCount;
	}

	@Override
	public boolean isEmpty() {
		return elementCount == 0;
	}

	@Override
	public void clear() {
		this.head = null;
		elementCount = 0;

	}

	@Override
	public Object[] toArray() {
		Object[] items = new Object[elementCount];

		Node<E> temp = head;

		for (int i = 0; i < elementCount; i++) {
			items[i] = temp.value;
			temp = temp.next;
		}

		return items;
	}

	/**
	 * Helper method that returns the Node preceding the supplied index.
	 */
	private Node<E> getPrevNode(int index) {

		Node<E> temp = head;

		for (int i = 0; i < index - 1; i++) {
			temp = temp.next;
		}

		return temp;
	}
	

	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}

	private class SinglyLinkedListIterator implements Iterator<E> {

		//Keeps track of if an item can be removed (next has been called)
		public boolean canRemove;
		
		//pointer nodes
		public Node<E> prev = head;
		public Node<E> removeable = head;
		public Node<E> point = head;
		//index of pointer
		public int index = 0;

		@Override
		public boolean hasNext() {
			if (index < elementCount) {
				return true;
			}
			return false;
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			index++;
			canRemove = true;
			removeable = point;
			point = point.next;
			if (index >= 2) {
				prev = prev.next;
			}
			return removeable.value;
		}

		@Override
		public void remove() throws IllegalStateException {
			if (!canRemove) {
				throw new IllegalStateException();
			}

			if (index == 1) {
				head = point;
			}
			if (index >= 2) {
				prev.next = point;
			}

			canRemove = false;
			index--;
			elementCount--;

		}
	}
}
