package polymorphism;

/**
 * Program to demonstrate polymorhphism through the class hierarchy of the Parent, 
 * Child, and Grandchild classes.
 * 
 * Answer the questions below about each case. Uncomment and run to verify your answer.
 * 
 * @author Erin Parker
 * @version January 14, 2019
 */
public class PolymorphismExercises {
	
	public static void main(String[] args) {
		
		Parent p = new Child();
		
		
		/* Parent class defines one method: equals
		   Child class defines two methods: bar, toString
		   Grandchild defines one method: equals 

		//Grandchild g = new Child();  // What happens here?
		// Possible answers: A - compiler error, B - exception at run time, C - no error
		
		Object o = new Parent();  // What happens here?
		// Possible answers: A - compiler error, B - exception at run time, C - no error
		//o.equals(o);  // Which version of the equals method is called?
		// Possible answers: A - Object, B - Parent, C - Grandchild, D - None, it is a compiler error.

		//System.out.println(o.toString());  // Which version of the toString method is called?
		// Possible answers: A - Object, B - Child, C - None, it is a compiler error.
		
		Object o2 = new Child();
		//o2.equals(o);  // Which version of the equals method is called?
		// Possible answers: A - Object, B - Parent, C - Grandchild, D - None, it is a compiler error.

		Parent p = new Child();
		//o.equals(p);
		//p.bar();    // Which version of the bar method is called?
		// Possible answers: A - Child, B - None, it is a compiler error.
		
		Object c = new Grandchild();
		c.equals(c);  // Which version of the equals method is called?
		System.out.println(c instanceof String);
		
		
		*/
		// Possible answers: A - Object, B - Parent, C - Grandchild, D - None, it is a compiler error.
	}
}