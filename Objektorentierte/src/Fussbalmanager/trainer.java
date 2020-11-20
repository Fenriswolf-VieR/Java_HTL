package Fussbalmanager;

public class trainer extends person {
	private int experience;
	
	public trainer(String n, int a, int e) {
		super(n,a);
		experience =e;
	}
	
	public int get_experience() {
		return experience;
	}
	
	public void set_experience(int e) {
		experience=e;
	}

}
