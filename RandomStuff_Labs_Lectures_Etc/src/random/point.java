package random;



public class point {
	public int x;
	public int y;
	
	public static void main(String[] args) {
		Employee e = new Employee();
		Person p = e;
		System.out.println(e.equals(e));
		Student s = new GradStudent();
		
		Object o = s;
		
		System.out.println(s.toString());
	}
	
}
