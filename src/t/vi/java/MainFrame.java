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
		Thread t = new Thread(mainpanel);
		t.start();
		this.add(mainpanel);
		this.addMouseListener(mainpanel);		
		this.setSize(Toolbox.frameWidth, Toolbox.frameHeigth);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		Insets temp = this.getInsets();
		mainpanel.setLeftadjust(temp.left);
		mainpanel.setTopadjust(temp.top);
	}
	
//	private void init() {
//		this.setSize(Toolbox.frameWidth, Toolbox.frameHeigth);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setVisible(true);
//	}

}
