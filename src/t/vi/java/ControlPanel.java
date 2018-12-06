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
		
		this.add(valueSet);
		this.add(reset);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

}
