package produkty;

public class Fotografia extends Produkty {

	private static final long serialVersionUID = 1L;

	// Konstruktor
	public Fotografia(String nazwa, float cena, int ilosc, String opis) {
		super(nazwa, cena, ilosc, opis);
	}

	public String toString() {
		return "Kategoria: fotografia, " + super.toString();
	}

}
