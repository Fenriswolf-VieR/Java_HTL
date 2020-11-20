package Animals;

public class Test_Animals {
	
	public static void main(String[] args){
	
	Animal x=new Animal("x","y","z");
	Dog l=new Dog("Bello","12","dog",45);
	Cat h=new Cat("muri","12","cat",54);
	
	x.toString("x", "y");
	l.toString("Bello","12",45);
	h.toString("muri", "12");
	}

}
