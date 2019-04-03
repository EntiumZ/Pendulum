package t.vi.java;

/**
 * @author Lihua Zhao
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private MainPanel mainpanel = null;	
	private ControlPanel controlpanel = null;	 

	public MainFrame(String s) {
		super(s);			
		CalEngine engine = new CalEngine();		
		
		mainpanel = new MainPanel();
		mainpanel.setEngine(engine);
				
		controlpanel = new ControlPanel();
		controlpanel.setEngine(engine);
		controlpanel.setMainpanel(mainpanel); 
		Thread t = new Thread(mainpanel);
		t.start();		
				
		this.setLayout(new BorderLayout());
		this.add(mainpanel);	
		this.add(controlpanel,BorderLayout.EAST);
		
		this.addMouseListener(mainpanel);
		
		this.setSize(Toolbox.frameWidth, Toolbox.frameHeigth);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		Insets temp = this.getInsets();
		mainpanel.setLeftadjust(temp.left);
		mainpanel.setTopadjust(temp.top);		
	}
}
