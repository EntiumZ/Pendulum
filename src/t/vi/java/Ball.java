package t.vi.java;

/**
 * 
 * @author Oak
 * moving object class in the field
 */

public class Ball implements Runnable{

	private int positionX = 0;
	private int positionY = 0;
	private double mass = Toolbox.coeff[0];
	private boolean stay = true;
	private Engine engine = null;
	
	public void setEngine(Engine e) {
		this.engine = e;
	}
	
	public Ball(int x, int y) {		
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

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
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
			
			if(engine.isBallClosed()) {
				this.setStay(true);
				break;
			}
			
//			this.positionX += 2;
//			this.positionY += 2;
			engine.Testtt();
			this.positionX = engine.calX();
			this.positionY = engine.calY();
			//System.out.println(this.positionX + ", " + this.positionY);
			//System.out.println(engine.calX() + ", " + engine.calY());
			
			if(engine.isBallClosed()) {
				this.setStay(true);
				//System.out.println("final:"+this.positionX + "+" +this.positionY);
				//System.out.println("ball mass:" + this.getMass());
				break;
			}
		}
		System.out.println("ball thread over");
						
	}

	public boolean isStay() {
		return stay;
	}

	public void setStay(boolean b) {
		this.stay = b;
	}



}
