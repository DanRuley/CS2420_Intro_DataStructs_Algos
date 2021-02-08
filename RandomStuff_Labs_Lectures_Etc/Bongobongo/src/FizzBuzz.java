import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class FizzBuzz {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> shuffle = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(i);
			shuffle.add(i);
		}
		
		Collections.shuffle(shuffle);
		
		double avg = 0;
		
		for (int i = 0; i < 1000; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.addAll(shuffle);
			avg += bozosort(temp,list);
		}
		System.out.println("Avg over 10000 tries: " + (double) (avg / 1000));
	}

	public static int bozosort(ArrayList<Integer> arr, ArrayList<Integer> check) {
		Random rng = new Random();
		int tally = 0;
		
		
		
		while (!check.equals(arr)) {
			int randindex1 = rng.nextInt(arr.size());
			int randindex2 = rng.nextInt(arr.size());
			int temp = arr.get(randindex1);
			arr.set(randindex1, arr.get(randindex2));
			arr.set(randindex2, temp);
			tally++;
		}
		System.out.println("done in " + tally + " tries");
		return tally;
	}

}