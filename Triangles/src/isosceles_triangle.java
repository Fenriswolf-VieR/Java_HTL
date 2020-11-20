
public class isosceles_triangle extends Triangle{

	public isosceles_triangle(double x, double y) {
		super(x, y, y);
	}
	
	public String toString(){
		String str = String.format("%30s: %7.2f %7.2f %7.2f", "Gleichschenkliges Dreieck", a, b, c);
		return str;
	}

}
