
public class Test_Person {
	
	public static void main(String[] args) {
		
		System.out.println("Anzahl der Objekte in klasse Person: "+Person.Anzahl);
		System.out.println("Gesamtgröße der Personen: "+Person.Gesamtgröße);
		System.out.println("Gesamtalter der Personen: "+Person.Gesamtalter);
		
		Person Pascal=new Person();
		Person Celine=new Person();
		Person René=new Person();
		
		Pascal.setAttribute("Pascal","blau","gelb","single");
		Celine.setAttribute("Celine","braun","braun","single");
		René.setAttribute("René","braun","braun","single");
		
		Pascal.setAlter(11);
		Celine.setAlter(15);
		René.setAlter(17);
		
		System.out.println(Celine.toString());
		Celine.geburtstag();
		Celine.getAlter();
		
		System.out.println(René.toString());
		René.geburtstag();
		
		System.out.println(Pascal.toString());
		System.out.println(Celine.toString());
		System.out.println(René.toString());
		System.out.println("Anzahl der Objekte in klasse Person: "+Person.Anzahl);
		System.out.println("Gesamtgröße der Personen: "+Person.Gesamtgröße);
		System.out.println("Gesamtalter der Personen: "+Person.Gesamtalter);
		
		
	}

}
