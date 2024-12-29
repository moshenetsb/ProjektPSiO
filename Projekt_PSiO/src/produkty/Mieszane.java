package produkty;

public class Mieszane extends Produkty {

	private static final long serialVersionUID = 1L;

	// Konstruktor dla utworzenia produktu na listaProduktow
	public Mieszane(String nazwa, float cena, int ilosc, String opis) {
		super(nazwa, cena, ilosc, opis);
	}

	// Konstruktor dla utworzenia produktu do listy Zakupy
	public Mieszane(int kodProduktu, String nazwa, float cena, int ilosc, String opis) {
		super(kodProduktu, nazwa, cena, ilosc, opis);
	}

	public String toString() {
		return "Kategoria: mieszane, " + super.toString();
	}

}
