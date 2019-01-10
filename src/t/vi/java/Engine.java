package t.vi.java;

import java.util.LinkedList;

public class Engine {
	
	private LinkedList<Integer> xList = new LinkedList<Integer>();
	private LinkedList<Integer> yList = new LinkedList<Integer>();
	private LinkedList<Integer> aList = new LinkedList<Integer>();
	
	private int x_t, y_t, v_t;
	private int x_lt, y_lt, v_lt;
	private int x_nt, y_nt, v_nt;
	private int a_t, a_lt;
	
	private Ball ball = Ball.getBall();
	
	public Ball getBall() {
		return this.ball;
	}

	public Engine() {
		
	}
	
	public void setP(int x, int y) {
		this.x_t = x;
		this.y_t = y;
	}
	
	public void listClean() {
		xList.clear();
		yList.clear();
		aList.clear();
	}
	
	public void calX() {
		x_nt = x_t + v_t + (4 * a_t - a_lt) / 6;
	}
	
	private void calA() {
		
	}

}
