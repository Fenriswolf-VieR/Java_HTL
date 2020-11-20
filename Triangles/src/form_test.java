import java.util.ArrayList;

public class form_test {
	
	public static void main(String[] args) {
		ArrayList <Form> formenListe = new ArrayList<Form>();
		formenListe.add(new Triangle(5,3,4));
		formenListe.add(new isosceles_triangle(15,13));
		formenListe.add(new equilateral_triangle(100));
		formenListe.add(new rectangle(50,25));
		
		for (Form e:formenListe)
			System.out.println(e);	
	}

}
