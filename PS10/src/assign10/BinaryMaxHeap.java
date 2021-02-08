package assign10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class defines a Priority Max Queue that is backed by a binary max heap.
 * It is generic and will accept types that either are Comparable, or come with
 * a Comparator provided by the user.
 * 
 * @authors Dan Ruley, Aric Woodliff
 * @version April 2019
 */
@SuppressWarnings("unchecked")
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
	
	
	public static void main(String[] args) {
		
		ArrayList<Integer> al = new ArrayList<>();
		
		for (int i = 0; i < 10; i ++) {
			al.add(i);
		}
		
		Collections.shuffle(al);
		
		BinaryMaxHeap<Integer> h = new BinaryMaxHeap<>(al);
		h.generateDot();
	
	}

	private E[] heapArr;
	private int size;
	private Comparator<? super E> c;

	/**
	 * Zero argument constructor, creates an empty BinaryMaxHeap. Comparisons will
	 * be based on natural order.
	 */
	public BinaryMaxHeap() {
		heapArr = (E[]) new Object[100];
		size = 0;
	}

	/**
	 * Creates an empty BinaryMaxHeap with the provided Comparator.
	 */
	public BinaryMaxHeap(Comparator<? super E> c) {
		this.c = c;
		heapArr = (E[]) new Object[100];
		size = 0;
	}

	/**
	 * Creates a BinaryMaxHeap containing the items in the input list. Comparisons
	 * will be based on natural order.
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		this(list, null);
	}

	/**
	 * Creates an empty BinaryMaxHeap with the provided Comparator and the items in
	 * the input list.
	 */
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> c) {
		this.c = c;

		if (list.size() == 0) {
			heapArr = (E[]) new Object[100];
			size = 0;
			return;
		}

		size = list.size();
		// initialize array with some extra space
		heapArr = (E[]) new Object[size * 2 + 2];
		for (int i = 0; i < list.size(); i++) {
			heapArr[i] = list.get(i);
		}

		buildHeap();

	}

	/**
	 * Adds the given item to this priority queue. O(1) in the average case, O(log
	 * N) in the worst case
	 * 
	 * @param item
	 */
	@Override
	public void add(E item) {
		size++;

		if (size == heapArr.length) {
			resize();
		}

		heapArr[size - 1] = item;
		percolateUp(size - 1);

	}

	/**
	 * Returns, but does not remove, the maximum item this priority queue. O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("Error, no elements in the queue.");
		return heapArr[0];
	}

	/**
	 * Returns and removes the maximum item this priority queue. O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("Error, no elements in the queue.");

		E max = heapArr[0];
		heapArr[0] = heapArr[size - 1];
		heapArr[size - 1] = null;
		percolateDown(0);
		size--;
		return max;
	}

	/**
	 * Returns the number of items in this priority queue. O(1)
	 */
	@Override
	public int size() {

		return size;
	}

	/**
	 * Returns true if this priority queue is empty, false otherwise. O(1)
	 */
	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	/**
	 * Empties this priority queue of items. O(1)
	 */
	@Override
	public void clear() {
		heapArr = (E[]) new Object[100];
		size = 0;
	}

	/**
	 * Creates and returns an array of the items in this priority queue, in the same
	 * order they appear in the backing array. O(N)
	 * 
	 * (NOTE: This method is needed for grading purposes. The root item must be
	 * stored at index 0 in the returned array, regardless of whether it is in
	 * stored there in the backing array.)
	 */
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = heapArr[i];
		}
		return result;
	}

	/**
	 * Find the right index to begin reverse level order percolations, then
	 * percolateDown one by one. O(N).
	 */
	private void buildHeap() {
		int start = (size - 2) / 2;
		for (int i = start; i >= 0; i--) {
			percolateDown(i);
		}
	}

	/**
	 * Helper method that takes an item at the specified index and percolates it
	 * down the heap until order property is satisfied. O(LogN) in the average and
	 * worst cases.
	 * 
	 * @param index - starting index for percolation
	 */
	private void percolateDown(int index) {

		while (true) {
			int left = this.getLeftChild(index);
			int right = this.getRightChild(index);

			// No children - percolation is over
			if (heapArr[left] == null && heapArr[right] == null)
				return;

			// Given the structure property, the only child that will ever be null is the
			// right one. Swap and end percolation.
			if (heapArr[right] == null) {
				if (innerCompare(heapArr[left], heapArr[index]) > 0) {
					swapElements(index, left);
				}
				return;
			}

			// If it has two children, compare and swap if needed.
			if (heapArr[left] != null && heapArr[right] != null) {

				// need to swap with left child
				if ((innerCompare(heapArr[left], heapArr[right]) >= 0)
						&& (innerCompare(heapArr[left], heapArr[index]) > 0)) {
					swapElements(index, left);
					index = left;
					continue;
				}

				// need to swap with right child
				if ((innerCompare(heapArr[right], heapArr[left]) >= 0)
						&& (innerCompare(heapArr[right], heapArr[index]) > 0)) {
					swapElements(index, right);
					index = right;
					continue;
				}

				// Case where there are two children but it is in heap order - end percolation.
				return;
			}
		}
	}

	/**
	 * Compares and swaps the element at this index with its parent and continues to
	 * swap until it in proper heap order. O(1)
	 * 
	 * @param index - starting index for percolation
	 */
	private void percolateUp(int index) {
		boolean keepGoing = true;

		// Keep comparing with the parent and swap if neccessary.
		while (keepGoing && index > 0) {
			int parentInd = getParent(index);

			if (innerCompare(heapArr[parentInd], heapArr[index]) < 0) {
				swapElements(index, parentInd);
				index = parentInd;
			} else {
				return;
			}
		}
	}

	/**
	 * Resizes the array and copies elements over once size exceeds the table
	 * capacity.
	 */
	private void resize() {
		E[] newHeapArr = (E[]) new Object[this.heapArr.length * 2];

		for (int i = 0; i < this.heapArr.length; i++) {
			newHeapArr[i] = this.heapArr[i];
		}
		this.heapArr = newHeapArr;
	}

	/**
	 * Swaps the elements at the two input indices.
	 */
	private void swapElements(int index1, int index2) {
		E temp = heapArr[index1];
		heapArr[index1] = heapArr[index2];
		heapArr[index2] = temp;
	}

	/**
	 * Compares the left hand element with the right hand element and returns the
	 * integer result. If this heap was constructed without a comparator, the method
	 * uses the natural ordering. If a comparator was provided, it uses the
	 * Comparator compare version.
	 */
	private int innerCompare(E lhs, E rhs) {

		if (c == null) {
			return (((Comparable<? super E>) lhs).compareTo(rhs));
		} else
			return c.compare(lhs, rhs);
	}

	/**
	 * Returns the index of the parent of the element at the input index.
	 */
	private int getParent(int index) {
		return (index - 1) / 2;
	}

	/**
	 * Returns the index of the leftChild of the element at the input index.
	 */
	private int getLeftChild(int index) {
		return 2 * index + 1;
	}

	/**
	 * Returns the index of the leftChild of the element at the input index.
	 */
	private int getRightChild(int index) {
		return 2 * index + 2;
	}
	
	private void generateDot() {
		String s = " ";
		String nl = "\n";
		try {
			PrintWriter p = new PrintWriter(new File("src/assign10/DOT"));
			
			p.write("Digraph G " + nl + "{" + nl);
			
			if (size == 1)
				p.write(heapArr[0].toString() + nl);
			if (size > 1) {
				for (int i = 0; i < size / 2; i++) {
					p.write("v"+ heapArr[i].toString() + " -> " + "v" + heapArr[getLeftChild(i)].toString() + nl);
					if (heapArr[getRightChild(i)] != null) {
						p.write("v" + heapArr[i].toString() + " -> " + "v" + heapArr[getRightChild(i)].toString() + nl);
					}
				}
			}
			p.write("}");
			p.flush();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
			
	}
	
}
