package produkty;

public class Fotografia extends Produkty {

	public Fotografia(int idProduktu, String nazwaProduktu, float cenaProduktu, int liczbaProduktu, String opisProduktu) {
		super(idProduktu, nazwaProduktu, cenaProduktu, liczbaProduktu, opisProduktu);
	}

	public String toString() {
		return "Kategoria: fotografia" + super.toString();
	}

}
