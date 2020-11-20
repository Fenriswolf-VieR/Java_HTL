
public class Methoden2{

	public static void main(String[] args) {
		double d;
		double k;
		
		double[]arrayX = new double[] {1.000,1.500,3.000,5.000,6.000,8.000}; //int array mit vorgegebenen Werten ist angelegt
		double[]arrayY = new double[] {2.600,2.500,3.350,4.600,5.200,5.850}; //int array mit vorgegebenen Werten ist angelegt
		
		d=((b1(arrayX,arrayY)*a21(arrayX))-(b2(arrayY)*a11(arrayX)))/((a12(arrayX)*a21(arrayX))-(a11(arrayX)*a22(arrayX)));
		k=((b1(arrayX,arrayY)*a22(arrayX))-(b2(arrayY)*a12(arrayX)))/((a11(arrayX)*a22(arrayX))-(a21(arrayX)*a12(arrayX)));
		
		System.out.println("y = "+k+"*x +"+d);
		
	}static double a11(double[]arrayX){
		double a11=0;
		for(int i=0; i<arrayX.length;i++) {
			a11=a11+(arrayX[i]*arrayX[i]);}
		return a11;
		
	}static double a12(double[]arrayX){
		double a12=0;
		for(int i=0; i<arrayX.length;i++) {
			a12=a12+arrayX[i];}
		return a12;
		
	}static double b1(double[]arrayX,double[]arrayY){
		double b1=0;
		for(int i=0; i<arrayX.length;i++) {
			b1=b1+(arrayX[i]*arrayY[i]);}
		return b1;
		
	}static double a21(double[]arrayX){
		double a21=0;
		for(int i=0; i<arrayX.length;i++) {
			a21=a21+arrayX[i];}
		return a21;
		
	}static double a22(double[]arrayX){
		double a22=arrayX.length;
		return a22;
		
	}static double b2(double[]arrayY){
		double b2=0;
		for(int i=0; i<arrayY.length;i++) {
			b2=b2+arrayY[i];}
		return b2;
		
	}

}
