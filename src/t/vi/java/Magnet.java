package t.vi.java;

/**
 * 
 * @author Oak
 * Magnet class in the field
 */

public class Magnet {
	private double positionX;
	private double positionY;
	private double km; //strength coefficient for single magnets
	private double kg; //strength coefficient of pull back
	
	public Magnet(double x, double y, double km, double kg) {
		this.setPositionX(x);
		this.setPositionY(y);
		this.setKm(km);
		this.setKg(kg);
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

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public double getKg() {
		return kg;
	}

	public void setKg(double kg) {
		this.kg = kg;
	}
	
}
