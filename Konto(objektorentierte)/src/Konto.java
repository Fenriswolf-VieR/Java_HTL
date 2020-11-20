
public class Konto {
	private String kontobesitzer;
	private int kontonummer;
	private double betrag;
	private boolean kontogesperrt;

	static double gesamtbetrag=0;
	static int anzahl=0;

	public Konto(String kontobesitzer_, int kontonummer_, double betrag_) {
		kontobesitzer=kontobesitzer_;
		kontonummer=kontonummer_;
		betrag=betrag_;
		kontogesperrt=false;
		anzahl++;
		gesamtbetrag+=betrag_;

	}

	public void set_kontobesitzer(String kontobesitzer_) {
		kontobesitzer = kontobesitzer_;
		System.out.println("Neuer Kontobesitzer: "+kontobesitzer);
	}

	public void ueberweisen(double betrag_) {
		betrag+=betrag_;
		gesamtbetrag+=betrag_;
		if(betrag>0) {
			kontogesperrt=false;
			System.out.println("Hinweis!! Ihr Konto wurde durch Überweisung entsperrt.");
		}
	}

	public void abheben(double betrag_) {
		if(kontogesperrt==true) {
			System.err.println("Error!! Ihr Konto ist gesperrt.");
		}else {
			betrag-=betrag_;
			gesamtbetrag-=betrag_;
			if(betrag<0) {
				kontogesperrt=true;
				System.err.println("Warnung!! Ihr Konto wurde durch Überziehung gesperrt.");
			}
		}
	}

	public void kontosperrstatus_ändern/*konto sperren; konto entsperren*/(){
		kontogesperrt=!kontogesperrt;
	}

	public String toString() {
		return "Kontobesitzer: "+kontobesitzer+", Kontonummer: " + kontonummer+", Betrag: " + betrag+", Kontogesperrt: " + kontogesperrt;
	}
}
