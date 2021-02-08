package assign01;

/**
 * This class represents a simple row or column vector of numbers. In a row
 * vector, the numbers are written horizontally (i.e., along the columns). In a
 * column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author Erin Parker & Dan Ruley
 * @version January 9, 2019
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;

	/**
	 * Creates a new row or column vector. For a row vector, the input array is
	 * expected to have 1 row and a positive number of columns, and this number of
	 * columns represents the vector's length. For a column vector, the input array
	 * is expected to have 1 column and a positive number of rows, and this number
	 * of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the
	 *                                  input 2D array is not compatible with a row
	 *                                  or column vector
	 */
	public MathVector(double[][] data) {
		if (data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if (data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");

		if (data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		} else if (data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		} else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");

		// Create the array and copy data over.
		if (this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Determines whether this vector is "equal to" another vector, where equality
	 * is defined as both vectors being row (or both being column), having the same
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {

		if (!(other instanceof MathVector))
			return false;

		MathVector otherVec = (MathVector) other;

		// For any case where two vectors are equal, their string representations should
		// be identical. Therefore the string results of both vectors are compared for
		// equality, and this value is returned.
		return this.toString().equals(otherVec.toString());
	}

	/**
	 * Generates a returns a new vector that is the transposed version of this
	 * vector.
	 */
	public MathVector transpose() {

		// for a row vector, create an array in column vector format from this' data.
		// Then return the new column vector containing the same elements.
		if (this.isRowVector) {
			double[][] columnData = new double[this.vectorSize][1];
			for (int i = 0; i < this.vectorSize; i++) {
				columnData[i][0] = this.data[0][i];
			}
			return new MathVector(columnData);
		}

		//same process but for a column -> row transposition
		else {
			double[][] rowData = new double[1][this.vectorSize];
			for (int i = 0; i < this.vectorSize; i++) {
				rowData[0][i] = this.data[i][0];
			}
			return new MathVector(rowData);
		}
	}

	/**
	 * Generates and returns a new vector representing the sum of this vector and
	 * another vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 */
	public MathVector add(MathVector other) {

		// throw IAE if vectors are not both row or column
		if (this.isRowVector && !other.isRowVector || !this.isRowVector && other.isRowVector) {
			throw new IllegalArgumentException("Error: vectors are not the same type.");
		}

		// throw IAE if vectors are not of equal size
		if (this.vectorSize != other.vectorSize) {
			throw new IllegalArgumentException("Error: vectors are not equal in size.");
		}

		// iterate through the array and add the elements of this and the other vector.
		// Return a new MathVector created with this data.
		if (this.isRowVector) {
			double[][] summedData = new double[1][vectorSize];
			for (int i = 0; i < this.vectorSize; i++) {
				summedData[0][i] = this.data[0][i] + other.data[0][i];
			}
			return new MathVector(summedData);
		}

		// same process, but for a column vector
		else {
			double[][] summedData = new double[this.vectorSize][1];
			for (int i = 0; i < this.vectorSize; i++) {
				summedData[i][0] = this.data[i][0] + other.data[i][0];
			}
			return new MathVector(summedData);
		}
	}

	/**
	 * Computes and returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the
	 *              dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 */
	public double dotProduct(MathVector other) {
		// throw IAE if vectors are not both row or column
		if (this.isRowVector && !other.isRowVector || !this.isRowVector && other.isRowVector) {
			throw new IllegalArgumentException("Error: vectors are not the same type.");
		}

		// throw IAE if vectors are not of equal size
		if (this.vectorSize != other.vectorSize) {
			throw new IllegalArgumentException("Error: vectors are not equal in size.");
		}

		double summedProducts = 0;

		// iterate through the array and sum the products of each element
		if (this.isRowVector) {
			for (int i = 0; i < this.vectorSize; i++) {
				summedProducts = summedProducts + (this.data[0][i] * other.data[0][i]);
			}
			return summedProducts;
		}

		// same process, but for column vector
		else {
			for (int i = 0; i < this.vectorSize; i++) {
				summedProducts = summedProducts + (this.data[i][0] * other.data[i][0]);
			}
			return summedProducts;
		}
	}

	/**
	 * Computes and returns this vector's magnitude (also known as a vector's
	 * length) .
	 */
	public double magnitude() {
		double sumSquares = 0;
		if (this.isRowVector) {
			for (int i = 0; i < this.vectorSize; i++) {
				sumSquares = sumSquares + (this.data[0][i] * this.data[0][i]); // add the sum of the squares of all
																				// elements
			}
			return Math.sqrt(sumSquares); // take the square root to get magnitude
		}

		// if it's not a row vector, do the same process, but for a column vector
		else {
			for (int i = 0; i < this.vectorSize; i++) {
				sumSquares = sumSquares + (this.data[i][0] * this.data[i][0]);
			}
			return Math.sqrt(sumSquares);
		}
	}

	/**
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {

		// iterate through row vector elements and divide by the magnitude, and store in
		// a new array. Then return a new Math Vector containing the normalized
		// elements.
		if (this.isRowVector) {
			double[][] normalizedData = new double[1][vectorSize];
			for (int i = 0; i < this.vectorSize; i++) {
				normalizedData[0][i] = (this.data[0][i] / this.magnitude());
			}
			return new MathVector(normalizedData);
		}

		// same process, but for a column vector
		else {
			double[][] normalizedData = new double[vectorSize][1];
			for (int i = 0; i < this.vectorSize; i++) {
				normalizedData[i][0] = (this.data[i][0] / this.magnitude());
			}
			return new MathVector(normalizedData);
		}
	}

	/**
	 * Generates and returns a textual representation of this vector. For example,
	 * "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and "1.0 2.0 3.0
	 * 4.0 5.0" for a sample column vector of length 5. In both cases, notice the
	 * lack of a newline or space after the last number.
	 */
	public String toString() {
		String result = "";

		// iterate through elements and concat. them onto result string, return string
		// when finished
		if (this.isRowVector) {
			// stop the loop at the second to last element so the final element does not
			// have whitespace after it
			for (int i = 0; i < this.vectorSize - 1; ++i) {
				result = result + this.data[0][i] + " ";
			}
			result = result + this.data[0][vectorSize - 1];
			return result;
		}

		// same process, but for column vector
		else {
			for (int i = 0; i < this.vectorSize - 1; ++i) {
				result = result + this.data[i][0] + "\n";
			}
			result = result + this.data[vectorSize - 1][0];
			return result;
		}
	}

	/**
	 * Getter method for isRowVector, strictly used for testing purposes.
	 * 
	 * @return isRowVector
	 */
	public boolean getIsRowVector() {
		return this.isRowVector;
	}

}