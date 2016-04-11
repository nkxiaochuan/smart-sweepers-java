package smart.sweepers.entity;

public class Point2D {

	private double x;
	private double y;
	
	public Point2D(){
		this.x = 0;
		this.y = 0;
	}
	public Point2D(double a, double b){
		this.x = a;
		this.y = b;
	}
	
	public double getPoint2DLength(){
		return Math.sqrt(x * x + y * y);
	}
	
	public void point2DNormalize(){
		double length = getPoint2DLength();
		this.x = x / length;
		this.y = y / length;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
