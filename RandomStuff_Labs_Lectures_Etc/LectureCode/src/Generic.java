import java.util.Comparator;

public class Generic <T> {
	
	public T genMeth(T rhs, T lhs) {
		
		((Comparable<? super T>)rhs).compareTo(lhs);
		
		Comparator c = new OrderStringLength();
		
		lasdkjf((String s1, String s2) -> s1.length() - s2.length());
				//(String s1, String s2) -> (s1.length() - s2.length());
		
		return rhs;
	}
	
	public void lasdkjf(Comparator<String> c) {
		Comparator c1 = c;
	}

	private class OrderStringLength implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			
			return o1.length() - o2.length();
		}
		
	}
}


