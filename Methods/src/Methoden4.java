
public class Methoden4 {
	public static void main(String[] args) {

		//teste:

		int t = stellen(10008.6276);
		System.out.println(t);//should be 5
		int t1 =  ggt(44,40);
		System.out.println(t1);//should be 4
		boolean t2 = istPrim(111);
		System.out.println(t2);//should be false
		int t3 = kgv(3,20);
		System.out.println(t3);//should be 60*/
		int[]t4 = teiler(10);
		for(int i=0;i<t4.length; i++) 
		{
			System.out.println(t4[i]);
		}//should be 1,2,5,10
		int[]t6 = new int[5];
		int[]t7 = new int[7];
		boolean t5 = gleich_i(t6,t7);
		System.out.println(t5);//should be false
		int[]t8 = new int[8];
		int[]t9 = new int[10];
		boolean t10 = disjunkt(t8,t9);
		System.out.println(t10);//should be true




	}

	static int stellen(double d) {
		String s = Double.toString(d);
		String  s1 = s.substring(s.indexOf("."));
		return s.length()-s1.length();
	}

	static int[]teiler(int a){

		int c=0;

		for(int i=1;i<=a;i++) {
			if(a%i==0) {
				c++;
			}
		}

		int[] t=new int[c];

		c=0;

		for(int i=1;i<=a;i++) {
			if(a%i==0) {
				t[c]=i;
				c++;
			}
		}
		return t;

	}

	static int ggt(int a, int b) {
		int h;
		do {
			h = a % b;
			a = b;
			b = h;
		} while (b != 0);

		return a;

	}

	static int kgv(int a, int b) {
		int p= a*b;
		p=Math.abs(p);
		int ggt=ggt(a,b);
		int kgv=p/ggt;
		return kgv;
	}

	static boolean istPrim(int a){
		int t=3;
		double b;
		//Berechnung größer/kleiner 0
		if (a > 0) {


			//Berechnung gerade Zahl
			b = a%2;
			if (b == 0) {
				if(a==2) {  
					return true;
				}else {
					return false;}
			}else {

				//Berechnung durch 3

				while (t<999) {
					b = a%t;
					if (b == 0) {
						if(a==t) {  
							return true;
						}else {
							return false;}
					}else {
						t=t+2;
					}
				}
				if (t==999) {
					return true; 
				}
			}
		}

		else { 
			System.out.println(a + " ist nicht zulässig");
			return false;
		}
		return false;
	}

	public static boolean gleich_i(int[] a, int[] b) {

		boolean x=true;

		if(a.length==b.length) {

			for(int i=a.length; i==1; i--) {
				if(a[i]==b[i]) {
				}
				else {
					x=false;
					break;
				}
			}
		}
		else {
			x=false;
		}
		return x;
	}
	
	public static boolean disjunkt(int[]a, int[]b) {
		 boolean x=true;
	        if(a.length==b.length) {
	            for(int i=a.length; i==1; i--) {
	                if(a[i]!=b[i]) {
	                }
	                else {
	                    x=false;
	                    break;
	                }
	            }
	        }

	        return x;
	}

}
