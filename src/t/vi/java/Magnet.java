package t.vi.java;

/**
 * 
 * @author EntiumZ
 * Magnet class in the system
 * 
 */

public class Magnet {
	private int positionX;
	private int positionY;
	private double km = Toolbox.km_0; //strength coefficient for single magnets
	
	public Magnet(int x, int y) {
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

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}
	
}
