package t.vi.java;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BackPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BackgroundCalEngine workEngine = null;
	private int[][]colorPatternCache = new int[801][801];
	private Color[] csets = Toolbox.colorset;
	private boolean isFirstPaint;
	
	private int leftadjust;
	private int topadjust;
		
	public BackPanel() {
		this.setSize(Toolbox.mainpanelWidth, Toolbox.mainpanelHeight);
		this.setVisible(true);
		isFirstPaint = true;		
	}
	
	public void setSideEngine(BackgroundCalEngine sideEngine) {
		this.workEngine = sideEngine;
	}

	public void setFirstPaint(boolean isFirstPaint) {
		this.isFirstPaint = isFirstPaint;
	}	
		
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int index = 0;
		for(int i = 0; i < 799; i+=5) {
			for(int j = 0; j < 799; j+=5) {
				if(isFirstPaint) {
					workEngine.refresh();
					index = workEngine.computePath(i, j);
					colorPatternCache[i / 5][j / 5] =  index;						
				}else {
					index = colorPatternCache[i / 5][j / 5];
				}				
				g.setColor(csets[index]);				
				g.fillRect(i - leftadjust - 5, j - topadjust - 5, 10, 10);
			}
		}
		isFirstPaint = false;
	}	
	
	public int getLeftadjust() {
		return leftadjust;
	}

	public void setLeftadjust(int leftadjust) {
		this.leftadjust = leftadjust;
	}
		
	public int getTopadjust() {
		return topadjust;
	}

	public void setTopadjust(int topadjust) {
		this.topadjust = topadjust;
	}

}
