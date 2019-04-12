package t.vi.java;

/**
 * 
 * @author EntiumZ
 * Main frame window for the app
 * 
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private MainPanel mainpanel = null;	
	private BasinPanel backpanel = null;
	private TracePanel tracepanel = null;
	private ControlPanel controlpanel = null;
	private JLayeredPane layeredPanel = null;

	public MainFrame(String s) {
		super(s);			
		BallEngine ballengine = new BallEngine();
		BasinCalEngine basinengine = new BasinCalEngine();
		TraceCalEngine traceengine = new TraceCalEngine();
		
		layeredPanel = new JLayeredPane();
		mainpanel = new MainPanel();
		mainpanel.setEngine(ballengine);
		
		backpanel = new BasinPanel();
		backpanel.setSideEngine(basinengine);
		
		tracepanel = new TracePanel();
		tracepanel.setEngine(traceengine);
		tracepanel.setBall(ballengine.getBall());
		
		controlpanel = new ControlPanel();
		controlpanel.setBallEngine(ballengine);
		controlpanel.setMainpanel(mainpanel);
		
		controlpanel.setPattenEngine(basinengine);
		controlpanel.setBackpanel(backpanel);
		
		controlpanel.setTraceEngine(traceengine);
		controlpanel.setTracepanel(tracepanel);
		
		layeredPanel.add(backpanel, new Integer(0), 0);
		layeredPanel.add(tracepanel, new Integer(1), 0);
		layeredPanel.add(mainpanel, new Integer(2), 0);
		
		Thread t = new Thread(mainpanel);
		t.start();		
				
		this.setLayout(new BorderLayout());
		this.add(layeredPanel);		
		this.add(controlpanel,BorderLayout.EAST);
		
		this.addMouseListener(mainpanel);
		this.addMouseListener(tracepanel);
		
		this.setSize(Toolbox.frameWidth, Toolbox.frameHeigth);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		Insets temp = this.getInsets();
		mainpanel.setLeftadjust(temp.left);
		mainpanel.setTopadjust(temp.top);
		backpanel.setLeftadjust(temp.left);
		backpanel.setTopadjust(temp.top);
		tracepanel.setLeftadjust(temp.left);
		tracepanel.setTopadjust(temp.top);		
	}
}
