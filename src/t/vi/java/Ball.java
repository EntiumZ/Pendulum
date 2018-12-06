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
	
	private static Ball ball = new Ball(0, 0, 0);
	
	private Ball(int m, int x, int y) {
		this.setMass(m);
		this.setPositionX(x);
		this.setPositionY(y);
	}

	public static Ball getBall() {
		return ball;
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

	@Override
	public void run() {
		while(true) {
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.positionX += 2;
			this.positionY += 3;
			
			if(this.positionX > 400 && this.positionY >400) {
				System.out.println(this.positionX + "+" +this.positionY);
				break;
			}
		}
						
	}



}
