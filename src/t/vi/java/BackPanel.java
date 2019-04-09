package t.vi.java;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BackPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BackgroundCalEngine workEngine = null;
	private int[][]colorPatternCache = new int[80][80];
	private Color[] csets = Toolbox.colorset;
	private boolean isFirstPaint;
	
	public BackPanel() {
		this.setSize(Toolbox.mainpanelWidth, Toolbox.mainpanelHeight);
		this.setVisible(true);
		isFirstPaint = true;		
	}
	
	public void setSideEngine(BackgroundCalEngine sideEngine) {
		this.workEngine = sideEngine;
	}
	
	public boolean isFirstPaint() {
		return isFirstPaint;
	}

	public void setFirstPaint(boolean isFirstPaint) {
		this.isFirstPaint = isFirstPaint;
	}
	
		
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Color c = g.getColor();
		for(int i = 0; i < 799; i+=10) {
			for(int j = 0; j < 799; j+=10) {
				
				int index = 0;
				if(isFirstPaint) {
					workEngine.refresh();
					index = workEngine.computePath(i, j);
					colorPatternCache[i / 10][j / 10] =  index;						
				}else {
					index = colorPatternCache[i / 10][j / 10];
				}				
				g.setColor(csets[index]);				
				g.fillRect(i, j, 10, 10);
			}
		}			
		g.setColor(c);
		isFirstPaint = false;
	}	

}
