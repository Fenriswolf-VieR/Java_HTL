class Program {
  public static void main(String[] args) {
    Firma f1 = new Firma();
    f1.addKunde("Müller");
    f1.addKunde("Maier");
    f1.addKunde("Huber");
    f1.rechnungEingeben("Müller", 1);
    f1.rechnungEingeben("Müller", 2);
    f1.rechnungEingeben("Maier", 1);
    f1.rechnungEingeben("Maier", 2);

    f1.addMitarbeiter("Kickor", 1234150799l);
    f1.addMitarbeiter("Sabno", 3452110601l);
    f1.addMitarbeiter("Heiner", 1134090277l);

    System.out.println("\n");
    System.out.println(f1.listMA());
    System.out.println("\n");
    System.out.println(f1.listKD());
  }
}