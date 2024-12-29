package produkty;

public class Gaming extends Produkty {

	private static final long serialVersionUID = 1L;

	// Konstruktor
	public Gaming(String nazwa, float cena, int ilosc, String opis) {
		super(nazwa, cena, ilosc, opis);
	}

	public String toString() {
		return "Kategoria: gaming, " + super.toString();
	}

}
