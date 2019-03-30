package t.vi.java;

import java.util.Vector;

public class Engine {

	private Vector<Magnet> magList = new Vector<Magnet>();
	
	public Vector<Magnet> getmagList() {
		return this.magList;
	}
	
	private int mag_quat = 3;
	private double km = Toolbox.km_0;
	private double kg = Toolbox.kg_0;
	private double kf = Toolbox.kf_0;
	
	private int[][] mag_position = {{400, 200},{227, 500},{573, 500}}; //temporary solution
	
	private Ball ball = null;
	
	public Ball getBall() {
		return this.ball;		
	}

	public Engine() {
		ball = new Ball(0, 0);
		ball.setEngine(this);
		
		for(int i = 0; i < mag_quat; i++) {
			//int[] temp_pos = new int[2];
			//this.setMagPosition(temp_pos, mag_quat, i, distance);
			magList.add(new Magnet(mag_position[i][0], mag_position[i][1]));
			//System.out.println(magList.get(i).getPositionX() + ", " + magList.get(i).getPositionY());
		}				
	}
	
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
		
	public void m_Calculate() {
		double[] magforce = new double[2];
		double[] gforce = new double[2];
		double[] friction = new double[2];

 		this.getMagforce(magforce,ball.getPositionX(), ball.getPositionY());
		this.getGforce(gforce,ball.getPositionX(), ball.getPositionY());
		this.getfriction(friction);

 		curAx = km * magforce[0] + kg * gforce[0] + kf * friction[0];
		curAy = km * magforce[1] + kg * gforce[1] + kf * friction[1];

 		nexP[0] = ball.getPositionX() + (int)(curVx + (4 * curAx - preAx) / 6);
		nexP[1] = ball.getPositionY() + (int)(curVy + (4 * curAy - preAy) / 6);

 		this.getMagforce(magforce, nexP[0], nexP[1]);
		this.getGforce(gforce,nexP[0], nexP[1]);

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
	
//	private void setMagPosition(int[] position, int num, int p, int length) {
//		
//		if(position.length != 2 || num < 3 || num > 6 || p < 0 || p >= num) {
//			System.exit(0);
//		}
//		
//		double temp_num = (double)num;
//		double temp_p = (double)p;
//		double temp_length = (double)length;
//		
//		position[0] = Toolbox.mainpanelWidth / 2+ (int)(Math.pow(-1.0, temp_p) * Math.sin(temp_p * 180.0 / temp_num) * temp_length);
//		position[1] = Toolbox.mainpanelHeight / 2 + (int)(Math.pow(-1.0, temp_p) * Math.cos(temp_p * 180.0 / temp_num) * temp_length);
//		
//		return;
//	}
		

	private void getMagforce(double[] result, int px, int py) {		
		if(result == null || result.length != 2) {
			result = new double[2];
		}
		
		for(int i = 0; i < magList.size(); i++) {
			Vect v = new Vect(px, py, magList.get(i).getPositionX(),magList.get(i).getPositionY());
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
				
		Vect v = new Vect(px, py, Toolbox.mainpanelWidth /2, Toolbox.mainpanelHeight / 2); 
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
		
		double dist = new Vect(ball.getPositionX(), ball.getPositionY(), Toolbox.mainpanelWidth / 2, Toolbox.mainpanelHeight / 2).getNorm();
				
		isClosed =  (isClosed || (dist < Toolbox.dist_allow)) && (curVx * curVx + curVy * curVy < 5);
		
		//System.out.println(isClosed? "true": "false");
		
		return isClosed;
	}

}
