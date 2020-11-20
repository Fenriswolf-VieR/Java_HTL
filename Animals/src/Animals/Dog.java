package Animals;

public class Dog extends Animal {
	
	private int companionship;
	
	public void toString(String name, String age, int companionship) {
		System.out.println("Name: "+name+"Age:"+age+"companionship: " + companionship);
	}

	public int getCompanionship() {
		return companionship;
	}

	public void setCompanionship(int companionship) {
		this.companionship = companionship;
	}

	public Dog(String name, String age, String race, int companion) {
		super(name, age,race);
		this.companionship=companion;
	}

	public void bark() {
		System.out.println("Bark bark");
	}
	
	public void die(int deathchange) {
		if(deathchange>=1) {
			companionship=-1000000000;
			System.out.println("The cat just died");
		}
	}

}
