
public class Test_Person {
	
	public static void main(String[] args) {
		
		System.out.println("Anzahl der Objekte in klasse Person: "+Person.Anzahl);
		System.out.println("Gesamtgr��e der Personen: "+Person.Gesamtgr��e);
		System.out.println("Gesamtalter der Personen: "+Person.Gesamtalter);
		
		Person Pascal=new Person();
		Person Celine=new Person();
		Person Ren�=new Person();
		
		Pascal.setAttribute("Pascal","blau","gelb","single");
		Celine.setAttribute("Celine","braun","braun","single");
		Ren�.setAttribute("Ren�","braun","braun","single");
		
		Pascal.setAlter(11);
		Celine.setAlter(15);
		Ren�.setAlter(17);
		
		System.out.println(Celine.toString());
		Celine.geburtstag();
		Celine.getAlter();
		
		System.out.println(Ren�.toString());
		Ren�.geburtstag();
		
		System.out.println(Pascal.toString());
		System.out.println(Celine.toString());
		System.out.println(Ren�.toString());
		System.out.println("Anzahl der Objekte in klasse Person: "+Person.Anzahl);
		System.out.println("Gesamtgr��e der Personen: "+Person.Gesamtgr��e);
		System.out.println("Gesamtalter der Personen: "+Person.Gesamtalter);
		
		
	}

}
