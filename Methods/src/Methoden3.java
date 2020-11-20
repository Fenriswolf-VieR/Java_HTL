
public class Methoden3 {
	public static void main(String[] args) {

		//ConvDec2Bin(13);
		//ConvBin2Dec(111);
		ggt(40,58);
		double[]v = new double[] {4,3};
		//vecLenght(v);
		double[]a=v;
		double[]b=v;
		skalProd(a,b);

	}

	static void ConvDec2Bin(int dec) {

		String ans="";
		int r=0;
		do
		{
			r=dec%2;
			dec=dec/2;
			ans=ans+r;
		}
		while(dec>=1);
		//System.out.println(ans);
		String finalAns="";
		for(int j=ans.length()-1;j>=0;j--)
		{
			finalAns=finalAns+ans.charAt(j);
		}

		System.out.println(finalAns);

	}
	
	static void ConvBin2Dec(int bin) {
		
		String str = String.valueOf(bin);
		int dec;
		dec = Integer.parseInt(str, 2);	
		System.out.println(dec);
		
	}
	
	static void ggt(int a, int b) {
		
		int h;

	    do {
	        h = a % b;
	        a = b;
	        b = h;
	    } while (b != 0);

	    System.out.println(a);
	    
	}
	
	static void vecLenght(double[]v) {
		double x=v[0];
		double y=v[1];
		x=Math.pow(x, 2);
		y=Math.pow(y, 2);
		double l=Math.sqrt(x+y);
		System.out.println(l);
	}
	
	static void skalProd(double[]a,double[]b) {
		double p= (a[0]*b[0])+(a[1]*b[1]);
		System.out.println(p);
	}
	
	static double[] rotate(double[]a,int n) {
		int x= n/2;
		double y;
		n=n-(2*x);
		if(n==1) {
			y=a[0];
			a[0]=a[1];
			a[1]=y;
		}
		
		return a;
		
	}
}
