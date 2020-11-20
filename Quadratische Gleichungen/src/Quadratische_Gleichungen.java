
public class Quadratische_Gleichungen {
	
	static double[]quadr_gleichung(double a, double b, double c){
		double[]x= new double[2];
		double W = Math.pow(b, 2)-4*a*c;
		if (W < 0) {
            return null;
        } else {
		 x[0]=(-b+Math.sqrt(W))/(2*a);
		 x[1]=(-b-Math.sqrt(W))/(2*a);
		 return x;
        }
		
	}
	static double quadr_wurzel( double a) {
		double xn = 1;
        double xn1;
        double difference;
        do {
            xn1 = 0.5 * (xn + a / xn);
            difference = Math.abs(xn1 - xn);
            xn = xn1;
        } while (difference > Math.pow(10, -10));
        return xn1;
	}

}
