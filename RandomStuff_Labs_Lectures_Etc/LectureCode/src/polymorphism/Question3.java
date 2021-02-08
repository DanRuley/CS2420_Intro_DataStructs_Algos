package polymorphism;

public class Question3 {

	public static void main(String[] args) {
		Lab l = new Lab();
		Restaurant r = new Diner();
		Building b = l;
		Object o = r;
		
		
		
		System.out.println(o.equals(o));
	}
	
}

 class Building {
	 
	
	 
	public void getLocation() {
		System.out.println("Building");
	}
}
 
 class Lab extends Building{
	 public String toString() {
		 System.out.println("Lab");
		 return "Lab";
	 }
	 
 }
 
 class Restaurant extends Building{
	 public void getSeatCount() {
		 System.out.println("Rest");
	 }
 }
 
 class Diner extends Restaurant {
	 public boolean equals(Object other) {
		 System.out.println("Diner");
		 return true;
	 }
 }