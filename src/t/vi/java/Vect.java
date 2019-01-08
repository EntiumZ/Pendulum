package t.vi.java;

public class Vect {
	
	private int pX, pY;

	public Vect(int x0, int y0, int x1, int y1) {
		this.setpX(x1 - x0);
		this.setpY(y1 - y0);
	}

	public int getpX() {
		return pX;
	}

	public void setpX(int x) {
		this.pX = x;
	}

	public int getpY() {
		return pY;
	}

	public void setpY(int y) {
		this.pY = y;
	}
	
	public int getTheta() {
		return pX/pY;
	}
	
	public void combineVect(Vect vec) {
		this.setpX(this.pX + vec.getpX());
		this.setpY(this.pY + vec.getpY());
	}

}
