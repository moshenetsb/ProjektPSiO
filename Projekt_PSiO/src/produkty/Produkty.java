package produkty;

import java.io.Serializable;

public abstract class Produkty implements Serializable {

	private static final long serialVersionUID = 1L;

	// Składowe klasy
	public static int id = 1;
	private int kodProduktu;
	private String nazwa;
	private float cena;
	private int ilosc;
	private String opis;

	// Konstruktor
	public Produkty(String nazwa, float cena, int ilosc, String opis) {
		this.kodProduktu = id;
		id++;
		this.nazwa = nazwa;
		this.cena = cena;
		this.ilosc = ilosc;
		this.opis = opis;
	}

	// Getters and Setters
	public int getIdProduktu() {
		return kodProduktu;
	}

	public void setIdProduktu(int idProduktu) {
		this.kodProduktu = idProduktu;
	}

	public String getNazwaProduktu() {
		return nazwa;
	}

	public void setNazwaProduktu(String nazwaProduktu) {
		this.nazwa = nazwaProduktu;
	}

	public float getCenaProduktu() {
		return cena;
	}

	public void setCenaProduktu(float cenaProduktu) {
		this.cena = cenaProduktu;
	}

	public int getLiczbaProduktu() {
		return ilosc;
	}

	public void setLiczbaProduktu(int liczbaProduktu) {
		this.ilosc = liczbaProduktu;
	}

	public String getOpisProduktu() {
		return opis;
	}

	public void setOpisProduktu(String opisProduktu) {
		this.opis = opisProduktu;
	}

	@Override
	public String toString() {
		return "Kod: " + kodProduktu + ", Nazwa: " + nazwa + ", Cena: " + cena + ", Ilosc: " + ilosc + ", Opis: "
				+ opis;
	}

}
