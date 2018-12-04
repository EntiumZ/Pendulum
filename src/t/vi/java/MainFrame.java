package t.vi.java;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	
	private MainPanel mainpanel = null;

	public MainFrame(String s) {
		super(s);
		mainpanel = new MainPanel();
		this.add(mainpanel);
		this.addMouseListener(mainpanel);
		this.init();
	}
	
	private void init() {
		this.setSize(Toolbox.frameWidth, Toolbox.frameHeigth);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
