package assign09;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester Class for the HashTable class.
 * @authors Subhan Gulistani, Dan Ruley
 * @version March, 2019
 */
public class HashTableTester {

	Random r;
	HashTable<StudentGoodHash, Double> tt;
	HashTable<StudentGoodHash, Double> t;
	
	MapEntry<StudentGoodHash,Double> g;
	MapEntry<StudentGoodHash,Double> e;
	MapEntry<StudentGoodHash,Double> ad;
	MapEntry<StudentGoodHash,Double> al;
		
	StudentGoodHash grace;
	StudentGoodHash edsgar;
	StudentGoodHash ada;
	StudentGoodHash alan;
	StudentGoodHash a;
	StudentGoodHash b;
	StudentGoodHash c;
	StudentGoodHash d;
	StudentGoodHash asdf;
	ArrayList<MapEntry<StudentGoodHash,Double>> list;

	@BeforeEach
	public void setUp() {
		r = new Random();
		r.setSeed(314);
		
		alan = new StudentGoodHash(1019999, "Alan", "Turing");
		ada = new StudentGoodHash(1004203, "Ada", "Lovelace");
		edsgar = new StudentGoodHash(1010661, "Edsgar", "Dijkstra");
		grace = new StudentGoodHash(1019941, "Grace", "Hopper");
		a = new StudentGoodHash(1119999, "asdf", "asdjklf");
		b = new StudentGoodHash(1114203, "asdjkl", "127893");
		c = new StudentGoodHash(1210661, "123", "Disdfstra");
		d = new StudentGoodHash(1219941, "321", "asder");
		asdf = new StudentGoodHash(1111, "123", "123");
		
		t = new HashTable<StudentGoodHash, Double>();
		t.put(alan, 3.2);
		t.put(ada, 3.5);
		t.put(edsgar, 3.8);
		t.put(grace, 4.0);
		
		list = new ArrayList<>();
		g = new MapEntry<>(grace, 4.0);
		e = new MapEntry<>(edsgar, 3.8);
		ad = new MapEntry<>(ada, 3.5);
		al = new MapEntry<>(alan, 3.2);
		list.add(g);
		list.add(e);
		list.add(ad);
		list.add(al);

		tt = new HashTable<StudentGoodHash, Double>(7);
		tt.put(alan, 3.2);
		tt.put(ada, 3.5);
		tt.put(edsgar, 3.8);
		tt.put(grace, 4.0);
		tt.put(a, 1.0);
		tt.put(b, 2.0);
		tt.put(c, 3.0);
		tt.put(d, 4.0);	
	}
	
	@Test
	void testContains()
	{
		assertTrue(tt.containsKey(alan));
		assertTrue(tt.containsKey(ada));
		assertTrue(tt.containsKey(edsgar));
		assertTrue(tt.containsKey(grace));
		assertTrue(tt.containsKey(a));
		assertTrue(tt.containsKey(b));
		assertTrue(tt.containsKey(c));
		assertTrue(tt.containsKey(d));
		assertFalse(tt.containsKey(asdf));
		
		assertTrue(tt.containsValue(3.2));
		assertTrue(tt.containsValue(3.5));
		assertTrue(tt.containsValue(3.8));
		assertTrue(tt.containsValue(4.0));
		assertTrue(tt.containsValue(1.0));
		assertTrue(tt.containsValue(2.0));
		assertTrue(tt.containsValue(3.0));
		assertTrue(tt.containsValue(4.0));
		assertFalse(tt.containsValue(5.0));
	}

	@Test
	void testBasicHTMethods() {

		assertTrue(((Double) 4.0).equals(t.get(grace)));
		assertEquals(4, t.size());
		assertFalse(t.isEmpty());

		t.remove(ada);
		assertEquals(3, t.size());
		assertFalse(t.containsKey(ada));
		assertFalse(t.containsValue(3.5));

		t.clear();
		assertTrue(t.isEmpty());

	}

	@Test
	void testHTMethodsLargeTable() {
		HashTable<StudentGoodHash, Integer> ht = new HashTable<>();

		buildRandomHT(ht, 10000);

		assertEquals(10000, ht.size());

		ht.put(alan, 5);
		assertEquals(10001, ht.size());
		assertEquals(5, (int) ht.get(alan));
		assertTrue(ht.containsKey(alan));
		assertTrue(ht.containsValue(5));
		ht.remove(alan);
		assertFalse(ht.containsKey(alan));

		buildRandomHT(ht, 10000);

		assertEquals(20000, ht.size());

		ht.clear();

		assertEquals(0, ht.size());
		assertTrue(ht.isEmpty());
		ht.put(alan, 5);
		assertFalse(ht.isEmpty());

	}

	@Test
	void testPutAndContainsBehavior() {

		HashTable<StudentGoodHash, Integer> ht = new HashTable<>();

		buildRandomHT(ht, 20);

		assertNull(ht.put(ada, 111111111));
		assertTrue(ht.containsValue(111111111));

		assertEquals(111111111, (int) ht.put(ada, 6));

		assertEquals(6, (int) ht.get(ada));
		assertFalse(ht.containsValue(111111111));
	}

	@Test
	void testHardCodeRemoves() {
		assertEquals(8,tt.size());
		
		assertEquals((Double)3.2,tt.remove(alan));
		assertEquals(null,tt.remove(alan));
		
		assertEquals((Double)3.5,tt.remove(ada));
		assertEquals(null, tt.remove(ada));
		
		assertEquals((Double)3.8,tt.remove(edsgar));
		assertEquals(null,tt.remove(edsgar));
		
		assertEquals((Double)4.0,tt.remove(grace));
		assertEquals(null,tt.remove(grace));
		
		assertEquals((Double)1.0,tt.remove(a));
		assertEquals(null,tt.remove(a));
		
		assertEquals((Double)2.0,tt.remove(b));
		assertEquals(null,tt.remove(b));
		
		assertEquals((Double)3.0,tt.remove(c));
		assertEquals(null,tt.remove(c));
		
		assertEquals((Double)4.0,tt.remove(d));
		assertEquals(null,tt.remove(d));
		
		assertEquals(0,tt.size());
		assertFalse(tt.containsKey(alan));
		assertFalse(tt.containsKey(a));
		assertFalse(tt.containsValue(4.0));
		
	}
	
	@Test
	void testEntries() {
		ArrayList<MapEntry<StudentGoodHash,Double>> tableList = (ArrayList<MapEntry<StudentGoodHash, Double>>) t.entries();
		for (MapEntry<StudentGoodHash,Double> m : list) {
			assertTrue(tableList.contains(m));
		}
	}
	
	@Test
	void testHTMethodsDoubleStringTable() {
		HashTable<Double, String> ht = new HashTable<>();
		ht.put(3.14, "pi");
		buildRandomHTDoubleString(ht,5000);
		assertTrue(ht.size() == 5001);
		assertTrue(ht.containsKey(3.14));
		assertTrue(ht.containsValue("pi"));
		assertTrue(ht.get(3.14).equals("pi"));
		assertTrue(ht.entries().size() == 5001);
		assertTrue(ht.put(3.14, "Pi").equals("pi"));
		assertTrue(ht.get(3.14).equals("Pi"));
		
	}
	
	

	private void buildRandomHT(HashTable<StudentGoodHash, Integer> ht, int size) {
		char[] c = new char[26];

		for (int i = 0; i < 26; i++) {
			c[i] = (char) (97 + i);
		}

		int n = 0;
		for (int j = 0; j < size; j++) {

			n = r.nextInt(500000);

			String x = "";

			String y = "";

			for (int i = 1; i < 9; i++) {
				x = x + c[r.nextInt(26)];
			}

			for (int i = 2; i < 9; i++) {
				y = y + c[r.nextInt(26)];
			}

			StudentGoodHash s = new StudentGoodHash(n, x, y);
			ht.put(s, (Integer) r.nextInt(1000000));
		}
	}
	
	

	private void buildRandomHTDoubleString(HashTable<Double, String> ht, int size) {
		char[] c = new char[26];

		for (int i = 0; i < 26; i++) {
			c[i] = (char) (97 + i);
		}

		int n = 0;
		int n1 = 0;
		
		for (int j = 0; j < size; j++) {

			n = r.nextInt(500000);
			n1 = r.nextInt(500000);
						
			String x = "";

			for (int i = 1; i < 9; i++) {
				x = x + c[r.nextInt(26)];
			}

			Double num = (double) n / n1;
			ht.put(num, x);
		}
	}

}
