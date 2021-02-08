package assign09;

import java.text.DecimalFormat;

/**
 * Student class with a "Good" hashcode. Results in far fewer collisions than
 * both the medium and bad codes.
 * 
 * @authors Subhan Gulistani, Dan Ruley
 * @version March 2019
 */
public class StudentGoodHash {
	private int uid;
	private String firstName;
	private String lastName;

	/**
	 * Creates a new student with the specified uid, firstName, and lastName.
	 * 
	 * @param uid
	 * @param firstName
	 * @param lastName
	 */
	public StudentGoodHash(int uid, String firstName, String lastName) {
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the UID for this student object
	 */
	public long getUid() {
		return this.uid;
	}

	/**
	 * @return the first name for this student object
	 */

	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @return the last name for this student object
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @return true if this student and 'other' have the same UID, first name, and
	 *         last name; false otherwise
	 */
	public boolean equals(Object other) {
		// change to StudentMediumHash and StudentGoodHash for two new classes
		if (!(other instanceof StudentGoodHash))
			return false;

		StudentGoodHash rhs = (StudentGoodHash) other;

		return this.uid == rhs.uid && this.firstName.equals(rhs.firstName) && this.lastName.equals(rhs.lastName);
	}

	/**
	 * @return a textual representation of this student
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("0000000");
		return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
	}

	/**
	 * Provides a hashCode that results in much fewer collisions than the bad and
	 * medium hashcodes.
	 */
	public int hashCode() {

		return firstName.charAt(0) * 128 + lastName.charAt(0) + uid;
	}
}
