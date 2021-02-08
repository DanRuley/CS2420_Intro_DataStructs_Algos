package lab02;

/**
 * This program loops 100 times looking for changes in the current time.  If
 * time advances in one-nanosecond increments, then this code should complete in
 * 100 nanoseconds.
 * 
 * @author Erin Parker
 * @version January 18, 2019
 */
public class TimingExperiment04 {

  public static void main(String[] args) {
    long lastTime = System.nanoTime();
    int advanceCount = 0;
    long[] advanceAmounts = new long[100];
    while (advanceCount < 100) {
      long currentTime = System.nanoTime();
      if (currentTime == lastTime)   //function can run multiple times in a nanosecond, this ensures same times aren't being recorded
        continue;
      advanceAmounts[advanceCount++] = currentTime - lastTime;
      lastTime = currentTime;
    }
    for (int i = 0; i < 100; i++)
      System.out.println("Time advanced " + advanceAmounts[i] + " nanoseconds.");
  }
}