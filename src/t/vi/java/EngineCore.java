package t.vi.java;

import java.util.HashMap;
import java.util.Vector;

public class EngineCore {
	
	private Vector<Magnet>magList = new Vector<Magnet>();
	private HashMap<Integer, Integer[]> fixedPoints= new HashMap<>();
	
	private double kg = Toolbox.kg_0;
	private double kf = Toolbox.kf_0;
	
	protected Vector<Magnet> getMagList() {
		return magList;
	}

	protected void setMagList(Vector<Magnet> magList) {
		this.magList = magList;
	}

	protected double getKg() {
		return kg;
	}

	protected void setKg(double kg) {
		this.kg = kg;
	}

	protected double getKf() {
		return kf;
	}

	protected void setKf(double kf) {
		this.kf = kf;
	}	

	public EngineCore() {
	}	
	
	protected void setFixedPoints() {
		fixedPoints.put(new Integer(0), new Integer[]{Toolbox.mainpanelWidth / 2, Toolbox.mainpanelWidth / 2});
		for(int i = 0; i < magList.size(); i++) {
			fixedPoints.put(new Integer(i + 1), new Integer[]{magList.get(i).getPositionX(), magList.get(i).getPositionY()});
		}
	}
	
	protected void getClosedPoint(int px, int py, double[] results) {
		
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
	
	
	protected void getfriction(double[] result, double curVx, double curVy) {
		if(result == null || result.length != 2) {
			result = new double[2];
		}		
		result[0] = -1 * kf * curVx;
		result[1] = -1 * kf * curVy;
	}

}
