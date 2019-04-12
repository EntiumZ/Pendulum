package t.vi.java;

/**
 * 
 * @author EntiumZ
 * Calculation engine for pattern of the space as the background
 * 
 */

import java.util.HashSet;

public class BasinCalEngine extends EngineCore{
	
	private HashSet<Double> closedpoints = null;
	private int stopcounter = 0;
	private boolean isFinished;
	
	private double curVx = 0.0, curVy = 0.0;
	private double preAx = 0.0, preAy = 0.0;
	private double curAx = 0.0, curAy = 0.0;
	private double nexAx = 0.0, nexAy = 0.0;	
	
	public BasinCalEngine() {
		setMagList(new MagnetsCollection().getMagList());		
		setFixedPoints();
		closedpoints = new HashSet<>();
		isFinished = false;			
	}	
		
	public void refresh() {		
		preAx = 0.0; preAy = 0.0;
		curAx = 0.0; curAy = 0.0;
		nexAx = 0.0; nexAy = 0.0;
		curVx = 0.0; curVy = 0.0;
		isFinished = false;
		stopcounter = 0;
	}	
	
	public int m_ComputePaths(int x, int y) {
		
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
			friction[1] = 0.0;
			
			getMagforce(magforce, x, y);
			getGforce(gforce, x, y);
			getfriction(friction, curVx, curVy);

	 		curAx = magforce[0] + gforce[0] + friction[0];
			curAy = magforce[1] + gforce[1] + friction[1];			

	 		x += (int)(curVx + (4 * curAx - preAx) / 6);
			y += (int)(curVy + (4 * curAy - preAy) / 6);

	 		getMagforce(magforce, x, y);
			getGforce(gforce, x, y);

	 		nexAx = magforce[0] + gforce[0] + friction[0];
			nexAy = magforce[1] + gforce[1] + friction[1];

			curVx += (2 * nexAx + 5 * curAx - preAx) / 6;
			curVy += (2 * nexAy + 5 * curAy - preAy) / 6;			
			
	 		preAx = curAx;
			preAy = curAy;			
			
			intercounter++;
			if(intercounter > 300) {
				getClosedPoint(x, y, results);				
				closedpoints.add(results[0]);				
				if(((int)curVx == 0 && (int)curVy == 0)||(!closedpoints.add(results[0]))) {
					stopcounter++;
				}else if(closedpoints.add(results[0])) {
					stopcounter = 0;
				}
			}
			
			isFinished = stopcounter > 10 ? true: false;
			
		}
		getClosedPoint(x, y, results);				
		return (int)results[0];		
	}	
	
}
