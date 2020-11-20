package Fussbalmanager;

import java.util.Random;

public class freundschaftsspiel implements Fussbalmanager.team.freundschaftsspiel {
	private String nameHeimMannschaft;
	private String nameGastMannschaft;
	private int punkteHeim;
	private int punkteGast;

	@Override
	public String getHeimMannschaft() {
		return nameHeimMannschaft;
	}

	@Override
	public String getGastMannschaft() {
		return nameGastMannschaft;
	}

	@Override
	public int getHeimPunkte() {
		return punkteHeim;
	}

	@Override
	public int getGastPunkte() {
		return punkteGast;
	}
	
	public void starteSpiel(team m1, team m2){
		nameHeimMannschaft = m1.getName();
		nameGastMannschaft = m2.getName();
		punkteHeim = 0;
		punkteGast = 0;

		// jetzt starten wir das Spiel und erzeugen für die 90 Minuten
		// Spiel plus Nachspielzeit die verschiedenen Aktionen
		// (wahrscheinlichkeitsbedingt) für das Freundschaftsspiel
		Random r = new Random();
		boolean spiellaeuft = true;
		int spieldauer = 90 + r.nextInt(5);
		int zeit = 1;
		int naechsteAktion;

		// solange das Spiel laeuft, koennen Torchancen entstehen...
		while (spiellaeuft){
			naechsteAktion = r.nextInt(15)+1;
			// Ist das Spiel schon zu Ende?
			if ((zeit + naechsteAktion>spieldauer)||(zeit>spieldauer)){
				spiellaeuft = false;
				break;
			}
			// Eine neue Aktion findet statt...
			zeit = zeit + naechsteAktion;
			// Einfluss der Motivation auf die Stärke:
			float staerke_1 = (m1.getPower()/2.0f) +
					((m1.getPower()/2.0f) * (m1.getMotivation()/10.0f));
			float staerke_2 = (m2.getPower()/2.0f) +
					((m2.getPower()/2.0f) * (m2.getMotivation()/10.0f));
			// Einfluss des Trainers auf die Stärke:
			int abweichung = r.nextInt(2);
			if (staerke_1 > m1.getTrainer().get_experience()) 
				abweichung = -abweichung;
			staerke_1 = Math.max(1, Math.min(10, staerke_1 + abweichung));
			abweichung = r.nextInt(2);
			if (staerke_2 > m2.getTrainer().get_experience()) 
				abweichung = -abweichung;
			staerke_2 = Math.max(1, Math.min(10, staerke_2 + abweichung));
			int schuetze = r.nextInt(10);
			if ((r.nextInt(Math.round(staerke_1+staerke_2))-staerke_1)<=0) {
				player s = m1.getKader()[schuetze];
				goalkeeper t = m2.getTorwart();
				int torschuss = s.shots_at_goal();
				// hält er den Schuss?
				boolean tor = !t.hold(torschuss);
				System.out.println();
				System.out.println(zeit+".Minute: ");
				System.out.println(" Chance fuer "+m1.getName()+" ...");
				System.out.println(" "+s.getName()+" zieht ab");
				if (tor) {
					punkteHeim++;
					s.addGoal();
					System.out.println(" TOR!!! "+punkteHeim+":"+
							punkteGast+" "+s.getName()+"("+s.getGoals()+")");
				} else {
					System.out.println(" "+m2.getTorwart().getName()
							+" pariert glanzvoll.");
				}
			}
			else

			{
				player s = m2.getKader()[schuetze];
				goalkeeper t = m1.getTorwart();
				int torschuss = s.shots_at_goal();
				boolean tor = !t.hold(torschuss);
				System.out.println();
				System.out.println(zeit+".Minute: ");
				System.out.println(" Chance fuer "+m2.getName()+" ...");
				System.out.println(" "+s.getName()+" zieht ab");
				if (tor) {
					punkteGast++;
					s.addGoal();
					System.out.println(" TOR!!! "+punkteHeim+":"+
							punkteGast+" "+s.getName()+"("+s.getGoals()+")");
				} else {
					System.out.println(" "+m1.getTorwart().getName()
							+" pariert glanzvoll.");
				}
			}
		}
	}
	
	@Override
	public String getErgebnisText() {
		return "Das Freundschaftsspiel endete \n\n"+nameHeimMannschaft+" - "+nameGastMannschaft+" "+punkteHeim+":"+punkteGast+".";

	}

}
