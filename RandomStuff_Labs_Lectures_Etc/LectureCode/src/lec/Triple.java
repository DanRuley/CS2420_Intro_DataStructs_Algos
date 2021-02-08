package lec;

import shapelec.*;


/**
 * This class represents a collection of three, same-type items, 
 * i.e., a triple.
 * 
 * Method comments are left for students to provide.
 * 
 * @author Erin Parker
 * @version January 16, 2019
 */
public class Triple<Type> {

	private Type item1;
	private Type item2;
	private Type item3;
	
	public Triple(Type item1, Type item2, Type item3) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}
	
	public Type getFirstItem() {
		return item1;
	}
	
	public Type getSecondItem() {
		return item2;
	}
	
	public Type getThirdItem() {
		return item3;
	}
	
	public String toString() {
		return item1 + " " + item2 + " " + item3;
 	}
	
	public static void main(String[] args) {
		// Use our generic class for specific type String.
		Triple<String> greetings = new Triple<String>("Hello", "Hi", "Howdy");
		System.out.println(greetings);
		System.out.println(greetings.toString());
		
		// Use our generic class for specific type Integer.
		Triple<Integer> numbers = new Triple<Integer>(1, 2, 3);
		System.out.println(numbers);
		
		// Use our generic class for specific type Shape.
		Triple<Shape> shapes = new Triple<Shape>(new Circle(10), 
					new Rectangle(9, 14), new Square(6));
		System.out.println(shapes);
	}
}