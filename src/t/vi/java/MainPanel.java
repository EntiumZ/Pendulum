package t.vi.java;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseListener, Runnable{
	/**
	 * 
	 */
	
	private int leftadjust;
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


	private int topadjust;
	private static final long serialVersionUID = 1L;
	public static final Image img = Toolbox.getImage("image/ball.png");
	private Ball ball = null; 
	
	public MainPanel() {
	}	
	
	
	
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
		if(ball == null) {
			ball = Ball.getBall();
			ball.setPositionX(x);
			ball.setPositionY(y);
			ball.setMass(Toolbox.ballMass);
		}
		if(ball != null) {
			if(ball.getPositionX() < 400 && ball.getPositionX() < 400) {
				Thread t = new Thread(ball);
				t.start();
				this.repaint();				
			}			
		}

		// TODO Auto-generated method stub
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while(true) {
			if(ball!=null) {
				if(ball.getPositionX() >400 && ball.getPositionY() >400) {
					break;
				}				
			}
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.repaint();
		}		
	}
	 

}
