package t.vi.java;

/**
 * @author Lihua Zhao
 * Calculation engine for the whole program
 */

import java.util.Vector;

public class Engine {

	private Ball ball = null;
	private Vector<Magnet> magList = new Vector<Magnet>();
	private MagnetsCollection magnets =  new MagnetsCollection();
	
	//default parameters
	private double kg = Toolbox.kg_0;
	private double kf = Toolbox.kf_0;
	
	//variables for computation
	private double curVx = 0.0, curVy = 0.0;		 
	private double preAx = 0.0, preAy = 0.0;
	private double curAx = 0.0, curAy = 0.0;
	private double nexAx = 0.0, nexAy = 0.0;
	
	private int[] nextP = {0, 0};
	
	public Engine() {
		ball = new Ball(0, 0);
		ball.setEngine(this);
		magList = magnets.getMagList();				
	}
	
	public Vector<Magnet> getmagList() {
		return this.magList;
	}
		
	public Ball getBall() {
		return this.ball;		
	}
			
	public void refresh() {				
		preAx = 0.0; preAy = 0.0;
		curAx = 0.0; curAy = 0.0;
		nexAx = 0.0; nexAy = 0.0;
	}
		
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
	}
	
	public int calX() {
		return nextP[0];
	}

	public int calY() {		
		return nextP[1];
	}
	
	public boolean isBallStay() {
		double v = Math.sqrt(curVx * curVx + curVy *curVy);		
		return ((int)v == 0 || (int)v == 1)&&(getMinDistance() < 10.0)? true : false;		
	}

	private void getMagforce(double[] result, int px, int py) {		
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
	
	private void getGforce(double[] result, int px, int py) {
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
	
	private double getMinDistance() {
		
		double minNorm = 1131.2;
		
		int px = ball.getPositionX();
		int py = ball.getPositionY();
		
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
