package t.vi.java;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainPanel mainpanel = new MainPanel();
	//private ControlPanel controlpanel = new ControlPanel();

	public MainFrame(String s) {
		super(s);
		this.init();
		this.add(mainpanel);
		
//		ControlPanel controlpanel = new ControlPanel();
//		this.add(controlpanel);
	}
	
	private void init() {
		this.setSize(Toolbox.frameWidth, Toolbox.frameHeigth);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void play() {
		
	}

}
