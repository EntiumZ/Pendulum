package t.vi.java;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class TracePanel extends JPanel implements MouseListener{
	
	private static final long serialVersionUID = 1L;

	private int[][] trace = null;
	private boolean isReady = false;
	

	private TraceCalEngine engine = null;
	private Ball ball = null;
	
	private int leftadjust;
	private int topadjust;

	public TracePanel() {
		this.setSize(Toolbox.mainpanelWidth, Toolbox.mainpanelHeight);		
		this.setOpaque(false);
		this.setVisible(true);
	}
	
	public void setEngine(TraceCalEngine e) {
		this.engine = e;		
	}
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);		
		if(isReady)
		for(int i = 0; i < trace.length; i++) {
			g.fillOval(trace[i][0] - leftadjust - 4, trace[i][1] - topadjust - 4, 8, 8);
		}		
	}	

	@Override
	public void mouseClicked(MouseEvent arg0) {		
		if(ball.isStay()) {
			int x = arg0.getX();
			int y = arg0.getY();
			engine.refresh();
			trace = engine.computePath(x, y);
			isReady = true;
			repaint();			
		}		
	}
	
	public void setBall(Ball ball) {
		this.ball = ball;
	}
	
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	public int getLeftadjust() {
		return leftadjust;
	}

	public void setLeftadjust(int leftadjust) {
		this.leftadjust = leftadjust;
	}
		
	public int getTopadjust() {
		return topadjust;
	}

	public void setTopadjust(int topadjust) {
		this.topadjust = topadjust;
	}
	
	//no implements
	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}
	
}
