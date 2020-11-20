import java.util.Scanner;

public class Methoden {

	public static void main(String[] args) {
		double flaeche;
		
		int radius;
		Scanner sc= new Scanner(System.in);
		radius = sc.nextInt();
		sc.close();
		
		if(istPositiv(radius)) {
			flaeche = kreisflaeche(radius);
			System.out.println("kreisflaeche für radius="+radius+" beträgt "+flaeche);
		}
		else {
			System.err.println("Fehlerhafte Eingabe!");
		}
		
		double[] temperaturwerte = {2,3,4,5,6,8,9,10,99};
		System.out.print("berechneter Mittelwert der Temperaturwerte:");
		for(double elem : temperaturwerte)//for each
			System.out.print(elem+" ");
		System.out.println("=>"+mittelwert(temperaturwerte));
	//Celsius in Kelvin: double[] celsius2Kelvin(double[])
	}
	static double[]celsius2Kelvin(double[]t_in_C){
	for(int i=0; i<t_in_C.length;i++) {
		t_in_C[i]+=273.15;
	}
	return t_in_C;
	}
	static void teste_celsius2Kelvin() {
		double[] t_in_C = new double[24];
		for (int i=0;i<t_in_C.length;i++) { //init
			t_in_C[i] = Math.random()*30;
		}
		System.out.println("Werte in C:");
		for (int i=0;i<t_in_C.length;i++)
			System.out.println("%.2f");
		}
	
	
	//rückgabe
	static double kreisflaeche(int radius) {
		double f;
		f= radius*radius*Math.PI;
		return f;
	}
	static Boolean istPositiv(int wert) {
		if(wert>0)
			return true;
		else
			return false;
	}
	static double mittelwert(double[] twerte) {
		int m=0;
		for(int i=0; i<twerte.length; i++) {
			m+=twerte[i];
		}m=m/twerte.length;
		return m;
	}
}

