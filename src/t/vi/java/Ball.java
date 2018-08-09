package t.vi.java;

/**
 * 
 * @author Oak
 * moving object class in the field
 */

public class Ball {
	private double positionX;
	private double positionY;
	private double mass;
	
	public Ball(double x, double y, double m) {
		this.setPositionX(x);
		this.setPositionY(y);
		this.setMass(m);
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}
	
	

}
