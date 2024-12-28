package produkty;

public class Mieszane extends Produkty {

	public Mieszane(int idProduktu, String nazwaProduktu, float cenaProduktu, int liczbaProduktu, String opisProduktu) {
		super(idProduktu, nazwaProduktu, cenaProduktu, liczbaProduktu, opisProduktu);
	}

	public String toString() {
		return "Kategoria: mieszane" + super.toString();
	}

}
