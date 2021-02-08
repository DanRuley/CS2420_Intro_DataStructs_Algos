package Euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Twenty {
public static void main(String[] args) throws FileNotFoundException {
	Scanner s = new Scanner(new File("src/Euler/Names"));
	s.useDelimiter("\",");
	ArrayList<String> names = new ArrayList<>();
	while(s.hasNext()) {
		String x = s.next();
		names.add(x.substring(1,x.length()));
	}
	//Comparator<String> c = new OrderAlph();
	names.sort((String o1, String o2) -> o1.compareToIgnoreCase(o2));
	
	System.out.println("hi");
}

	
	

}


