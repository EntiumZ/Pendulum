package t.vi.java;

import javax.swing.SwingUtilities;

//import javax.swing.SwingUtilities;

public class App {
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame frame = new MainFrame("Pendulum App");
			}
		});	
//		MainFrame frame = new MainFrame("Pendulum App");
//		frame.play();
		
	}

}
	