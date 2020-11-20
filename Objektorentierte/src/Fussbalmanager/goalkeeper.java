package Fussbalmanager;

import java.util.Random;

public class goalkeeper extends player{
	private int reaktion;
	
	public goalkeeper(String n, int a, int s, int t, int m, int r) {
		super(n, a, s, t, m);
		reaktion = r;	
	}

	//Als Parameter erh�lt der Torwart die Torschussst�rke und nun muss
	//entschieden werden, ob der Torwart h�lt oder nicht
	public boolean hold(int schuss) {
		Random r = new Random();
		//+-1 ist hier die Variable
		int ret = Math.max(1, Math.min(10, reaktion + r.nextInt(3)-1));
		if(ret>=schuss) {
			return true; //gehalten
		}else
			return false; //TOR!!!
	}
}
