package produkty;

public class Mieszane extends Produkty {

	private static final long serialVersionUID = 1L;

	// Konstruktor
	public Mieszane(String nazwa, float cena, int ilosc, String opis) {
		super(nazwa, cena, ilosc, opis);
	}

	public String toString() {
		return "Kategoria: mieszane, " + super.toString();
	}

}
