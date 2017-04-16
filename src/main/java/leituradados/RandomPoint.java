package leituradados;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class RandomPoint {
	private static AtomicLong time = new AtomicLong();
	
	public static Point nextPoint() {
		Random r = new Random();
		return new Point(time.getAndIncrement(), new Long(r.nextInt(50)));
	}
}
