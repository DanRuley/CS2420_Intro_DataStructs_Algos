package Euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Eighteen {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<ArrayList<Integer>> numlists = new ArrayList<>();
		Scanner s = new Scanner(new File("src/Euler/triangle"));
		while (s.hasNextLine()) {
			Scanner s1 = new Scanner(s.nextLine());
			ArrayList<Integer> list = new ArrayList<>();
			while (s1.hasNext()) {
				list.add(s1.nextInt());
			}
			numlists.add(list);
		}
		
		System.out.println(getBiggestSum(numlists));
		
		
	}
	
	private static void generateDot(ArrayList<ArrayList<Integer>> list) throws FileNotFoundException {
		PrintWriter p = new PrintWriter(new File("src/Euler/output"));
		//row
		for (int i = 0; i < list.size() - 1; i++)
		{
			
			
			
		}
		
		
	}

	private static int getBiggestSum(ArrayList<ArrayList<Integer>> numlists) {

		int index = 0;
		int sum = 0;
		sum = numlists.get(0).get(0);
		for (int i = 0; i < numlists.size() - 1; i++) {
			
			//left > right
			if (numlists.get(i+1).get(index) > numlists.get(i+1).get(index+1)) {
				sum += numlists.get(i+1).get(index);
			}
			//right > left
			else {
				sum += numlists.get(i+1).get(index + 1);
				index++;
				}
			}

		return sum;
		
	}
}
