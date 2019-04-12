package t.vi.java;

import java.awt.Color;

/**
 * 
 * @author EntiumZ
 * Keep all constants and parameters for this project
 *
 */

public class Toolbox {
	
	private Toolbox() {}
	
	public static int frameWidth = 1055;
	public static int frameHeigth = 800;
	public static int mainpanelWidth = 800;
	public static int mainpanelHeight = 800;
	public static int controlpanelWidth = 255;
	public static int controlpanelHeight = 800;
	
	//default parameters Mass, Strength Coefficient-km, Gravity Coefficient-kg, Friction Coefficient-kf
	public static double mass_0 = 1.0;
	public static double km_0 = 1.0;  
	public static double kg_0 = 0.5;
	public static double kf_0 = 0.05;	
	public static double coeff[] = {km_0, kg_0, kf_0};
	
	public static int ball_size = 30;
	public static int mag_size = 20;
	public static int mag_quant = 3;
	public static int distance = 200;	
	public static double dis_allow = 15.0;
	public static double minNorm_initial = 1131.2;
	
	public static Double[] pos_3 = {0.0, -1.0, -1.732/2, 0.5, 1.732/2, 0.5};
	public static Double[] pos_4 = {0.0, -1.0, -1.0, 0.0, 0.0, 1.0, 1.0, 0.0};
	public static Double[] pos_5 = {0.0, -1.0, -0.9511, -0.309, -0.5878, 0.809, 0.5878, 0.809, 0.9511, -0.309};
	
	public static Color[] colorset = {Color.white, Color.red, Color.green, Color.blue, Color.yellow, Color.orange};
}
