
public class Person {

	private int Alter;
	private int Gr��e;
	private String name;
	private String haarfarbe;
	private String augenfarbe;
	private String lebensstatus;
	

	static int Anzahl=0;
	static int Gesamtalter=0;
	static int Gesamtgr��e=0;

	public Person(){
		Anzahl+=1;
	}

	public int getAlter() {
		return Alter;

	}

	public void setAlter(int alter) {
		if(alter>0) {
			if(alter<=2) {
				Gr��e=50+(alter*2);
			}else if(alter<=5) {
				Gr��e=(alter*110/5);
			}else if(alter<=15) {
				Gr��e=((alter-5)*5)+110;
			}else if(alter<=18) {
				Gr��e=(alter*175/17);
			}else {
				Gr��e=185;
			}
			Gesamtgr��e+=Gr��e;
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
		int gr��e1=Gr��e;
		int alter=Alter;
		if(alter<=2) {
			Gr��e=50+(alter*2);
		}else if(alter<=5) {
			Gr��e=(alter*110/5);
		}else if(alter<=15) {
			Gr��e=(alter*160/15);
		}else if(alter<=18) {
			Gr��e=(alter*175/17);
		}else {
			Gr��e=185;
		}
		Gesamtgr��e=Gesamtgr��e+Gr��e-gr��e1;
	}

	public String toString() {
		return "Name: "+name+", Alter: " + Alter +", Gr��e: " + Gr��e;
	}
	
	public void print() {
		System.out.println(toString());
	}
}
