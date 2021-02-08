import java.util.ArrayList;

public class EulerOne {

	public static void main(String[] args) {

		ArrayList<Integer> fibbs = new ArrayList<>();
		fibbs.add(1);
		fibbs.add(2);

		ArrayList<Integer> evens = new ArrayList<>();
		evens.add(2);
		
		int sum = 0;
		boolean flag = true;

		for (int i = 1; i < 5000000; i++) {
			int temp = fibbs.get(i) + fibbs.get(i - 1);
			if (temp > 4000000) {
				break;
			}
			fibbs.add(temp);
			if (temp % 2 == 0) {
				evens.add(temp);
				
			}
		}
		
		for (Integer i : evens) {
			System.out.print(i + " ");
		}
		
	}
}
