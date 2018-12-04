package t.vi.java;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	
	private MainPanel mainpanel = null;

	public MainFrame(String s) {
		super(s);
		mainpanel = new MainPanel();		
		this.add(mainpanel);
		this.addMouseListener(mainpanel);
		
		this.setSize(Toolbox.frameWidth, Toolbox.frameHeigth);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		Insets a = this.getInsets();
		mainpanel.setLeftadjust(a.left);
		mainpanel.setTopadjust(a.top);

		//mainpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
			
		
		System.out.println("mainpanel height:" + mainpanel.getHeight()+"\n");
		System.out.println("mainpanel width:" + mainpanel.getWidth()+"\n");
		
		System.out.println("�˵����ĸ߶�Ϊ��"+a.top);
		System.out.println("JFrame��߿�Ŀ�ȣ�"+a.left);
		System.out.println("JFrame�ұ߿�Ŀ�ȣ�"+a.right);
		System.out.println("JFrame�±߿�Ŀ�ȣ�"+a.bottom);
		//this.init();
	}
	
	private void init() {
		this.setSize(Toolbox.frameWidth, Toolbox.frameHeigth);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
