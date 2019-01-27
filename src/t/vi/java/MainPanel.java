package t.vi.java;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseListener, Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
	private int leftadjust;
	private int topadjust;
	
	private Engine engine = null;
	private Thread t = null;
	
	public void setEngine(Engine e) {
		this.engine = e;
		t = new Thread(engine.getBall());
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
	
	private Ball ball = null; 
	
	public MainPanel() {
		this.setSize(Toolbox.mainpanelWidth, Toolbox.mainpanelHeight);
		this.setVisible(true);
	}	
	
	private Color[] colorset = {Color.blue, Color.red, Color.GREEN};
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		
		//draw magnets
		for(int i = 0; i < engine.getmagList().size(); i++) {
			g.setColor(colorset[i]);
			g.fillOval(engine.getmagList().get(i).getPositionX()-leftadjust-Toolbox.mag_size / 2, engine.getmagList().get(i).getPositionY()-topadjust-100 / 2, Toolbox.mag_size, Toolbox.mag_size);
		}
		
		//draw ball
		g.setColor(Color.black);
		if(ball != null) {			
			g.fillOval(ball.getPositionX()-leftadjust-Toolbox.ball_size/2, ball.getPositionY()-topadjust-Toolbox.ball_size/2, Toolbox.ball_size, Toolbox.ball_size);
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		int x = arg0.getX();
		int y = arg0.getY();
		engine.refresh();
		
		System.out.println(x + ", " + y);
		
		if(ball == null) {
			ball = engine.getBall();
			ball.setPositionX(x);
			ball.setPositionY(y);
		}
		
		
		
		if(ball.isStay() == true) {
			ball.setPositionX(x);
			ball.setPositionY(y);
			
			if(ball.getPositionX() < 800 && ball.getPositionY() < 800) {
				this.repaint();
				if(!t.isAlive()) {
					singleThreadExecutor.execute(t);					
				}				
			}
		}		
	}



	@Override
	public void run() {
		
		while(true) {
			
			try {
				Thread.sleep(15);
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
