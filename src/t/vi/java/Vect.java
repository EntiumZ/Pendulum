package t.vi.java;

/**
 * 
 * @author EntiumZ
 * Utility tool: vector in Mathematics 
 *
 */

public class Vect {
	
	private int pX, pY;

	public Vect(int x0, int y0, int x1, int y1) {
		this.setpX(x1 - x0);
		this.setpY(y1 - y0);
	}
	
	public Vect(Vect v1, char c, Vect v2) {
		if(c == '+') {
			this.setpX(v1.getpX() + v2.getpX());
			this.setpY(v1.getpY() + v2.getpY());
		}else if(c == '-') {
			this.setpX(v1.getpX() - v2.getpX());
			this.setpY(v1.getpY() - v2.getpY());
		}else {
			this.setpX(0);
			this.setpY(0);
		}		
	}
	
	public Vect(int dx, int dy) {
		this.setpX(dx);
		this.setpY(dy);
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
	
	public double getNorm() {
		return Math.sqrt((double)(pX * pX + pY * pY));
	}
	
	public void addVect(Vect vec) {
		this.setpX(this.pX + vec.getpX());
		this.setpY(this.pY + vec.getpY());
	}

	public void subVect(Vect vec) {
		this.setpX(this.pX - vec.getpX());
		this.setpY(this.pY - vec.getpY());
	}
	
	public void rescaleVect(double d) {
		this.setpX((int)((double)this.pX * d));
		this.setpY((int)((double)this.pY * d));		
	}
	
	
}
