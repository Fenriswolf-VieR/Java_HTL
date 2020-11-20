
public class Person {

	private int Alter;
	private int Größe;
	private String name;
	private String haarfarbe;
	private String augenfarbe;
	private String lebensstatus;
	

	static int Anzahl=0;
	static int Gesamtalter=0;
	static int Gesamtgröße=0;

	public Person(){
		Anzahl+=1;
	}

	public int getAlter() {
		return Alter;

	}

	public void setAlter(int alter) {
		if(alter>0) {
			if(alter<=2) {
				Größe=50+(alter*2);
			}else if(alter<=5) {
				Größe=(alter*110/5);
			}else if(alter<=15) {
				Größe=((alter-5)*5)+110;
			}else if(alter<=18) {
				Größe=(alter*175/17);
			}else {
				Größe=185;
			}
			Gesamtgröße+=Größe;
			Alter=alter;
			Gesamtalter+=alter;

		}else {
			System.err.println("Alter muss mindestens 0 sein!");
		}
	}
	public void setAttribute(String name_,String augenfarbe_,String haarfarbe_,String lebensstatus_){
		name=name_;
		augenfarbe=augenfarbe_;
		haarfarbe=haarfarbe_;
		lebensstatus=lebensstatus_;
	}
	public void geburtstag() {
		Alter+=1;
		int größe1=Größe;
		int alter=Alter;
		if(alter<=2) {
			Größe=50+(alter*2);
		}else if(alter<=5) {
			Größe=(alter*110/5);
		}else if(alter<=15) {
			Größe=(alter*160/15);
		}else if(alter<=18) {
			Größe=(alter*175/17);
		}else {
			Größe=185;
		}
		Gesamtgröße=Gesamtgröße+Größe-größe1;
	}

	public String toString() {
		return "Name: "+name+", Alter: " + Alter +", Größe: " + Größe;
	}
	
	public void print() {
		System.out.println(toString());
	}
}
