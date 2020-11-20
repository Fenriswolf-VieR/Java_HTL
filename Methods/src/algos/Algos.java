package algos;

public class Algos {

	public static boolean isPrime(int n) {

		int t;
		double b;

		//Eingabe
		t=3;
		//Berechnung größer/kleiner 0
		if (n > 0) {


			//Berechnung gerade Zahl
			b = n%2;

			if (b == 0) {
				if(n==2) {  
					return true;
				}else {
					return false;}

			}else {

				//Berechnung durch 3

				while (t<99999) {
					b = n%t;

					if (b == 0) {
						if(n==t) {  
							return true;
						}else {
							return false;}

					}else {
						t=t+2;


					}
				}
				if (t==99999) {

					return true; }



			}

		}




		else { 
			System.out.println(n + " ist nicht zulässig");
			return false;


		}
		return false;
	}
	public static boolean isLeapYear(int n) {
		double e;
		double h;
		double v;


		
		//Berechnung der Teilbereiche
		e=n%4;
		h=n%100;
		v=n%400;

		//Berechnung ob Schaltjahr

		if(v==0) {
			return true;

		}else {
			if (h == 0) {
				return false;

			}else {
				if (e == 0) {
					return true;

				}else {
					return false;

				}
			}
		}
	}
}













