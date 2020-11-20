public class Methoden1 {

	public static void main(String[] args) {
		//Smallest();	
		System.out.println();
		Second_to_Biggest();
		System.out.println();
		//select_sort();

	}
	static void Smallest() {
		int k=102;
		int s=0;
		int[]array2 = new int[10];
		for(int i=0; i<array2.length; i++) {
			array2[i]=(int)(Math.random()*101);}
		
		for(int i=0; i<array2.length; i++) {
			 if(array2[i]<k) {
				 k=array2[i];
			 	 s=i;}
			 }
		 	System.out.println("Der kleinste Wert beträgt"+" "+k+" "+"und sein index ist"+" "+s);}
	
	static void Second_to_Biggest() {
		int k=0;
		int k2=0;
		int s=0;
		int s2=0;
		int[]array2 = new int[10];
		for(int i=0; i<array2.length; i++) {
			array2[i]=(int)(Math.random()*101);}
		for(int i=0; i<array2.length; i++)
			System.out.println(array2[i]);
		
		for(int i=0; i<array2.length; i++) {
			 if(array2[i]>k) {
				 k2=k;
				 k=array2[i];
			 	 s2=s;
				 s=i;}
			 else 
				 if(array2[i]>k2) {
			 		k2=array2[i];
			 		s2=i;}
			 	}
		 	System.out.println("Der zweitgrößte Wert beträgt"+" "+k2+" "+"und sein index ist"+" "+s2);
	}
	static void select_sort() {
		int k=102;
		int n=0;
		int c=0;
		int[]array2 = new int[10];
		for(int i=0; i<array2.length; i++) {
			array2[i]=(int)(Math.random()*101);}
		
		n=array2.length;
		 for(int i=0; i<n-1; i++) {
			 k=i;
			 for(int j=i+1; j<n; j++) {
				if(array2[j]<array2[k]) 
					k=j;
				}
			 c=array2[k];
			 array2[k]=array2[i];
			 array2[i]=c;
			 
		 }
		 for(int i=0; i<array2.length; i++) {
			 System.out.println(array2[i]);
		 }
	}
}
