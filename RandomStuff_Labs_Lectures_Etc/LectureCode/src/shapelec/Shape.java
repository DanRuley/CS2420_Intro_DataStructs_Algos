package shapelec;

/**
 * Class representing a generic shape.
 * 
 * @author Erin Parker & Derek Castillo
 * @version January 16, 2019
 */
public abstract class Shape implements Comparable<Shape> {
	
	private String name; // String representation, such as "Circle"

	public Shape(String name) {
		this.name = name;
	}

	/**
	 * @return the area of a shape
	 */
	public abstract int area();

	/**
	 * @return the perimeter of a shape
	 */
	public abstract int perimeter();

	/**
	 * @return the String that describes a shape
	 */
	public final String toString() {
		return name + " with area " + area() + " and perimeter " + perimeter() + ".";
	}
	
	/**
	 * Compares two shapes using their areas.
	 * 
	 * @return negative value if "this" shape's area is less than "other" shape's area
	 *         positive value if "this" shape's area is greater than "other" shape's area
	 *         0 if "this" shape's area is the same as "other" shape's area
	 */
	public int compareTo(Shape other) {
		if (this.area() < other.area()) {
			return -1;
		}
		if (this.area() > other.area()) {
			return 1;
		}
		
		return 0;
		
		/* Below is an alternate way to implement this method.
		 * Since area() returns an int, this is method of comparison is safe.
		 * It would not be a good idea to compare double values this way.
		 */
//		return this.area() - other.area();
	}
}