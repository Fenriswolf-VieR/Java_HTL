package Animals;

 

public class Cat extends Animal{
	
	private int sleepiness;
	
	public void toString(String name, String age, int sleepiness) {
		System.out.println("Name: "+name+"Age:"+age+"Sleepiness: " + sleepiness);
	}

	public int getSleepiness() {
		return sleepiness;
	}

	public void setSleepiness(int sleepiness) {
		this.sleepiness = sleepiness;
	}

	public Cat(String name, String age, String race, int sleepiness) {
		super(name, age,race);
		this.sleepiness=sleepiness;
	}

	public void purr() {
		System.out.println("Purr purr");
	}
	public void die(int deathchange) {
		if(deathchange>=1) {
			sleepiness=1000000000;
			System.out.println("The cat just died");
		}
	}

}
