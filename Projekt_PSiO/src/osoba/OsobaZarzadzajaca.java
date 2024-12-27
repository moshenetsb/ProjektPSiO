package osoba;

import adres.Adres;

public abstract class OsobaZarzadzajaca extends Osoba {

	private static final long serialVersionUID = 1L;

	private String pesel;
	private float ocena;

	public OsobaZarzadzajaca(String email, String haslo, String login, String nazwiskoImie, int wiek, Adres[] adres,
			double saldoKonta, String pesel, float ocena) {
		super(email, haslo, login, nazwiskoImie, wiek, adres, saldoKonta);
		this.pesel = pesel;
		this.ocena = ocena;
	}

	public String getPesel() {
		return pesel;
	}

	public float getOcena() {
		return ocena;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public void setOcena(float ocena) {
		this.ocena = ocena;
	}

	abstract public void wyswietlProdukty();

	abstract public void wyswietlStanProduktu();

	abstract public void wyswietlStanKlienta();

	abstract public void wyswietlKlientow();

}