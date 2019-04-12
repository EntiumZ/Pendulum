package t.vi.java;

/**
 * 
 * @author EntiumZ
 * Panel for parameters set and reset program
 * 
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private int textlength = 5;
	
	private JLabel jlTitle = new JLabel("Magnetic");
	private String[] slabel = {"Strength Coefficient:", "Gravity Coefficient:", "Friction Coefficient:"}; 
	private JLabel[] jlLabels = new JLabel[3];
	private JLabel kmLabel = new JLabel("1.0"); //label for km coefficient
	private JTextField[] jtFields = new JTextField[3];
	private JButton jbReset = new JButton("reset");
	private JButton jbOk = new JButton("OK");
	private JPanel[] jpPanels = new JPanel[4];
	
	private JButton jbDefault = new JButton("Default Setting");	
	
	private MainPanel mainpanel = null;
	private BasinPanel basinpanel = null;
	private TracePanel tracepanel = null;
	
	private BallEngine ballEngine = null;
	private BasinCalEngine basinEngine = null;
	private TraceCalEngine traceEngine = null; 

	private Ball ball = null;
	
	public ControlPanel() {		
		this.panelContentSet();		
	}
	
	public void setBallEngine(BallEngine engine) {
		this.ballEngine = engine;
		this.ball = ballEngine.getBall();
	}
	
	public void setPattenEngine(BasinCalEngine engine) {
		basinEngine = engine;
	}
	
	public void setTraceEngine(TraceCalEngine engine) {
		traceEngine = engine;
	}
	
	public void setMainpanel(MainPanel p) {
		mainpanel = p;
	}
	
	public void setBackpanel(BasinPanel p) {
		basinpanel = p;
	}
	
	public void setTracepanel(TracePanel p) {
		tracepanel = p;
	}
	
	//Panel contents
	private void panelContentSet() {		
		//whole panel consists 4 sub-panel
		this.setLayout(new GridLayout(4,1));		
		for(int i = 0; i < 4; i++) {
			jpPanels[i] = new JPanel();
			jpPanels[i].setSize(Toolbox.controlpanelWidth, Toolbox.controlpanelHeight / 4);
			jpPanels[i].setBackground(Color.white);
			this.add(jpPanels[i]);
		}
		
		//sub-panel 0 setup, display title
		jpPanels[0].setLayout(new BorderLayout());
		jpPanels[0].add(jlTitle, BorderLayout.CENTER);
		String text = "<html>Magnetic<br/>Pendulum</html>";
		jlTitle.setText(text);
		Font font = new Font("Dialog", 1, 50);
		jlTitle.setFont(font);
		jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);		
		
		//sub-panel 1 setup, add labels and set text field
		jlLabels[0] = new JLabel(slabel[0]);
		for(int i = 1; i < 3; i++) {
			jlLabels[i] = new JLabel(slabel[i]);
			jtFields[i] = new JTextField(textlength);
		}
		
		jpPanels[1].setLayout(new GridLayout(5,2,20,10));
		
		setLabelsText();
		
		//two buttons
		jpPanels[1].add(jbOk);		
		jpPanels[1].add(jbReset);
		this.setSize(Toolbox.controlpanelWidth, Toolbox.controlpanelHeight);
		jbOk.addActionListener(this);
		jbReset.addActionListener(this);
		
		//sub-panel 2 setup, only add 1 button for default reset
		jpPanels[2].add(jbDefault);
		jbDefault.addActionListener(this);
	}
	
	private void setLabelsText() {
		jpPanels[1].add(jlLabels[0]);
		jpPanels[1].add(kmLabel);
		for(int i = 1; i < 3; i++) {
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
			mainpanel.repaint();
			tracepanel.setReady(false);
			tracepanel.repaint();
		}else if(ae.getSource() == jbOk && ball != null) {
			if(ball.isStay() == true) {				
				double kg = Double.valueOf(jtFields[1].getText());
				double kf = Double.valueOf(jtFields[2].getText());
				
				ballEngine.setKg(kg);
				ballEngine.setKf(kf);				
				basinEngine.setKg(kg);
				basinEngine.setKf(kf);				
				traceEngine.setKg(kg);
				traceEngine.setKf(kf);
				
				ball.wipeBall();
				mainpanel.repaint();				
				tracepanel.setReady(false);
				tracepanel.repaint();				
				basinpanel.setFirstPaint(true);
				basinpanel.repaint();
			}			
		}else if(ae.getSource() == jbDefault) {
			setLabelsText();
			
			ballEngine.setKg(Toolbox.kg_0);
			ballEngine.setKf(Toolbox.kf_0);
			basinEngine.setKg(Toolbox.kg_0);
			basinEngine.setKf(Toolbox.kf_0);
			traceEngine.setKg(Toolbox.kg_0);
			traceEngine.setKf(Toolbox.kf_0);
			
			ball.setStay(true);
			ball.wipeBall();
			mainpanel.repaint();
			basinpanel.setFirstPaint(true);
			basinpanel.repaint();
			tracepanel.setReady(false);
			tracepanel.repaint();			
		}		
	}

}
