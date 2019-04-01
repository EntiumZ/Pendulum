package t.vi.java;

import java.util.Vector;

public class ComputeEngine {

	public ComputeEngine() {
		
	}
	
	private Vector<Magnet> magList = new Vector<Magnet>();
	
	public Vector<Magnet> getMagList() {
		return magList;
	}

	public void setMagList(Vector<Magnet> magList) {
		this.magList = magList;
	}	
	
	private double km = Toolbox.km_0;
	private double kg = Toolbox.kg_0;
	private double kf = Toolbox.kf_0;
	
	private double nexVx = 0.0, nexVy = 0.0;
	private double curVx = 0.0, curVy = 0.0;		 
	private double preAx = 0.0, preAy = 0.0;
	private double curAx = 0.0, curAy = 0.0;
	private double nexAx = 0.0, nexAy = 0.0;
	
	private int[] nexP = {0, 0};
		
	public void refresh() {		
		nexVx = 0.0; nexVy = 0.0;
		curVx = 0.0; curVy = 0.0;		
		preAx = 0.0; preAy = 0.0;
		curAx = 0.0; curAy = 0.0;
		nexAx = 0.0; nexAy = 0.0;
	}
		
	public void Calculate(int x, int y) {
		double[] magforce = new double[2];
		double[] gforce = new double[2];
		double[] friction = new double[2];

 		this.getMagforce(magforce, x, y);
		this.getGforce(gforce, x, y);
		this.getfriction(friction);

 		curAx = km * magforce[0] + kg * gforce[0] + kf * friction[0];
		curAy = km * magforce[1] + kg * gforce[1] + kf * friction[1];

 		nexP[0] = x + (int)(curVx + (4 * curAx - preAx) / 6);
		nexP[1] = y + (int)(curVy + (4 * curAy - preAy) / 6);

 		this.getMagforce(magforce, nexP[0], nexP[1]);
		this.getGforce(gforce, nexP[0], nexP[1]);

 		nexAx = km * magforce[0] + kg * gforce[0] + kf * friction[0];
		nexAy = km * magforce[1] + kg * gforce[1] + kf * friction[1];

 		nexVx = (curVx + (2 * nexAx + 5 * curAx - preAx) / 6);
		nexVy = (curVy + (2 * nexAy + 5 * curAy - preAy) / 6);

 		curVx = nexVx;
		curVy = nexVy;

 		preAx = curAx;
		preAy = curAy;
								
	}
	
	public int calX() {
		return nexP[0];
	}

	public int calY() {		
		return nexP[1];
	}		

	private void getMagforce(double[] result, int px, int py) {		
		if(result == null || result.length != 2) {
			result = new double[2];
		}		
		for(int i = 0; i < magList.size(); i++) {
			Vect v = new Vect(px, py, magList.get(i).getPositionX(), magList.get(i).getPositionY());
			double norm = v.getNorm();
			double temp = Math.sqrt(norm * norm + 20 * 20);
			double x = (double)v.getpX();
			double y = (double)v.getpY();			
			result[0] += 10000.0 * x / (temp * temp * temp);
			result[1] += 10000.0 * y / (temp * temp * temp);			
		}
	}
	
	private void getGforce(double[] result, int px, int py) {
		if(result == null || result.length != 2) {
			result = new double[2];
		}				
		Vect v = new Vect(px, py, Toolbox.mainpanelWidth / 2, Toolbox.mainpanelHeight / 2); 
		double x = (double)v.getpX();
		double y = (double)v.getpY();				
		result[0] = 0.01 * x;
		result[1] = 0.01 * y;
		 
	}
	
	private void getfriction(double[] result) {
		if(result == null || result.length != 2) {
			result = new double[2];
		}		
		result[0] = -1 * curVx;
		result[1] = -1 * curVy;
	}
	
	private boolean isClosed;
	public boolean isBallClosed() {
		
		isClosed = false;
		
//		for(int i = 0; i < mag_quat; i++) {
//			double dist = new Vect(ball.getPositionX(), ball.getPositionY(), magList.get(i).getPositionX(), magList.get(i).getPositionY()).getNorm();
//			//System.out.println("x0: " + ball.getPositionX() + " y0: " + ball.getPositionY() + " x1: " + magList.get(i).getPositionX() + " y1: " + magList.get(i).getPositionY());
//			isClosed = isClosed || (dist < Toolbox.dist_allow);
//			//System.out.println(isClosed? "true": "false");
//		}
		
		//double dist = new Vect(ball.getPositionX(), ball.getPositionY(), Toolbox.mainpanelWidth / 2, Toolbox.mainpanelHeight / 2).getNorm();
				
		//isClosed =  (isClosed || (dist < Toolbox.dist_allow)) && (curVx * curVx + curVy * curVy < 5);
				
		return isClosed;
	}

}
