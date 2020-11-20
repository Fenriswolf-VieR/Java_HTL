
public class Triangle extends Form {
	protected double a, b, c;
	
	public Triangle(double x, double y, double z){
		a = x;
		b = y;
		c = z;
	}
	
	public String toString(){
		String str = String.format("%30s: %7.2f %7.2f %7.2f","Dreieck", a, b, c);
		return str;
	}

}
