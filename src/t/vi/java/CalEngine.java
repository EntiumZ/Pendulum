package t.vi.java;

/**
 * @author Lihua Zhao
 * Calculation engine for single pendulum trace
 */

import java.util.Vector;

public class CalEngine {
	
	private Ball ball = null;	
	private Vector<Magnet> magList = new Vector<Magnet>();
	

	private MagnetsCollection magnets =  new MagnetsCollection();
	
	//default parameters
	private double kg = Toolbox.kg_0;
	private double kf = Toolbox.kf_0;
	
	//variables for computation
	private double curVx = 0.0, curVy = 0.0;
	
	public void setCurVx(double curVx) {
		this.curVx = curVx;
	}

	public void setCurVy(double curVy) {
		this.curVy = curVy;
	}

	private double preAx = 0.0, preAy = 0.0;
	private double curAx = 0.0, curAy = 0.0;
	private double nexAx = 0.0, nexAy = 0.0;
	
	private int[] nextP = {0, 0};
	
	public CalEngine() {
		ball = new Ball(0, 0);
		ball.setEngine(this);
		magList = magnets.getMagList();				
	}
	
	public Vector<Magnet> getmagList() {
		return this.magList;
	}	
		
	public void setMagList(Vector<Magnet> magList) {
		this.magList = magList;
	}
	
	public Ball getBall() {
		return this.ball;		
	}
	
	public double getKg() {
		return kg;
	}

	public void setKg(double kg) {
		this.kg = kg;
	}

	public double getKf() {
		return kf;
	}

	public void setKf(double kf) {
		this.kf = kf;
	}
			
	public void refresh() {				
		preAx = 0.0; preAy = 0.0;
		curAx = 0.0; curAy = 0.0;
		nexAx = 0.0; nexAy = 0.0;
		counter = 0;
	}
		
	private int counter = 0;
	public void m_Calculate() {
		double[] magforce = new double[2];
		double[] gforce = new double[2];
		double[] friction = new double[2];

 		this.getMagforce(magforce, ball.getPositionX(), ball.getPositionY());
		this.getGforce(gforce, ball.getPositionX(), ball.getPositionY());
		this.getfriction(friction);		

 		curAx = magforce[0] + gforce[0] + friction[0];
		curAy = magforce[1] + gforce[1] + friction[1];		

 		nextP[0] = ball.getPositionX() + (int)(curVx + (4 * curAx - preAx) / 6);
		nextP[1] = ball.getPositionY() + (int)(curVy + (4 * curAy - preAy) / 6);

 		getMagforce(magforce, nextP[0], nextP[1]);
		getGforce(gforce, nextP[0], nextP[1]);

 		nexAx = magforce[0] + gforce[0] + friction[0];
		nexAy = magforce[1] + gforce[1] + friction[1];

		curVx = curVx + (2 * nexAx + 5 * curAx - preAx) / 6;
		curVy = curVy + (2 * nexAy + 5 * curAy - preAy) / 6;
		
 		preAx = curAx;
		preAy = curAy;
		
		if((int)Math.sqrt(curVx * curVx + curVy *curVy) == 0) {
			counter++;
		}
				
	}
	
	public int calX() {
		return nextP[0];
	}

	public int calY() {		
		return nextP[1];
	}
	
	public boolean isBallStay() {
		double v = Math.sqrt(curVx * curVx + curVy *curVy);
		double minDistance = getMinDistance(ball.getPositionX(), ball.getPositionY());
		if(((int)v == 0 || (int)v == 1) && minDistance < Toolbox.dis_allow){
			return true;
		}
		
		Vect vect = new Vect(ball.getPositionX(), ball.getPositionY(), Toolbox.mainpanelWidth / 2, Toolbox.mainpanelHeight / 2);
		double markDistance = vect.getNorm();
		
		if(counter > 10) {
			if((int)v == 0 && minDistance == markDistance) {
				if(minDistance < 100.0) {
					return true;
				}
			}			
		}		
		
		return false;
	}

	protected void getMagforce(double[] result, int px, int py) {		
		if(result == null || result.length != 2) {
			result = new double[2];
		}
		
		for(int i = 0; i < magList.size(); i++) {
			Vect vec = new Vect(px, py, magList.get(i).getPositionX(), magList.get(i).getPositionY());
			double norm = vec.getNorm();
			double temp = Math.sqrt(norm * norm + 40 * 40);
			double x = (double)vec.getpX();
			double y = (double)vec.getpY();			
			result[0] += magList.get(i).getKm() * 10000.0 * x / (temp * temp * temp);
			result[1] += magList.get(i).getKm() * 10000.0 * y / (temp * temp * temp);			
		}
	}
	
	protected void getGforce(double[] result, int px, int py) {
		if(result == null || result.length != 2) {
			result = new double[2];
		}
				
		Vect vec = new Vect(px, py, Toolbox.mainpanelWidth / 2, Toolbox.mainpanelHeight / 2); 
		double x = (double)vec.getpX();
		double y = (double)vec.getpY();		
		result[0] = kg * 0.01 * x;
		result[1] = kg * 0.01 * y;
		 
	}
	
	private void getfriction(double[] result) {
		if(result == null || result.length != 2) {
			result = new double[2];
		}		
		result[0] = -1 * kf * curVx;
		result[1] = -1 * kf * curVy;
	}		
	
	private double getMinDistance(int px, int py) {
		
		double minNorm = 1131.2;
		
		for(int i = 0; i < magList.size(); i++) {
			Vect vec = new Vect(px, py, magList.get(i).getPositionX(), magList.get(i).getPositionY());
			double norm = vec.getNorm();
			minNorm = Math.min(minNorm, norm);
		}
		
		Vect vec = new Vect(px, py, Toolbox.mainpanelWidth / 2, Toolbox.mainpanelHeight / 2); 
		minNorm = Math.min(minNorm, vec.getNorm());
		
		return minNorm;		
	}

	

}
