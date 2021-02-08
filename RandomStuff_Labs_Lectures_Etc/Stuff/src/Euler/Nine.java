package Euler;

public class Nine {

	public static void main(String[] args) {
		for (int i = 0; i < 500; i++) {
			int i2 = i * i;
			for (int j = 0; j < 500; j++) {
				int j2 = j * j;
				if (((int)Math.sqrt(i2 + j2) * (int)Math.sqrt(i2 + j2)) == i2 + j2) {
					int a = i, b = j, c = (int) Math.sqrt(i2 + j2);
					if (a + b + c == 1000) {
						System.out.println(a * b * c + " " + a + " " + b + " " + c +" ");
					}
				}
			}
			
		}
	}
}
