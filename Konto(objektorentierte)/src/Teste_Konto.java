
public class Teste_Konto {

	public static void main(String[] args) {
		
		System.out.println("Anzahl der Objekte in klasse Konto: "+Konto.anzahl);
		System.out.println("Gesamtbetrag der Kontostände: "+Konto.gesamtbetrag);
		
		//Objekte vom Typ Konto anlegen
				Konto Merwyrm = new Konto("Merwyrm",13032302,500.73);
				Konto Alith = new Konto("Alith",13052302,1);
				Konto Daniel = new Konto("Daniel",13072302,0);
				
				Alith.set_kontobesitzer("Anar");
				Daniel.abheben(100);
				Merwyrm.kontosperrstatus_ändern();
				
				System.out.println(Alith.toString());
				System.out.println(Daniel.toString());
				System.out.println(Merwyrm.toString());
				System.out.println("Anzahl der Objekte in klasse Konto: "+Konto.anzahl);
				System.out.println("Gesamtbetrag der Kontostände: "+Konto.gesamtbetrag);
				
				Daniel.ueberweisen(149.99);
				Merwyrm.kontosperrstatus_ändern();
				
				System.out.println(Daniel.toString());
				System.out.println(Merwyrm.toString());
				System.out.println("Anzahl der Objekte in klasse Konto: "+Konto.anzahl);
				System.out.println("Gesamtbetrag der Kontostände: "+Konto.gesamtbetrag);

	}

}
