package t.vi.java;

import java.awt.BorderLayout;

/**
 * @author Lihua Zhao
 * 
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private int textlength = 5;
	
	private JLabel jlTitle = new JLabel("Magnetic");
	private String[] slabel = {"Mass:", "Strength Coefficient:", "Gravity Coefficient:", "Friction Coefficient:"}; 
	private JLabel[] jlLabels = new JLabel[4];
	private JTextField[] jtFields = new JTextField[4];
	private JButton jbReset = new JButton("reset");
	private JButton jbValueSet = new JButton("OK");
	private JPanel[] jpPanels = new JPanel[4];
	private JPanel jpMainpanel = null;
	private JButton jbDefault = new JButton("Default Setting");	
	
	private CalEngine engine = null;	
	private Ball ball = null;
	
	public ControlPanel() {		
		this.panelSet();		
	}
	
	public void setEngine(CalEngine e) {
		this.engine = e;
		this.ball = engine.getBall();
	}
	
	public void setMainpanel(JPanel p) {
		jpMainpanel = p;
	}
	
	//Panel contents
	private void panelSet() {
		
		this.setLayout(new GridLayout(4,1));
		
		for(int i = 0; i < 4; i++) {
			jpPanels[i] = new JPanel();
			jpPanels[i].setSize(Toolbox.controlpanelWidth, Toolbox.controlpanelHeight / 4);
			jpPanels[i].setBackground(Color.white);
			this.add(jpPanels[i]);
		}
		
		jpPanels[0].setLayout(new BorderLayout());
		jpPanels[0].add(jlTitle, BorderLayout.CENTER);
		String text = "<html>Magnetic<br/>Pendulum</html>";
		jlTitle.setText(text);
		Font font = new Font("Dialog", 1, 50);
		jlTitle.setFont(font);
		jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		for(int i = 0; i < 4; i++) {
			jlLabels[i] = new JLabel(slabel[i]);
			jtFields[i] = new JTextField(textlength);
		}
		
		jpPanels[1].setLayout(new GridLayout(5,2,20,10));
		
		setLabelsText();
				
		jpPanels[1].add(jbValueSet);		
		jpPanels[1].add(jbReset);
		this.setSize(Toolbox.controlpanelWidth, Toolbox.controlpanelHeight);
		jbValueSet.addActionListener(this);
		jbReset.addActionListener(this);
		
		jpPanels[2].add(jbDefault);
		jbDefault.addActionListener(this);
	}
	
	private void setLabelsText() {		
		for(int i = 0; i < 4; i++) {
			jpPanels[1].add(jlLabels[i]);
			jpPanels[1].add(jtFields[i]);
			jtFields[i].setText(Double.toString(Toolbox.coeff[i]));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == jbReset) {
			ball.setStay(true);
			ball.wipeBall();
			jpMainpanel.repaint();
		}else if(ae.getSource() == jbValueSet && ball != null) {
			if(ball.isStay() == true) {
				ball.setMass(Double.valueOf(jtFields[0].getText()));
				Vector<Magnet> magList = engine.getmagList(); 
				for(int i = 0; i < magList.size(); i++) {
					magList.get(i).setKm(Double.valueOf(jtFields[1].getText()));					
				}
				engine.setKg(Double.valueOf(jtFields[2].getText()));
				engine.setKf(Double.valueOf(jtFields[3].getText()));
			}			
		}else if(ae.getSource() == jbDefault) {
			ball.setStay(true);
			ball.wipeBall();
			jpMainpanel.repaint();
			ball.setMass(Toolbox.mass_0);
			Vector<Magnet> magList = engine.getmagList(); 
			for(int i = 0; i < magList.size(); i++) {
				magList.get(i).setKm(Toolbox.km_0);					
			}
			engine.setKg(Toolbox.kg_0);
			engine.setKf(Toolbox.kf_0);			
			setLabelsText();
		}		
	}

}
