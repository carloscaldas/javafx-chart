package leituradados;

public class Point {
	private long x;
	private long y;
	public long getX() {
		return x;
	}
	public void setX(long x) {
		this.x = x;
	}
	public long getY() {
		return y;
	}
	public void setY(long y) {
		this.y = y;
	}
	
	public Point(Long x, Long y) {
		this.x = x;
		this.y = y;
	}
}
