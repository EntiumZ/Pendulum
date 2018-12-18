package t.vi.java;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 
 * @author Oak
 * keep all constants for this project
 *
 */

public class Toolbox {
	
	private Toolbox() {}
	
	public static int timestep = 1;
	public static int ballMass = 1;
	
	public static int frameWidth = 1000;
	public static int frameHeigth = 800;
	public static int mainpanelWidth = 800;
	public static int mainpanelHeight = 800;
	public static int controlpanelWidth = 200;
	public static int controlpanelHeight = 800;
	
	public static int ball_size = 20;
	
	
	public static Image getImage(String path) {
		URL u = Toolbox.class.getClassLoader().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
	
	

}
