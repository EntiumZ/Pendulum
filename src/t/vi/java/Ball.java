package t.vi.java;

import java.awt.Image;

/**
 * 
 * @author Oak
 * moving object class in the field
 */

public class Ball{
	private int positionX = 0;
	private int positionY = 0;
	private int mass = Toolbox.ballMass;
	
	
	public Ball(int m, int x, int y) {
		this.setMass(m);
		this.setPositionX(x);
		this.setPositionY(y);
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}



}
