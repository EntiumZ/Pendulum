package t.vi.java;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Image img = Toolbox.getImage("image/ball.png");
	private Ball ball = new Ball(Toolbox.ballMass);
	
	public MainPanel() {
		Dimension size = getPreferredSize();
		size.width = Toolbox.mainpanelWidth;
		size.height = Toolbox.mainpanelHeight;
		setPreferredSize(size);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
//				ball.setPositionX(e.getX());
//				ball.setPositionY(e.getY());
				moveBall(e.getX(),e.getY());
			}
		});
	}	
	
	
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		g.drawOval(400, 400, 200, 200);
		ball.paintBall(g);
		
	}
	
	private void moveBall(int x, int y) {
		final int CURR_X = ball.getPositionX();
		final int CURR_Y = ball.getPositionY();
		final int OFFSET = 1;
		
		if ((CURR_X!=x) || (CURR_Y!=y)) {

            // The square is moving, repaint background 
            // over the old square location. 
            repaint(CURR_X,CURR_Y,CURR_X+OFFSET,CURR_Y+OFFSET);

            // Update coordinates.
            ball.setPositionX(x);
            ball.setPositionY(y);

            // Repaint the square at the new location.
            repaint(ball.getPositionX(), ball.getPositionY(), 
            		ball.getPositionX()+OFFSET, 
            		ball.getPositionY()+OFFSET);
        }
		
	}

	
	class Ball {
		private int positionX = 0;
		private int positionY = 0;
		private int mass = Toolbox.ballMass;
		
		
		public Ball(int m) {
			this.setMass(m);
		}

		public int getPositionX() {
			return positionX;
		}

		public void setPositionX(int positionX) {
			this.positionX = positionX;
		}

		public int getPositionY() {
			return positionY;
		}

		public void setPositionY(int positionY) {
			this.positionY = positionY;
		}

		public int getMass() {
			return mass;
		}

		public void setMass(int mass) {
			this.mass = mass;
		}

		public void paintBall(Graphics g) {
			g.drawImage(img, ball.getPositionX(), ball.getPositionY(), null);
			
//			while(true) {
//				positionX += 3;
//				positionY += 3;
//				//this.panel.
//				
//				try {
//					Thread.sleep(100);
//				}catch(InterruptedException e) {
//					e.printStackTrace();
//				}
//				
//				
//			}
		}
	}
	 

}
