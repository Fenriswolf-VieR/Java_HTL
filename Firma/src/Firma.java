import java.util.ArrayList;

public class Firma {
	ArrayList<Mitarbeiter> mitarbeiter;
	ArrayList<Kunde> kunden;

	public Firma() {
		mitarbeiter = new ArrayList<Mitarbeiter>();
		kunden = new ArrayList<Kunde>();

	}

	public void addKunde(String name) {
		kunden.add(new Kunde(name));
	}

	public void addMitarbeiter(String name, long svNR) {
		mitarbeiter.add(new Mitarbeiter(name,svNR));
	}
	
	public void rechnungEingeben(String name, int reNr) {
		for (Kunde c : kunden) {
			if (c.getName()== name) {
				c.addRechnung(reNr);
			}
		}
	}
	
	public String listMA() {
		String list="";
		for(int i =0;i<mitarbeiter.size();i++) {
			list=list+mitarbeiter.get(i).getName()+"  "+mitarbeiter.get(i).getSozialversicherungsnumer()+"\n";
		}
		return list;
	}
	
	public String listKD() {
		String list="";
		for(int i =0;i<kunden.size();i++) {
			list=list+kunden.get(i).getName()+"  "+kunden.get(i).getRechnungsList()+"\n";
		}
		return list;
	}

}
