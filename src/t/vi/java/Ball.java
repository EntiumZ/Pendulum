package t.vi.java;

/**
 * 
 * @author Oak
 * moving object class in the field
 */

public class Ball implements Runnable{

	private int positionX = 0;
	private int positionY = 0;
	private int mass = Toolbox.ballMass;
	private boolean stay = true;
	
	private static Ball ball = new Ball(0, 0, Toolbox.ballMass);
	
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
		ball.setStay(false);
		while(true) {
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.positionX += 2;
			this.positionY += 3;
			
			if(this.positionX > 400 && this.positionY >400) {
				ball.setStay(true);
				System.out.println(this.positionX + "+" +this.positionY);
				break;
			}
		}
						
	}

	public boolean isStay() {
		return stay;
	}

	public void setStay(boolean b) {
		this.stay = b;
	}



}
