package Animals;

public class Animal {
	
	private String name;
	private String age;
	private String race;

	public void sleep() {
		System.out.println("Schlafen...");
	}

	public Animal(String name, String age, String race) {
		this.race = race;
		this.name = name;
		this.age = age;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public void toString(String name, String age) {
		System.out.println("Name: "+name+"Age:"+age);
	}
	
	public void change_Race( int mutate) {
		if(mutate!=0)
			this.race="Mutamt";
	}
}
