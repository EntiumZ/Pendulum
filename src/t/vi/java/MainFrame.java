package t.vi.java;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MainPanel mainpanel = null;
	private ControlPanel controlpanel = null;

	public MainFrame(String s) {
		super(s);
		mainpanel = new MainPanel();
		controlpanel = new ControlPanel();
		Thread t = new Thread(mainpanel);
		t.start();
		this.setLayout(new GridLayout(1,2));
		this.add(mainpanel);
		this.add(controlpanel);
		this.addMouseListener(mainpanel);
		this.setSize(Toolbox.frameWidth, Toolbox.frameHeigth);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		Insets temp = this.getInsets();
		mainpanel.setLeftadjust(temp.left);
		mainpanel.setTopadjust(temp.top);		
	}
}
