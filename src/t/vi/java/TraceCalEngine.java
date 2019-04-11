package t.vi.java;

public class TraceCalEngine extends EngineCore{
	
	private double curVx = 0.0, curVy = 0.0;
	private double preAx = 0.0, preAy = 0.0;
	private double curAx = 0.0, curAy = 0.0;
	private double nexAx = 0.0, nexAy = 0.0;
	private int trace[][] = new int[301][2];

	public TraceCalEngine() {
		setMagList(new MagnetsCollection().getMagList());
	}
	
	public void refresh() {
		preAx = 0.0; preAy = 0.0;
		curAx = 0.0; curAy = 0.0;
		nexAx = 0.0; nexAy = 0.0;
		curVx = 0.0; curVy = 0.0;
	}
	
	public int[][] computePath(int x, int y) {
		
		double[] magforce = new double[2];
		double[] gforce = new double[2];
		double[] friction = new double[2];
		
		trace[0][0] = x;
		trace[0][1] = y;
				
		for(int i = 1; i < 301; i++) {
			
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
			
			trace[i][0] = x;
			trace[i][1] = y;			
		}
		
		return trace;		
	}
	

}
