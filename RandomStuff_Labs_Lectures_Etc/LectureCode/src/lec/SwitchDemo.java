package lec;

/**
 * A program to demonstrate how switch statements.
 * 
 * @author Erin Parker & CS 2420 class
 * @version January 9, 2019
 */
public class SwitchDemo {
	
	public static void main(String[] args) {
				
		char letter = 'm';
		int number = -1;
		
		// FILL IN with switch statement to map a character to an integer
		// as is done by an alphanumeric telephone keypad
		
		switch(letter) {
			case 'a': case 'b': case 'c': 
				number = 2;
				break;
			case 'd': case 'e': case 'f':
				number = 3;
				break;
			case 'g': case 'h': case 'i':
				number = 4;
				break;
			case 'j': case 'k': case 'l':
				number = 5;
				break;
			case 'm': case 'n': case 'o':
				number = 6;
				break;
			case 'p': case 'q': case 'r': case 's': 
				number = 7;
				break;
			case 't': case 'u': case 'v': 
				number = 8;
				break;
			case 'w': case 'x': case 'y': case 'z':
				number = 9;
				break;		
			default: 
				System.out.println("You must use a letter a-z.");
				System.exit(0);
		}

		System.out.println("Letter " + letter + " corresponds to number " + number + ".");
	}
}