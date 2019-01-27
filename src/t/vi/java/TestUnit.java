package t.vi.java;

public class TestUnit {

	public TestUnit() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Vect v1 = new Vect(5,5,7,2);
		Vect v2 = new Vect(5,5,6,8);
		Vect v3 = new Vect(5,5,3,3);
		Vect v4 = new Vect(5,5,2,7);
		v1.addVect(v2);
		v3.addVect(v4);
		Vect v5 = new Vect(v1, '+', v3);
		//System.out.println(v5.getpX() + ", " + v5.getpY());
			}

}
