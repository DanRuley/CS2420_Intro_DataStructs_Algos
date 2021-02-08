package lab04;

import java.util.Arrays;
import java.util.Random;

public class Lab04 {

	public static void main(String[] args) {
		
		Random rand = new Random();
		System.out.println(Arrays.toString(permuteArray(10)));
		System.out.println(Arrays.toString(permuteArray(20)));
		System.out.println(Arrays.toString(permuteArray(30)));
		System.out.println(Arrays.toString(permuteArray(40)));
		
		for (int i = 0; i < 100000000; i++) {
			System.out.println(Arrays.toString(permuteArray(rand.nextInt(100))));
		}
		
	}
	
	public static int[] permuteArray(int size) {
		int arr[] = new int[size];
		Random r = new Random();
		
		//setup ordered array with values from 0 to 99
		for (int i = 0; i < size; i++) {
			arr[i] = i;
		}
		
		//swap around values randomly
		for (int i = 0; i < size; i++) {
			int randomIndex = r.nextInt(arr.length);
			int temp = arr[i];
			arr[i] = arr[randomIndex];
			arr[randomIndex] = temp;
		}
		
		return arr;
	
	}
}
