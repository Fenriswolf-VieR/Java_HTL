package Fussbalmanager;

public class team {
	private String name;
	private trainer trainer;
	private goalkeeper goalkeeper;
	private player[] kader;

	public team(String n, trainer t, goalkeeper tw, player[] s) {
		name =n;
		trainer =t;
		goalkeeper =tw;
		kader =s;
	}

	public int getPower() {// liefert die durschnittliche Mannschafsstärke
		int summ=0;
		for(int i=0; i<10; i++) {
			summ += kader[i].getPower();
		}
			return summ/10;	
		}
		
		public int getMotivation() {// liefert die durschnittliche Mannschafsmotivation
			int summ =0;
			for(int i=0; i<10; i++) {
				summ+=kader[i].getMotivation();
			}
			return summ/10;
		}

		public interface freundschaftsspiel{
			String getHeimMannschaft();
			String getGastMannschaft();
			int getHeimPunkte();
			int getGastPunkte();
			
			String getErgebnisText();
		}

		public String getName() {
			return null;
		}

		public trainer getTrainer() {
			return trainer;
		}

		public goalkeeper getTorwart() {
			return goalkeeper;
		}

		public player[] getKader() {
			return kader;
		}
		

	}
