
public class Mitarbeiter extends Person {
	
	private long Sozialversicherungsnumer;

	public Mitarbeiter(String Name,long Versicherungsnummer) {
		super(Name);
		Sozialversicherungsnumer=Versicherungsnummer;
		
	}

	public long getSozialversicherungsnumer() {
		return Sozialversicherungsnumer;
	}

	public void setSozialversicherungsnumer(long sozialversicherungsnumer) {
		Sozialversicherungsnumer = sozialversicherungsnumer;
	}

}
