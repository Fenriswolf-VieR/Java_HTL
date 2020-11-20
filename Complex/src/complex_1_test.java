
public class complex_1_test {
	
	public static void main(String[] args) {
		
		complex one = new complex(1, 1);
		complex two = new complex(2,2);
		complex three = one.add(two);
		one.add(two);
		System.out.println(one+" + "+two+" = "+three);
		
	}

}
