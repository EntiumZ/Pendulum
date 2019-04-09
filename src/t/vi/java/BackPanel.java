package t.vi.java;

import java.awt.Color;
import java.awt.Graphics;
//import java.util.Random;

import javax.swing.JPanel;

public class BackPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BackgroundCalEngine sideEngine = null;
	private int[][]colorPatternCache = new int[80][80];
	private Color[] csets = Toolbox.colorset;
	private boolean isFirstPaint;
	
	//private Random random = new Random();

	public BackPanel() {
		this.setSize(Toolbox.mainpanelWidth, Toolbox.mainpanelHeight);
		this.setVisible(true);
		isFirstPaint = true;		
	}
	
	public void setSideEngine(BackgroundCalEngine sideEngine) {
		this.sideEngine = sideEngine;
	}
		
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Color c = g.getColor();
		for(int i = 0; i < 799; i+=10) {
			for(int j = 0; j < 799; j+=10) {
				
				int index = 0;
				if(isFirstPaint) {
					sideEngine.refresh();
					index = sideEngine.computePath(i, j);
					colorPatternCache[i / 10][j / 10] =  index;						
				}else {
					index = colorPatternCache[i / 10][j / 10];
				}				
				
				//int index = random.nextInt(4);
				g.setColor(csets[index]);
				//g.setColor(csets[4]);				
				g.fillRect(i, j, 10, 10);
			}
		}			
		g.setColor(c);
		isFirstPaint = false;
	}	

}
