package comprehensive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class defines an object that parses a given grammar file, generating a
 * hash map representation of the grammar. It also contains the parseRule method
 * which recursively generates a random phrases from the input grammar file,
 * appending it to a StringBuilder.
 * 
 * @authors Aric Woodliff, Dan Ruley
 * @version April 2019
 */
public class GrammarParser {

	/**
	 * The hash map that represents the given grammar. Non-terminal keys are mapped
	 * lists of all the values they can be expanded into
	 */
	private HashMap<String, ArrayList<String>> gMap;

	/** StringBuilder used to generate the phrase. */
	private StringBuilder sb;

	/** This reader parses the grammar file to generate the map. */
	private BufferedReader reader;

	/** Random object used to pick random terminals to use */
	private Random rnd;

	/** Keeps track of how many recursive calls have been made */
	private int recursCounter;

	/**
	 * Threshold designed to limit large numbers of recursive calls and excessively
	 * long phrases.
	 */
	private int thresh;

	/**
	 * Constructs a new GrammarParserAndPhraseGenerator from the given file.
	 */
	public GrammarParser(File f) {
		try {
			reader = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		gMap = new HashMap<>();
		sb = new StringBuilder();
		rnd = new Random();
		thresh = 5;
		recursCounter = 0;
		ruleGenerator();
	}

	/**
	 * Reads through the grammar file using a buffered reader, calling the
	 * ruleMapper() method when the reader is in between { } symbols.
	 */
	private void ruleGenerator() {

		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				if (line.length() > 0 && line.charAt(0) == '{')
					ruleMapper();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * For a given non-terminal key, this method finds all of the rules the key
	 * corresponds to, adds them to an ArrayList, and then adds the array to the
	 * hashmap.
	 */
	private void ruleMapper() {

		ArrayList<String> vals = new ArrayList<>();
		String key;

		try {
			key = reader.readLine();

			while (true) {
				String mapping = reader.readLine();
				if (mapping.charAt(0) != '}')
					vals.add(mapping);
				else
					break;
			}
			gMap.put(key, vals);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method parses an input grammar rule. It picks out non-terminals and
	 * terminals from the input string, recursively calling parseRule on the
	 * non-terminals and simply appending the terminals to the string builder. The
	 * recursive threshold seeks to prevent excessive recursive calls by invoking
	 * getShortestRule() instead of making more recursive calls.
	 */
	public void parseRule(String s) {

		int end;

		while (s.indexOf("<") >= 0) {

			// first item in string is non-terminal: call parseRule recursively until it is
			// expanded into a terminal.
			if (s.charAt(0) == '<') {

				end = s.indexOf(">");

				String key = s.substring(0, end + 1);

				s = s.substring(end + 1, s.length());

				String rule = gMap.get(key).get(rnd.nextInt(gMap.get(key).size()));

				recursCounter++;

				// if counter < threshold, keep recursing, else call getShortestRule to
				// terminate phrase earlier
				if (recursCounter < thresh)
					parseRule(rule);
				else
					getShortestRule(key);
			}

			// handles terminal expressions that are in the same line as a non-terminal.
			else {

				end = s.indexOf("<");

				// append the terminal
				sb.append(s.substring(0, end));

				s = s.substring(end, s.length());

			}
		}

		// append the terminal
		sb.append(s);

	}

	/**
	 * This method is designed to get the phrase generation to terminate and prevent
	 * excessive recursive calls. If the key maps to terminal value, it picks a
	 * terminal value and adds it to the string. If the key does not map to a
	 * terminal value, it finds the shortest non-terminal and parses it.
	 */
	private void getShortestRule(String key) {

		ArrayList<String> vals = gMap.get(key);
		String[] terminals = new String[vals.size()];
		String min = vals.get(0);
		int termSize = 0;

		// Iterate through the rules mapped to the input key, find the smallest one
		for (String item : vals) {

			if (!item.contains("<")) {
				terminals[termSize] = item;
				termSize++;
			}

			if (item.length() < min.length()) {
				min = item;
			}
		}

		// If there are no terminals, parse the shortest non-terminal expression
		if (min.contains("<"))
			parseRule(min);
		// If there are terminals, pick a random one and append it.
		else
			sb.append(terminals[rnd.nextInt(termSize)]);
	}

	/**
	 * Returns the map representation of the grammar file.
	 */
	public HashMap<String, ArrayList<String>> getMap() {
		return gMap;
	}

	/**
	 * Returns the random string that has been generated.
	 */
	public String fetchString() {
		return sb.toString();
	}

	/**
	 * Resets the GrammerParser, making it ready to generate a new phrase.
	 */
	public void reset() {
		sb = new StringBuilder();
		recursCounter = 0;
	}
}
