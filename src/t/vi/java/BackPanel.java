package t.vi.java;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *@author Lihua Zhao 
 */



public class BackPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BackPanel() {
		this.setSize(Toolbox.mainpanelWidth, Toolbox.mainpanelHeight);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Color temp = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillRect(200, 200, 300, 300);
		g.setColor(temp);
	}

}
