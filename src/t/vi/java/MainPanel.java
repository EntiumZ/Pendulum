package t.vi.java;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Image img = Toolbox.getImage("image/ball.png");
	private Ball ball = null; 
	
	public MainPanel() {
	}	
	
	
	public void paint(Graphics g) {		
		super.paint(g);
		g.setColor(Color.BLUE);
		if(ball != null) {			
			g.drawOval(ball.getPositionX(), ball.getPositionY(), 50, 50);
			System.out.println(ball.getPositionX() +"," + ball.getPositionY() );
		}		
	}
	
	

	
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
//		System.out.println("x_press="+x+"\n");
//		System.out.println("y_press="+y+"\n");
		ball = new Ball(Toolbox.ballMass,x,y);
		this.repaint();
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
	 

}
