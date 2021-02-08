package Euler;

import java.math.BigInteger;
import java.util.ArrayList;

public class Seventeen {

	public static void main(String[] args) {
		ArrayList<String> nums = new ArrayList<>();
		String one = "one";
		String two = "two";
		String three = "three";
		String four = "four";
		String five = "five";
		String six = "six";
		String seven = "seven";
		String eight = "eight";
		String nine = "nine";
		String ten = "ten";
		String eleven = "eleven";
		String twelve = "twelve";
		String thirteen = "thirteen";
		String fourteen = "fourteen";
		String fifteen = "fifteen";
		String sixteen = "sixteen";
		String seventeen = "seventeen";
		String eighteen = "eighteen";
		String nineteen = "nineteen";
		String[] low = new String[] { "", one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve,
				thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen };
		String twenty = "twenty";
		String thirty = "thirty";
		String forty = "forty";
		String fifty = "fifty";
		String sixty = "sixty";
		String seventy = "seventy";
		String eighty = "eighty";
		String ninety = "ninety";
		String[] tens = new String[] { "", "", twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety };

		String hundred = "hundred";

		for (int i = 0; i < 1000; i++) {
			
			String number = "";
			String s = Integer.toString(i);

			if (s.length() == 3) {
				number += low[Character.getNumericValue(s.charAt(0))] + " hundred";
				if (i % 100 != 0) {
					number += " and ";
				}
				s = s.substring(1, 3);
			}
			if (s.length() == 2) {
				if (Integer.parseInt(s) < 20) {
					number += low[Integer.parseInt(s)];
				} else {
					number += tens[Character.getNumericValue(s.charAt(0))]
							+ low[Character.getNumericValue(s.charAt(1))];
				}
			}
			if (s.length() == 1) {
				number += low[Integer.parseInt(s)];
			}
			nums.add(number);
		}
		nums.add("onethousand");
		
		int lettercount = 0;
		for (String x: nums) {
			for (int i = 0; i < x.length(); i++) {
				if (Character.isLetter(x.charAt(i))) {
					lettercount++;
				}
			}
		}
		System.out.println(lettercount);
		
	}

	public static int parseNumGetDig(int n) {

		ArrayList<String> nums = new ArrayList<>();
		String one = "one";
		String two = "two";
		String three = "three";
		String four = "four";
		String five = "five";
		String six = "six";
		String seven = "seven";
		String eight = "eight";
		String nine = "nine";
		String ten = "ten";
		String eleven = "eleven";
		String twelve = "twelve";
		String thirteen = "thirteen";
		String fourteen = "fourteen";
		String fifteen = "fifteen";
		String sixteen = "sixteen";
		String seventeen = "seventeen";
		String eighteen = "eighteen";
		String nineteen = "nineteen";
		String[] low = new String[] { "", one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve,
				thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen };
		String twenty = "twenty";
		String thirty = "thirty";
		String forty = "forty";
		String fifty = "fifty";
		String sixty = "sixty";
		String seventy = "seventy";
		String eighty = "eighty";
		String ninety = "ninety";
		String[] tens = new String[] { "", "", twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety };

		String hundred = "hundred";

		for (int i = 100; i < 1000; i++) {
			
			String number = "";
			String s = Integer.toString(i);

			if (s.length() == 3) {
				
				number += low[Character.getNumericValue(s.charAt(0))] + " hundred ";
				if (i % 100 != 0)
					number += "and ";
				s = s.substring(1, 3);
			}
			if (s.length() == 2) {
				if (Integer.parseInt(s) < 20) {
					number += low[Integer.parseInt(s)];
				} else {
					number += tens[Character.getNumericValue(s.charAt(0))]
							+ low[Character.getNumericValue(s.charAt(1))];
				}
			}
			if (s.length() == 1) {
				number += low[Integer.parseInt(s)];
			}
			nums.add(number);
		}

		return 0;
	}

}
