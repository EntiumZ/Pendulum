package t.vi.java;

import java.util.Vector;
import java.util.HashMap;

public class MagnetsCollection {
	
	private Vector<Magnet> magList = new Vector<Magnet>();
	private HashMap<Integer, Double[]> mag_positions = new HashMap<Integer, Double[]>();
	private int distance = Toolbox.distance;	
	private int mag_quantity = Toolbox.mag_quant;		
	
	public MagnetsCollection() {
		mag_positions.put(3, Toolbox.pos_3);
		mag_positions.put(4, Toolbox.pos_4);
		mag_positions.put(5, Toolbox.pos_5);
		this.createMaglist();
	}
	
	private void createMaglist() {		
		if(!magList.isEmpty()) {
			magList.clear();
		}
				
		Double[] temp = mag_positions.get(mag_quantity);
		
		for(int i = 0; i < mag_quantity; i++) {			
			int x = (int)(temp[i * 2].doubleValue() * (double)distance) + 400;
			int y = (int)(temp[i * 2 + 1].doubleValue() * (double)distance) + 400;
			magList.add(new Magnet(x, y));
		}		
	}
	
	public void refresh(int dis, int num) {		
		if(dis != distance || num != mag_quantity) {
			distance = dis != distance? dis: distance;
			mag_quantity = num != mag_quantity? num : mag_quantity;
			this.createMaglist();			
		}
	}
	
	public Vector<Magnet> getMagList() {
		return magList;
	}

}
