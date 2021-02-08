package assign06;

import java.util.NoSuchElementException;

/**
 * This class defines a generic stack backed by a linked list.  It implements the stack interface.
 * 
 * @authors Dan Ruley, Aric Woodliff
 * @version February 2019
 */

public class LinkedListStack<E> implements Stack<E> {

	private SinglyLinkedList<E> backerList;
	
	public LinkedListStack() {
		backerList = new SinglyLinkedList<>();
	}
	
	@Override
	public void clear() {
		backerList.clear();
	}

	@Override
	public boolean isEmpty() {
		return backerList.size() == 0;
	}

	@Override
	public E peek() throws NoSuchElementException {
		return backerList.getFirst();
	}

	@Override
	public E pop() throws NoSuchElementException {
		E elem = backerList.getFirst();
		backerList.removeFirst();
		return elem;
	}

	@Override
	public void push(E element) {
		backerList.addFirst(element);
		
	}

	@Override
	public int size() {
		return backerList.size();
	}

}
