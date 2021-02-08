package shapelec;

import java.util.Comparator;

/**
 * Comparator for comparing two shapes, using their perimeters.
 * 
 * @author Erin Parker & Derek Castillo
 * @version January 16, 2019
 */
public class OrderByPerimeter implements Comparator<Shape> {

	/**
	 * Compares two shapes using their perimeters.
	 * 
	 * @return negative value if "o1" shape's area is less than "o2" shape's area
	 *         positive value if "o1" shape's area is greater than "o2" shape's area
	 *         0 if "o1" shape's area is the same as "o2" shape's area
	 */
	public int compare(Shape o1, Shape o2) {
		return o1.perimeter() - o2.perimeter();
	}
}