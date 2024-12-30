package osoba;

import adres.Adres;

public abstract class OsobaZarzadzajaca extends Osoba {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private String pesel;

	// Konstruktor
	public OsobaZarzadzajaca(String email, String haslo, String login, String nazwisko, String imie, int wiek,
			Adres adres, double saldoKonta, String pesel) {
		super(email, haslo, login, nazwisko, imie, wiek, adres, saldoKonta);
		this.pesel = pesel;
	}

	// Inne metody
	@Override
	public String toString() {
		return super.toString() + ", PESEL: " + pesel;
	}

	// Getters and Setters
	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	
	// Metody abstrakcyjne
	abstract public void wyswietlProdukty();

	abstract public void wyswietlStanProduktu();

	abstract public void wyswietlStanKlienta();

	abstract public void wyswietlKlientow();

}