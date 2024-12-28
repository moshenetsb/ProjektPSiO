package produkty;

public class Gaming extends Produkty {

	public Gaming(int idProduktu, String nazwaProduktu, float cenaProduktu, int liczbaProduktu, String opisProduktu) {
		super(idProduktu, nazwaProduktu, cenaProduktu, liczbaProduktu, opisProduktu);
	}

	public String toString() {
		return "Kategoria: gaming" + super.toString();
	}

}
