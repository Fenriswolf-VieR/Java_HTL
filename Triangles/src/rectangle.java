
public class rectangle extends Form{

	protected double a,b;
	
	public rectangle(int seite_a, int seite_b){
		a= seite_a;
		b= seite_b;
	}

	public String toString() {
		String str = String.format("%30s: %7.2f %7.2f ","Rechteck", a, b);
		return str;
	}

}
