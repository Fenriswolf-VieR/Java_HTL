
public class equilateral_triangle extends isosceles_triangle {
	public equilateral_triangle(double x) {
		super(x, x);
	}
	public String toString(){
		String str = String.format("%30s: %7.2f %7.2f %7.2f", "Gleichseitiges Dreieck", a, b, c);
		return str;
	}
}
