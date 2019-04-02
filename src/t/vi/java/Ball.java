package t.vi.java;

/**
 * 
 * @author Lihua Zhao
 * Pendulum object, called as ball
 */

public class Ball implements Runnable{

	private int positionX = 0;
	private int positionY = 0;
	private double mass = Toolbox.coeff[0];
	private boolean stay = true;
	private Engine engine = null;
	
	public Ball(int x, int y) {		
		this.setPositionX(x);
		this.setPositionY(y);
	}
	
	public void setEngine(Engine e) {
		this.engine = e;
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

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public boolean isStay() {
		return stay;
	}

	public void setStay(boolean b) {
		this.stay = b;
	}
	
	@Override
	public void run() {		
		this.setStay(false);		
		while(!this.isStay()) {			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			engine.m_Calculate();			
			this.positionX = engine.calX();
			this.positionY = engine.calY();
			if(engine.isBallStay()) {
				setStay(true);
			}			
		}
		System.out.println("ball thread over");						
	}

}
