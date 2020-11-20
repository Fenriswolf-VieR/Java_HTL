package test_algos;
import javax.swing.JOptionPane;


public class Test_Algos {

	public static void main(String[] args) {

		//boolean istprim = false;


		//for (int i= 2; i < 10000; i++) {
		//istprim=algos.Algos.isPrime(i);
		//System.out.println(i+ " ist prim: " + istprim);}




		boolean isLeapYear;{
			String input;
			int n;
			//Eingabe
			input = JOptionPane.showInputDialog("Bitte Jahreszahl eingeben");
			n = Integer.parseInt(input);
			
			isLeapYear= algos.Algos.isLeapYear(n);
			System.out.println(n+ " is leapyear:" + isLeapYear);

		}
	}
}
