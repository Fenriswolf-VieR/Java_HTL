import java.util.ArrayList;

public class Kunde extends Person {
	ArrayList<Integer> Rechnungsnummern;

	public Kunde(String Name) {
		super(Name);
		Rechnungsnummern = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getRechnungsnummern() {
		return Rechnungsnummern;
	}

	public void setRechnungsnummern(ArrayList<Integer> rechnungsnummern) {
		Rechnungsnummern = rechnungsnummern;
	}
	
	public String getRechnungsList() {
		ArrayList<Integer> Rechnungen = getRechnungsnummern();
		String RechnungsList="";
		for(int i=0;i<Rechnungen.size();i++) {
			RechnungsList = RechnungsList+Rechnungen.get(i)+"\n";
		}
		return RechnungsList;
	}
	
	public void addRechnung(int reNR) {
		Rechnungsnummern.add(reNR);
	}

}
