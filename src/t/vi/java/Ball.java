package t.vi.java;

import java.awt.Image;

/**
 * 
 * @author Oak
 * moving object class in the field
 */

public class Ball implements Runnable{
	private int positionX = 0;
	private int positionY = 0;
	private int mass = Toolbox.ballMass;
	public static Image img = Toolbox.getImage("image/ball.png");
	private MainPanel panel;
	
	public Ball(int x, int y, int m) {
		this.setPositionX(x);
		this.setPositionY(y);
		this.setMass(m);
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
	
	public void setPanel(MainPanel p) {
		this.panel = p;
	}

	@Override
	public void run() {
		while(true) {
			positionX += 3;
			positionY += 3;
			//this.panel.
			
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}

}
