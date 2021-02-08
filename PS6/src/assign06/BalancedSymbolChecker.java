package assign06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Erin Parker && Aric Woodliff && Dan Ruley
 * @version February 2019
 */
public class BalancedSymbolChecker {

	/**
	 * Generates a message indicating whether the input file has unmatched symbols.
	 * (Use the helper methods below for constructing messages.)
	 * 
	 * @param filename - name of the input file to check
	 * @return a message indicating whether the input file has unmatched symbols
	 * @throws FileNotFoundException if the file does not exist
	 */
	public static String checkFile(String filename) throws FileNotFoundException {
		return checkFileHelper(filename);
	}

	/**
	 * Helper method that does the work of checking a file for balanced symbols.
	 * Checks for comments and quotation literals, and ignores any symbols contained
	 * within. Makes use of a stack for checking if a previously seen symbol 
	 * matches the one being read.
	 */
	private static String checkFileHelper(String filename) throws FileNotFoundException {

		String line = "";
		Scanner scn = new Scanner(new File(filename));
		
		int colNum = 0;
		int rowNum = 0;
		boolean inBlock = false;
		ArrayStack<Character> symbolStack = new ArrayStack<>();
		Character read;

		//Iterate through file with scanner.
		while (scn.hasNextLine()) {

			line = " " + scn.nextLine() + " ";
			rowNum++;
			colNum = 0;

			for (int i = 0; i < line.length(); i++, colNum++) {

				read = line.charAt(i);

				//Break to next line if a line counter is encountered.
				if (read == '/' && line.charAt(i + 1) == '/') {
					break;
				}

				//Comment or literal encountered, begin ignoring characters.
				if (((read == '/' && line.charAt(i + 1) == '*') || line.charAt(i) == '\'' || line.charAt(i) == '\"')
						&& !inBlock) {
					inBlock = true;
					continue;
				}

				//Check whether it's time to exit comment or literal block.
				if ((line.charAt(i) == '*' && line.charAt(i + 1) == '/')
						|| ((line.charAt(i) == '\'' || line.charAt(i) == '\"') && line.charAt(i - 1) != '\\')) {

					inBlock = false;
					continue;
				}
				//Keep going without checking symbols if within a block.
				if (inBlock) {
					continue;
				}

				//Push "left" symbols on the stack.
				if (read == '(' || read == '[' || read == '{') {
					symbolStack.push(read);
				}

				//Pop from stack if we read a "right" symbol
				if (read == ')' || read == ']' || read == '}') {
					
					//Case where stack is empty but we still saw a symbol, ' ' is expected.
					if (symbolStack.isEmpty()) {
						scn.close();
						return unmatchedSymbol(rowNum, colNum, read, ' ');
					}

					Character popped = symbolStack.pop();
					//Case where what we read doesn't match what came off the stack.
					if (read != getExpected(popped)) {
						scn.close();
						return unmatchedSymbol(rowNum, colNum, read, getExpected(popped));
					}
				}
			}
		}

		scn.close();
		//If we get through file and are still in block, we return unfinished comm message.
		if (inBlock) {
			return unfinishedComment();
		}
		//If stuff is left in stack, return unmatched at EOF message.
		if (symbolStack.size() != 0) {
			return unmatchedSymbolAtEOF(getExpected(symbolStack.pop()));
		}
		return allSymbolsMatch();
	}

	/**
	 * Helper method that returns the balanced expected symbol from an input symbol.
	 */
	private static char getExpected(char c) {
		if (c == '{')
			return '}';
		if (c == '[')
			return ']';
		else
			return ')';
	}

	/**
	 * Use this error message in the case of an unmatched symbol.
	 * 
	 * @param lineNumber     - the line number of the input file where the matching
	 *                       symbol was expected
	 * @param colNumber      - the column number of the input file where the
	 *                       matching symbol was expected
	 * @param symbolRead     - the symbol read that did not match
	 * @param symbolExpected - the matching symbol expected
	 * @return the error message
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected "
				+ symbolExpected + ", but read " + symbolRead + " instead.";
	}

	/**
	 * Use this error message in the case of an unmatched symbol at the end of the
	 * file.
	 * 
	 * @param symbolExpected - the matching symbol expected
	 * @return the error message
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Use this error message in the case of an unfinished comment (i.e., a file
	 * that ends with an open /* comment).
	 * 
	 * @return the error message
	 */
	private static String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Use this message when no unmatched symbol errors are found in the entire
	 * file.
	 * 
	 * @return the success message
	 */
	private static String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}