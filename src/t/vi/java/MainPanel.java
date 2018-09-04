package t.vi.java;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainPanel() {
		Dimension size = getPreferredSize();
		size.width = Toolbox.mainpanelWidth;
		size.height = Toolbox.mainpanelHeight;
		setPreferredSize(size);
		addMouseListener(this);
	}
	
	private Ball ball = new Ball(0,0,Toolbox.ballMass);
	
	public void setBall(Ball b) {
		this.ball = b;
	}
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		g.drawOval(400, 400, 200, 200);
		g.drawImage(Ball.img, ball.getPositionX(), ball.getPositionY(), null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		ball.setPositionX(e.getX());
//		ball.setPositionY(e.getY());
//		ball.setPanel(this);
//		ball.run();
		System.out.println(e.getX() + "/" +e.getY() + "/mouse click");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	 

}
