package Fussbalmanager;
public class FussballTestKlasse {

	public static void main(String[] args) {
		// Mannschaft 1
		trainer t1 = new trainer("Juergen Klinsmann", 34, 9);
		goalkeeper tw1 = new goalkeeper("J. Lehmann", 36, 8, 1, 9, 7);

		player[] sp1 = new player[10];
		sp1[0] = new player("P. Lahm", 23, 9, 5, 9);
		sp1[1] = new player("C. Metzelder", 25, 8, 2, 7);
		sp1[2] = new player("P. Mertesacker", 22, 9, 2, 8);
		sp1[3] = new player("M. Ballack", 29, 7, 5, 8);
		sp1[4] = new player("T. Borowski", 26, 9, 8, 9);
		sp1[5] = new player("D. Odonkor", 22, 7, 5, 8);
		sp1[6] = new player("B. Schweinsteiger", 22, 2, 3, 2);
		sp1[7] = new player("L. Podolski", 21, 7, 8, 9);
		sp1[8] = new player("M. Klose", 28, 10, 9, 7);
		sp1[9] = new player("O. Neuville", 33, 8, 8, 7);

		// Mannschaft 2
		trainer t2 = new trainer("Carlos Alberto Parreira", 50, 3);
		goalkeeper tw2 = new goalkeeper("Dida", 25, 9, 1, 6, 8);

		player[] sp2 = new player[10];
		sp2[0] = new player("Cafu", 33, 8, 4, 6);
		sp2[1] = new player("R. Carlos", 32, 9, 9, 2);
		sp2[2] = new player("Lucio", 29, 10, 9, 9);
		sp2[3] = new player("Ronaldinho", 25, 10, 9, 5);
		sp2[4] = new player("Zé Roberto", 27, 7, 7, 4);
		sp2[4] = new player("Zé Roberto", 27, 7, 7, 4);
		sp2[5] = new player("Kaká", 22, 10, 8, 10);
		sp2[6] = new player("Juninho", 26, 7, 10, 3);
		sp2[7] = new player("Adriano", 23, 8, 8, 4);
		sp2[8] = new player("Robinho", 19, 9, 8, 9);
		sp2[9] = new player("Ronaldo", 28, 4, 10, 2);

		team m1 = new team("Deutschland WM 2006",t1,tw1,sp1);
		team m2 = new team("Brasilien WM 2006",t2,tw2,sp2);
		freundschaftsspiel f1 = new freundschaftsspiel();
		System.out.println("------------------------------------------");
		System.out.println("Start des Freundschaftspiels zwischen");
		System.out.println();
		System.out.println(m1.getName());
		System.out.println(" Trainer: "+m1.getTrainer().getName());
		System.out.println();
		System.out.println(" und");
		System.out.println();
		System.out.println(m2.getName());
		System.out.println(" Trainer: "+m2.getTrainer().getName());
		System.out.println("------------------------------------------");

		f1.starteSpiel(m1, m2);

		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println(f1.getErgebnisText());
		System.out.println("------------------------------------------");
	}



}


