package Fussbalmanager;

import java.util.Random;

public class player extends person {
		private int power;		// von 1 (schlecht) bis 10 (super)
		private int torschuss;    // von 1 (schlecht) bis 10 (super)
		private int motivation;   // von 1 (schlecht) bis 10 (super)
		private int goals;
		
public player(String n, int a, int s, int t,int m) {
	super(n,a);
	power = s;
	torschuss = t;
	motivation =m;
	goals = 0;
	}

public void addGoal() {
	goals++;
}

// eine Zahl von 1-10 liefert die Qualität des Torschusses mit einem kleinen Zufallswert +1 oder -1
public int shots_at_goal() {
	Random r = new Random();
	//Entfernungspauschale
	torschuss = Math.max(1, Math.min(10, torschuss-r.nextInt(3)));
	//+-1 ist hier die Variant
	int ret = Math.max(1, Math.min(10, torschuss-r.nextInt(3)-1));
	return ret;
}

public int getPower() {
	return power;
}

public int getMotivation() {
	return motivation;
}

public int getGoals() {
	return goals;
}

}
