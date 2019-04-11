package t.vi.java;

/**
 * 
 * @author Lihua Zhao
 * Pendulum object, called as ball
 */

public class Ball implements Runnable{

	private int positionX = 0;
	private int positionY = 0;
	private double mass = Toolbox.mass_0;
	private boolean stay = true;
	private CalEngine engine = null;
	
	private int[] newposition = null;
	
	public Ball(int x, int y) {		
		this.setPositionX(x);
		this.setPositionY(y);
	}
	
	public void setEngine(CalEngine e) {
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
	
	public void wipeBall() {
		setPositionX(1000);
		setPositionY(1000);
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
			
			newposition = engine.m_Calculate();			
			this.positionX = newposition[0];
			this.positionY = newposition[1];
			if(engine.isBallStay()) {
				setStay(true);
			}			
		}
		System.out.println("ball thread over");						
	}

}
