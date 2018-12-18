package t.vi.java;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	
	JButton reset = new JButton("reset");
	JButton valueSet = new JButton("OK");
	private static final long serialVersionUID = 1L;
	
	private static Ball ball = null;
	
	public static void setBall(Ball b) {
		ball = b;
	}

	public ControlPanel() {		
		this.init();
		this.panelSet();
	}
	
	//basic properties of ControlPanel
	private void init() {
		this.setSize(Toolbox.controlpanelWidth, Toolbox.controlpanelHeight);
	}
	
	//Panel contents
	private void panelSet() {
		
		this.add(valueSet);
		this.add(reset);
		reset.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ball.setStay(true);
		System.out.println(ball.getPositionX() + "%%" +ball.getPositionY());
	}

}
