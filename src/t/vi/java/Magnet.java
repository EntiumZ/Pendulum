package t.vi.java;

/**
 * 
 * @author Oak
 * Magnet class in the field
 */

public class Magnet {
	private int positionX;
	private int positionY;
	private int km; //strength coefficient for single magnets
	private int kg; //strength coefficient of pull back
	
	public Magnet(int x, int y, int km, int kg) {
		this.setPositionX(x);
		this.setPositionY(y);
		this.setKm(km);
		this.setKg(kg);
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

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public int getKg() {
		return kg;
	}

	public void setKg(int kg) {
		this.kg = kg;
	}
	
}
