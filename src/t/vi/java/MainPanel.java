package t.vi.java;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseListener, Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int leftadjust;
	private int topadjust;
	
	private Engine engine = null;
	
	public void setEngine(Engine e) {
		this.engine = e;
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

	public static final Image img = Toolbox.getImage("image/ball.png");
	private Ball ball = null; 
	
	public Ball getBall() {
		return this.ball;
	}
	
	public MainPanel() {
		this.setSize(Toolbox.mainpanelWidth, Toolbox.mainpanelHeight);
		this.setVisible(true);
	}	
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		g.setColor(Color.BLUE);
		if(ball != null) {			
			g.fillOval(ball.getPositionX()-leftadjust-Toolbox.ball_size/2, ball.getPositionY()-topadjust-Toolbox.ball_size/2, Toolbox.ball_size, Toolbox.ball_size);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		System.out.println("x= " + x +" y= " + y +"\n");
		if(ball == null || ball.isStay() == true) {
			ball = engine.getBall();
			ball.setPositionX(x);
			ball.setPositionY(y);
		}
		if(ball != null && ball.isStay() == true) {
			if(ball.getPositionX() < 400 && ball.getPositionY() < 400) {
				this.repaint();
				Thread t = new Thread(ball);
				t.start();
			}			
		}
	}



	@Override
	public void run() {
		
		while(true) {
			if(ball!=null && ball.isStay() == false) {
				if(ball.getPositionX() >400 && ball.getPositionY() >400) {
					ball.setStay(true);
				}
			}
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(ball != null && ball.isStay() == false) {
				this.repaint();
			}
			
		}		
	}
	
	//implementation not need
		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}	 

}
