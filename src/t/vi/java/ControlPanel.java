package t.vi.java;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel implements ActionListener{
	
	private int textlength = 4;
	
	private static final long serialVersionUID = 1L;
	
	private JLabel mass = new JLabel("Mass:");
	private JTextField mass_input = new JTextField(textlength);
	
	private JLabel km = new JLabel("Strengh Coefficient:");
	private JTextField km_input = new JTextField(textlength);
	
	private JLabel kg = new JLabel("Gravity Coefficient:");
	private JTextField kg_input = new JTextField(textlength);
	
	private JLabel kf = new JLabel("Friction Coefficient:");
	private JTextField kf_input = new JTextField(textlength);
	
	private JButton reset = new JButton("reset");
	private JButton valueSet = new JButton("OK");
		
	private static Ball ball = null;
	
	private JPanel[] ps = new JPanel[4];
	
	public static void setBall(Ball b) {
		ball = b;
	}

	public ControlPanel() {
		
		this.panelSet();
	
	}
	
	//Panel contents
	private void panelSet() {
		
		this.setLayout(new GridLayout(4,1));
		
		for(int i = 0; i < 4; i++) {
			ps[i] = new JPanel();
			ps[i].setSize(Toolbox.controlpanelWidth, Toolbox.controlpanelHeight / 4);
			this.add(ps[i]);
		}
		
		ps[1].setLayout(new GridLayout(5,2,10,10));
		ps[1].add(mass);
		ps[1].add(mass_input);
		ps[1].add(kf);
		ps[1].add(kf_input);
		ps[1].add(kg);
		ps[1].add(kg_input);
		ps[1].add(km);
		ps[1].add(km_input);
		ps[1].add(valueSet);		
		ps[1].add(reset);
		this.setSize(Toolbox.controlpanelWidth, Toolbox.controlpanelHeight);		
		reset.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ball.setStay(true);
		System.out.println(ball.getPositionX() + "%%" +ball.getPositionY());
	}

}
