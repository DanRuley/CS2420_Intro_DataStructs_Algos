package comprehensive;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class consists simply of a main method that takes two arguments from the
 * console: A grammar file to be read, and the number of random phrases to
 * generate. It then generates the indicated number of phrases.
 * 
 * @authors Dan Ruley, Aric Woodliff
 * @version April, 2019
 */
public class RandomPhraseGenerator {

	/**
	 * Takes the command line arguments, then creates a
	 * GrammarParserAndPhraseGenerator object given the input grammar file. Then it
	 * loops as many times as the user specifies, generating a new random phrase
	 * each time and printing it to the console.
	 */
	public static void main(String[] args) {

		BufferedWriter printer = new BufferedWriter(new OutputStreamWriter(System.out));

		GrammarParser h = new GrammarParser(new File(args[0]));
		
		Random r = new Random();
		
		try {
			for (int i = 0; i < Integer.parseInt(args[1]); i++) {
				h.parseRule("<start>");
				printer.write(h.fetchString() + "\n");
				h.reset();
			}

			printer.flush();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
