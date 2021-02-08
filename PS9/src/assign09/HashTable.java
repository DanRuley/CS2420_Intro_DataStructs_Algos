package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Represents a generic Hash Table consisting of Key Value pairs.  If the specified key has a well implemented hashCode method,
 * this hash table performs adds, searches, and removals in O(1) time.
 * @authors Dan Ruley, Subhan Gulistani
 * @version March, 2019
 */
public class HashTable<K, V> implements Map<K, V> {

	private ArrayList<LinkedList<MapEntry<K, V>>> table;

	private double lambda;
	private double lambdaThresh;

	private int initialCapacity;
	private int capacity;
	private int size;

	/**
	 * Constructs a hash table with a default capacity of 100.
	 */
	public HashTable() {

		this(100);

	}

	/**
	 * Overloaded constructer that constructs a hash table of with the specified
	 * initial capacity.
	 */
	public HashTable(int c) {
		
		lambda = 0;
		lambdaThresh = 2;
		initialCapacity = c;
		capacity = c;
		size = 0;
		
		//populate array with empty lists
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for (int i = 0; i < capacity; i++)
			table.add(new LinkedList<MapEntry<K, V>>());

	}

	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length)
	 */
	@Override
	public void clear() {

		//reset capacity and make new table of empty lists
		capacity = initialCapacity;
		size = 0;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for (int i = 0; i < capacity; i++)
			table.add(new LinkedList<MapEntry<K, V>>());

	}

	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	@Override
	public boolean containsKey(K key) {

		for (MapEntry<K, V> m : table.get(Math.abs(key.hashCode()) % capacity)) {
			if (m.getKey().equals(key)) {
				return true;
			}

		}
		return false;

	}

	/**
	 * Determines whether this map contains the specified value.
	 * 
	 * O(table length)
	 * 
	 * @param value
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	@Override
	public boolean containsValue(V value) {

		for (LinkedList<MapEntry<K, V>> l : table) {
			for (MapEntry<K, V> m : l) {
				if (m.getValue().equals(value))
					return true;
			}

		}
		return false;
	}

	/**
	 * Returns a List view of the mappings contained in this map, where the ordering
	 * of mapping in the list is insignificant.
	 * 
	 * O(table length)
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	@Override
	public List<MapEntry<K, V>> entries() {

		List<MapEntry<K, V>> list = new ArrayList<>();

		for (LinkedList<MapEntry<K, V>> l : table) {
			for (MapEntry<K, V> m : l) {
				list.add(m);
			}

		}
		return list;
	}

	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	@Override
	public V get(K key) {

		for (MapEntry<K, V> m : table.get(Math.abs(key.hashCode()) % capacity)) {
			if (m.getKey().equals(key)) {
				return m.getValue();
			}
		}

		return null;
	}

	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * O(1)
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 * Associates the specified value with the specified key in this map. (I.e., if
	 * the key already exists in this map, resets the value; otherwise adds the
	 * specified key-value pair.)
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V put(K key, V value) {

		lambda = (double) size / capacity;

		// If lambda is over the threshold, we're going to need a bigger boat.
		if (lambda > lambdaThresh)
			increaseCap();

		int index = Math.abs(key.hashCode()) % capacity;

		for (MapEntry<K, V> m : table.get(index)) {
			if (m.getKey().equals(key)) {
				V temp = m.getValue();
				m.setValue(value);
				return temp;
			}
		}

		table.get(index).add(new MapEntry<K, V>(key, value));
		size++;

		return null;

	}

	/**
	 * Doubles capacity and copies items over to the new backing array by calling
	 * put() for every value. Since capacity has been increased, these items will be
	 * "re-hashed" to new indices.
	 * 
	 * This is called when the load factor is > 2.
	 */
	private void increaseCap() {

		capacity *= 2;

		ArrayList<MapEntry<K, V>> list = (ArrayList<MapEntry<K, V>>) this.entries();

		table = new ArrayList<LinkedList<MapEntry<K, V>>>(capacity);

		for (int i = 0; i < capacity; i++)
			table.add(new LinkedList<MapEntry<K, V>>());

		// clear out size so it does not get doubled during resizing
		size = 0;
		for (MapEntry<K, V> m : list) {
			this.put(m.getKey(), m.getValue());
		}

	}

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * O(1)
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V remove(K key) {
		int index = Math.abs(key.hashCode()) % capacity;

		for (MapEntry<K, V> m : table.get(index)) {
			if (m.getKey().equals(key)) {
				V val = m.getValue();
				table.get(index).remove(m);
				size--;
				return val;
			}
		}

		return null;
	}

	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1)
	 * 
	 * @return the number of mappings in this map
	 */
	public int size() {
		return size;
	}

}
