package polymorphism;
// This is a dummy class created to demonstrate polymorphism.
public class Grandchild extends Child {

	// This is a dummy equals method.
	public boolean equals(Object other) {
		System.out.println("Hello from the equals method in Grandchild.");
		return true;
	}
}