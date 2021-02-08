package lab04;

import java.util.ArrayList;

public class SwapHighAndLow {

	public static void main(String[] args) {
		String[] stuff = new String[]{"a", "b", "c", "d", "e", "f","g","h","i","j","k","l"};
		for (String s: stuff) {
			System.out.print(s + " ");
		}
		System.out.println();
		
		swapHighAndLow(stuff);
		
		for (String s: stuff) {
			System.out.print(s + " ");
		}
		
		ArrayList<Character> AL = new ArrayList<>();
		char c;
		for (c = 'a'; c <= 'z'; c++) {
			AL.add(c);
		}
		System.out.println();
		
	}
	
	
	public static <T> void swapHighAndLow(T[] inputArr) {
		
		int j = inputArr.length - 1;
		
		for (int i = 0; i < j ;i++) {
			T temp = inputArr[j];
			inputArr[j] = inputArr[i];
			inputArr[i] = temp;
			j--;
		}
	}
	
public static <T> void swapHighAndLowAL(ArrayList<T> inputArr) {
		
		int j = inputArr.size() - 1;
		
		for (int i = 0; i < j ;i++) {
			T temp = inputArr.get(j);
			inputArr.set(j, inputArr.get(i));
			inputArr.set(i, temp);
			j--;
		}
	}

	
}
