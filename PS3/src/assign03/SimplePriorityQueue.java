package assign03;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A class that defines a Simple Priority Queue Object. Items may be inserted
 * into the queue individually, or all at once. The minimum item will always be
 * at the front of the queue and able to be retrieved very quickly.
 * 
 * @authors Dan Ruley, Aric Woodliff
 * @version January 30, 2019
 */
@SuppressWarnings("unchecked")
public class SimplePriorityQueue<E> implements PriorityQueue<E>, Iterable<E> {

	private E[] queueData; // The array backing this priority queue
	private int queueSize; // The number of elements contained in this priority queue
	private Comparator<? super E> c; // Placeholder for comparator which will be supplied by user if they choose not
										// to use compareTo for insertion

	/**
	 * "Natural Order" version of the constructor.
	 */
	public SimplePriorityQueue() {
		queueData = (E[]) new Object[20];
		queueSize = 0;
		c = null;
	}

	/** Overloaded constructor that takes a Comparator object. */
	public SimplePriorityQueue(Comparator<? super E> comp) {
		queueData = (E[]) new Object[20];
		queueSize = 0;
		c = comp;
	}

	/**
	 * Retrieves, but does not remove, the minimum element in this priority queue.
	 * 
	 * @return the minimum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E findMin() throws NoSuchElementException {
		if (queueSize == 0) {
			throw new NoSuchElementException();
		}
		return queueData[queueSize - 1];
	}

	/**
	 * Retrieves and removes the minimum element in this priority queue.
	 * 
	 * @return the minimum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E deleteMin() throws NoSuchElementException {
		E minReturn = this.findMin();
		queueSize--;
		return minReturn;
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@Override
	public void insert(E item) {

		if (queueSize == 0) {
			queueData[0] = item;
			return;
		}
		
		// Perform binary search to find insertion index
		int index = binarySearch(item);
		
		// Increment queueSize to make room for item
		queueSize++;

		// Next, put it at the end
		queueData[queueSize - 1] = item;
		
		
		// If queueSize is = to the array size, we're going to need a bigger boat
		// (double array size)
		if (queueSize == (queueData.length)) {
			E[] tempData = (E[]) new Object[queueSize * 2];
			for (int i = 0; i < queueSize; i++) {
				tempData[i] = queueData[i];
			}
			queueData = tempData;
		}

		// Perform a series of swaps until it's the right place
		for (int i = queueSize - 1; i > index; i--) {
			E tmp = queueData[i];
			queueData[i] = queueData[i - 1];
			queueData[i - 1] = tmp;
		}

	}

	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (E nextItem : coll) {
			this.insert(nextItem);
		}
	}

	/**
	 * @return the number of elements in this priority queue
	 */
	@Override
	public int size() {
		return queueSize;
	}

	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return queueSize == 0;
	}

	/**
	 * Removes all of the elements from this priority queue. The queue will be empty
	 * when this call returns.
	 */
	@Override
	public void clear() {
		queueData = (E[]) new Object[20];
		queueSize = 0;
	}

	/**
	 * Performs a binary search to find the correct insertion index for a given
	 * item. This method works using both the natural ordering, or with a comparator
	 * provided by the user.
	 * 
	 * @param The item we are inserting
	 * @return The insertion index
	 */
	protected int binarySearch(E item) {

		int index = queueSize, low = 0, mid = 0, high = queueSize - 1;

		// Binary search using natural ordering
		if (c == null) {
			while (low <= high) {

				mid = (low + high) / 2;

				// Case where item to insert equals item at mid mid, return the index of mid.
				if (((Comparable<? super E>) queueData[mid]).compareTo(item) == 0) {
					index = mid + 1;
					break;
				}

				// Case where item to insert is greater than item at mid mid, keep going and
				// adjust high downwards.
				else if (((Comparable<? super E>) queueData[mid]).compareTo(item) < 0) {
					high = mid - 1;
				}

				// Case where item to insert is less than item at mid, keep going and adjust low
				// upwards
				else if (((Comparable<? super E>) queueData[mid]).compareTo(item) > 0) {
					low = mid + 1;
				}
			}
		}

		// If a comparator has been provided by the user, use this version which uses
		// compare instead of compareTo.
		else {
			while (low <= high) {

				mid = (low + high) / 2;

				if (c.compare(queueData[mid], item) == 0) {
					
				}

				if (c.compare(queueData[mid], item) < 0) {
					
				}

				if (c.compare(queueData[mid], item) > 0) {
					
				}
			}
		}

		// Once search is performed, index is = to mid, return this value.
		
		return index;

	}

	@Override
	public Iterator<E> iterator() {
		return new SimplePriorityQueueIterator();
	}

	private class SimplePriorityQueueIterator implements Iterator<E> {

		private int nextIndex;
		private boolean canRemove;

		@Override
		public boolean hasNext() {
			if (nextIndex < queueSize) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			nextIndex++;
			canRemove = true;

			return queueData[nextIndex - 1];
		}

		@Override
		public void remove() {

			if (!canRemove) {
				throw new IllegalStateException();
			}

			canRemove = false;
			nextIndex--;

			for (int i = nextIndex; i < queueSize - 1; i++) {
				queueData[i] = queueData[i + 1];
			}

			queueSize--;
		}

	}

}
