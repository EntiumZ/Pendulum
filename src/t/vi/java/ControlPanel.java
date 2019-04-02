package t.vi.java;

/**
 * @author Lihua Zhao
 * 
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private int textlength = 5;
	
	private String[] slabel = {"Mass:", "Strength Coefficient:", "Gravity Coefficient:", "Friction Coefficient:"}; 
	private JLabel[] labels = new JLabel[4];
	private JTextField[] fields = new JTextField[4];
	private JButton reset = new JButton("reset");
	private JButton valueSet = new JButton("OK");
	private JPanel[] ps = new JPanel[4];
	
	private Engine engine = null;	
	private Ball ball = null;
	
	public ControlPanel() {		
		this.panelSet();	
	}
	
	public void setEngine(Engine e) {
		this.engine = e;
		this.ball = engine.getBall();
	}
	
	//Panel contents
	private void panelSet() {
		
		this.setLayout(new GridLayout(4,1));
		
		for(int i = 0; i < 4; i++) {
			ps[i] = new JPanel();
			ps[i].setSize(Toolbox.controlpanelWidth, Toolbox.controlpanelHeight / 4);
			ps[i].setBackground(Color.white);
			this.add(ps[i]);
		}
		
		for(int i = 0; i < 4; i++) {
			labels[i] = new JLabel(slabel[i]);
			fields[i] = new JTextField(textlength);
		}
		
		ps[1].setLayout(new GridLayout(5,2,20,10));
		
		for(int i = 0; i < 4; i++) {
			ps[1].add(labels[i]);
			ps[1].add(fields[i]);
			fields[i].setText(Double.toString(Toolbox.coeff[i]));
		}
				
		ps[1].add(valueSet);		
		ps[1].add(reset);
		this.setSize(Toolbox.controlpanelWidth, Toolbox.controlpanelHeight);
		valueSet.addActionListener(this);
		reset.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == reset) {
			ball.setStay(true);						
		}else if(ae.getSource() == valueSet && ball != null) {
			if(ball.isStay() == true) {
				ball.setMass(Double.valueOf(fields[0].getText()));
				System.out.println("ball mass: " + ball.getMass());
			}			
		}		
	}

}
