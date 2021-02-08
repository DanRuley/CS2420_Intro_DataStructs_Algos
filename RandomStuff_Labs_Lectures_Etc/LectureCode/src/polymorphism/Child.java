package polymorphism;

// This is a dummy class created to demonstrate polymorphism.
public class Child extends Parent {

	public int bar() {
		System.out.println("Hello from the bar method in Child.");
		return 0;
	}
	
	public String toString() {
		System.out.println("Hello from the toString method in Child.");
		return "I am a child.";
	}
}