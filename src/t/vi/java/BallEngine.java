package t.vi.java;

/**
 * 
 * @author EntiumZ
 * Calculation engine for single pendulum trace
 * 
 */

import java.util.HashSet;
import java.util.Vector;

public class BallEngine extends EngineCore{
	
	private HashSet<Double> closedpoints = new HashSet<>();
	private Ball ball = null;	
	private int counter = 0;
	private int stopcounter = 0;
	
	//variables for computation
	private double curVx = 0.0, curVy = 0.0;
	private double preAx = 0.0, preAy = 0.0;
	private double curAx = 0.0, curAy = 0.0;
	private double nexAx = 0.0, nexAy = 0.0;	
	private int[] nextP = {0, 0};
	
	public BallEngine() {
		ball = new Ball(0, 0);
		ball.setEngine(this);
		setMagList(new MagnetsCollection().getMagList());		
		setFixedPoints();						
	}
	
	@Override
	public Vector<Magnet> getMagList() {
		return super.getMagList();
	}
	
	public Ball getBall() {
		return this.ball;		
	}
				
	public void refresh() {				
		preAx = 0.0; preAy = 0.0;
		curAx = 0.0; curAy = 0.0;
		nexAx = 0.0; nexAy = 0.0;
		curVx = 0.0; curVy = 0.0;
		counter = 0;
		stopcounter = 0;
	}		
	
	private double[] magforce = new double[2];
	private double[] gforce = new double[2];
	private double[] friction = new double[2];
	private double[] results = new double[2];
	
	public int[] m_Calculate() {
		
		magforce[0] = 0.0;
		magforce[1] = 0.0;
		gforce[0] = 0.0;
		gforce[1] = 0.0;
		friction[0] = 0.0;
		friction[1] = 0.0;		

 		this.getMagforce(magforce, ball.getPositionX(), ball.getPositionY());
		this.getGforce(gforce, ball.getPositionX(), ball.getPositionY());
		this.getfriction(friction, curVx, curVy);		

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
		
		counter++;
		if(counter > 300) {
			getClosedPoint(nextP[0], nextP[1], results);				
			closedpoints.add(results[0]);				
			if(((int)curVx == 0 && (int)curVy == 0)||(!closedpoints.add(results[0]))) {
				stopcounter++;
			}else if(closedpoints.add(results[0])) {
				stopcounter = 0;
			}
		}
		
		return nextP;
				
	}
	
	public boolean isBallStay() {		
		return stopcounter > 10 ? true : false;
	}	

}
