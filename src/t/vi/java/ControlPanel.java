package t.vi.java;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlPanel() {		
		this.init();
		this.panelSet();
	}
	
	//basic properties of ControlPanel
	private void init() {
		Dimension size = getPreferredSize();
		size.width = Toolbox.controlpanelWidth;
		size.height = Toolbox.controlpanelHeight;
		setPreferredSize(size);
	}
	
	//Panel contents
	private void panelSet() {
		JButton valueSet = new JButton("OK");
		this.add(valueSet);

	}

}
