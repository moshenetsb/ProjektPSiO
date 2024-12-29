package produkty;

public class Fotografia extends Produkty {

	private static final long serialVersionUID = 1L;

  // Konstruktor dla utworzenia produktu na listaProduktow
	public Fotografia(String nazwa, float cena, int ilosc, String opis) {
		super(nazwa, cena, ilosc, opis);
	}

	// Konstruktor dla utworzenia produktu do listy Zakupy
	public Fotografia(int kodProduktu, String nazwa, float cena, int ilosc, String opis) {
		super(kodProduktu, nazwa, cena, ilosc, opis);
	}
  
	public String toString() {
		return "Kategoria: fotografia, " + super.toString();
	}

}
