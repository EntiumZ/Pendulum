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
	
	public static int ballMass = 1;
	
	public static int frameWidth = 1055;
	public static int frameHeigth = 800;
	public static int mainpanelWidth = 800;
	public static int mainpanelHeight = 800;
	public static int controlpanelWidth = 255;
	public static int controlpanelHeight = 800;
	
	//default parameters Mass, Strength Coefficient-km, Gravity Coefficient-kg, Friction Coefficient-kf
	public static double mass_0 = 1.0;
	public static double km_0 = 100;  
	public static double kg_0 = 0.1;
	public static double kf_0 = 0.5;	
	public static double coeff[] = {mass_0, km_0, kg_0, kf_0};
	
	public static int ball_size = 50;
	public static int mag_size = 20;
	public static int distance = 200;
	
	public static double dist_allow = 5.0;
	
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
