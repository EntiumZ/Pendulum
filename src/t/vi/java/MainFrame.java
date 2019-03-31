package t.vi.java;

/**
 * @author Lihua Zhao
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private MainPanel mainpanel = null;
	private BackPanel backpanel = null;
	private ControlPanel controlpanel = null;
	private JLayeredPane layerPane = null; 

	public MainFrame(String s) {
		super(s);			
		Engine engine = new Engine();
		
		layerPane = new JLayeredPane();
		
		mainpanel = new MainPanel();
		mainpanel.setEngine(engine);
		
		backpanel = new BackPanel();
		
		controlpanel = new ControlPanel();
		controlpanel.setEngine(engine);
		 
		Thread t = new Thread(mainpanel);
		t.start();
		 
		layerPane.add(backpanel, new Integer(0), 0);
		layerPane.add(mainpanel, new Integer(1), 0);
				
		this.setLayout(new BorderLayout());
		this.add(layerPane);	
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
