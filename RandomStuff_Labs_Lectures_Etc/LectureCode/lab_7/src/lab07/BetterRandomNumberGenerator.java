package lab07;

public class BetterRandomNumberGenerator implements RandomNumberGenerator{

	private long seed;
	private long result;
	private int MODULUS = 20;
	private final static int INCREMENT = 1;
	private final static int MULTIPLIER = 3;
	
	public static void main(String[] args) {
		BetterRandomNumberGenerator r = new BetterRandomNumberGenerator();
		r.nextInt(5);
		for (int i = 0; i < 100; i++)
			System.out.println(r.nextInt(10000));
	}
	
	public BetterRandomNumberGenerator() {
		setSeed(0);
		computeFirst();	
	}
	
	@Override
	public int nextInt(int max) {
		
		result = (2521490317L * result + 11) % (long) Math.pow(2, 48);
		return (int) (Math.abs(result % max));
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;
		
	}

	private void computeFirst() {
		result = ((6293 * 0 + 997) % 163848);
	}
	
	@Override
	public void setConstants(long const1, long const2) {
		// TODO Auto-generated method stub
		
	}

}
