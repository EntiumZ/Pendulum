package t.vi.java;

/**
 * @author Lihua Zhao
 * Calculation engine for pattern of the space
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class SingleCalEngine{
	
	private CalEngine mainEngine = null;	
	private Vector<Magnet> magLists = null;
	private HashMap<Integer, Integer[]> fixedPoints= null;
	private HashSet<Double> closedpoints = null;
	private boolean isFinished;
	
	private double curVx = 0.0, curVy = 0.0;
	private double preAx = 0.0, preAy = 0.0;
	private double curAx = 0.0, curAy = 0.0;
	private double nexAx = 0.0, nexAy = 0.0;	
	
	private double kg = 0.0;
	private double kf = 0.0;
	
	private int stopcounter = 0;	

	public SingleCalEngine(CalEngine e) {
		setmainEngine(e);
		setParameters();
		setFixedPoints();
		closedpoints = new HashSet<>();
		isFinished = false;			
	}	
	
	public void setmainEngine(CalEngine e) {
		mainEngine = e;
	}
	
	public void setParameters() {
		magLists = mainEngine.getmagList();
		kg = mainEngine.getKg();
		kf = mainEngine.getKf();
		
	}
	
	private void setFixedPoints() {		
		fixedPoints= new HashMap<>();
		fixedPoints.put(new Integer(0), new Integer[]{Toolbox.mainpanelWidth / 2, Toolbox.mainpanelWidth / 2});
		for(int i = 0; i < magLists.size(); i++) {
			fixedPoints.put(new Integer(i + 1), new Integer[]{magLists.get(i).getPositionX(), magLists.get(i).getPositionY()});
		}
	}
	
	public void refresh() {		
		preAx = 0.0; preAy = 0.0;
		curAx = 0.0; curAy = 0.0;
		nexAx = 0.0; nexAy = 0.0;
		curVx = 0.0; curVy = 0.0;
		isFinished = false;
		stopcounter = 0;
	}	
	
	public int computePath(int x, int y) {
		
		double[] magforce = new double[2];
		double[] gforce = new double[2];
		double[] friction = new double[2];
		double[] results = new double[2];
		
		int intercounter = 0;
				
		while(!isFinished) {
			
			magforce[0] = 0.0;
			magforce[1] = 0.0;
			gforce[0] = 0.0;
			gforce[1] = 0.0;
			friction[0] = 0.0;
			friction[0] = 0.0;
			
			getMagforce(magforce, x, y);
			getGforce(gforce, x, y);
			getfriction(friction);

	 		curAx = magforce[0] + gforce[0] + friction[0];
			curAy = magforce[1] + gforce[1] + friction[1];			

	 		x = x + (int)(curVx + (4 * curAx - preAx) / 6);
			y = y + (int)(curVy + (4 * curAy - preAy) / 6);

	 		getMagforce(magforce, x, y);
			getGforce(gforce, x, y);

	 		nexAx = magforce[0] + gforce[0] + friction[0];
			nexAy = magforce[1] + gforce[1] + friction[1];

			curVx = curVx + (2 * nexAx + 5 * curAx - preAx) / 6;
			curVy = curVy + (2 * nexAy + 5 * curAy - preAy) / 6;			
			
	 		preAx = curAx;
			preAy = curAy;	
			
			//isFinished = isComputeDone(x, y);
			isFinished = stopcounter > 10 ? true: false;
			
//			temp[0] = x;
//			temp[1] = y;
			
			intercounter++;
			if(intercounter > 300) {
				getMinDistance(x, y, results);
				
				closedpoints.add(results[0]);
				
				if(((int)curVx == 0 && (int)curVy == 0)||(!closedpoints.add(results[0]))) {
					stopcounter++;
				}else if(closedpoints.add(results[0])) {
					stopcounter = 0;
				}
				//System.out.println("x= " + x + "y= " + y);
				//System.out.println((int)Math.sqrt(curVx * curVx + curVy * curVy));
			}
			
		}
		getMinDistance(x, y, results);
		//System.out.println("x= " + temp[0] + "y= " + temp[1]);
				
		return (int)results[0];
		
	}	
	
	private void getMinDistance(int px, int py, double[] results) {
		
		if(results == null || results.length != 2) {
			results = new double[2];
		}
		
		double minNorm = Toolbox.minNorm_initial;
		double minIndex = 0.0;
		
		for(int i = 0; i < fixedPoints.size(); i++) {
			Vect vec = new Vect(px, py, fixedPoints.get(i)[0], fixedPoints.get(i)[1]);
			double norm = vec.getNorm();
			
			if(norm < minNorm) {
				minNorm = norm;
				minIndex = (double)i;
			}
			
		}
		
		results[0] = minIndex;
		results[1] = minNorm;		
	}
	
	
//	private boolean isComputeDone(int x, int y) {
//		double v = Math.sqrt(curVx * curVx + curVy * curVy);
//		double[] results = new double[2];
//		getMinDistance(x, y, results);
//		double minDistance = results[1];
//		if(((int)v == 0 || (int)v == 1) && minDistance < Toolbox.dis_allow){
//			return true;
//		}
//		
//		Vect vect = new Vect(x, y, Toolbox.mainpanelWidth / 2, Toolbox.mainpanelHeight / 2);
//		double markDistance = vect.getNorm();
//		
////		if(stopcounter > 5) {
////			if((int)curVx == 0 && (int)curVy == 0 && minDistance == markDistance) {
////				if(minDistance < 100.0) {
////					return true;
////				}
////			}
////			
////		}
//				
//		if(stopcounter > 10) {		
//			return true;			
//		}
//		
//		return false;
//	}
	
	private void getMagforce(double[] result, int px, int py) {		
		if(result == null || result.length != 2) {
			result = new double[2];
		}
		
		for(int i = 0; i < magLists.size(); i++) {
			Vect vec = new Vect(px, py, magLists.get(i).getPositionX(), magLists.get(i).getPositionY());
			double norm = vec.getNorm();
			double temp = Math.sqrt(norm * norm + 40 * 40);
			double x = (double)vec.getpX();
			double y = (double)vec.getpY();			
			result[0] += magLists.get(i).getKm() * 10000.0 * x / (temp * temp * temp);
			result[1] += magLists.get(i).getKm() * 10000.0 * y / (temp * temp * temp);			
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
	
	

}
