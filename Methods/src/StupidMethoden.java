import javax.swing.JOptionPane;

public class StupidMethoden {

	public static void main(String[] args) {
		Pölzer();

	}
	static void Pölzer() {
		String Name =/* JOptionPane.showInputDialog("Bitte Name eingeben");*/"Toni Polster";
			if(Name =="Toni Polster") {
				int steuerindex;
				steuerindex=(int) (Math.random()*5);
			 switch(steuerindex) {
			 case 1: 
				 System.out.println("Wir brauchen die Methoden die wir brauchen.");
				 break;
			 case 2: 
				System.out.println("Des sagt der Eagle!!");
				 break;
			 case 3:
				 System.out.println("Des spüts ned");
				 break;
			 case 4:
				 System.out.println("Des steht im leitfodn");
				 break;
			 case 5:
				 System.out.println("DESF KO DA EAGLE!!");
				 break;
			default://keines der cases trägt die nummer
				System.out.println(/*z.b.*/"Keine gültige Eingabe");
				
			 	}
			}
	}
}
