package osoba;

import adres.Adres;

public abstract class OsobaZarzadzajaca extends Osoba {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private String pesel;
	private float ocena;

	// Konstruktor
	public OsobaZarzadzajaca(String email, String haslo, String login, String nazwisko, String imie, int wiek,
			Adres adres, double saldoKonta, String pesel, float ocena) {
		super(email, haslo, login, nazwisko, imie, wiek, adres, saldoKonta);
		this.pesel = pesel;
		this.ocena = ocena;
	}

	// Inne metody
	@Override
	public String toString() {
		return super.toString() + ", PESEL: " + pesel + ", Ocena: " + ocena;
	}

	// Getters and Setters
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

	// Metody abstrakcyjne
	abstract public void wyswietlProdukty();

	abstract public void wyswietlStanProduktu();

	abstract public void wyswietlStanKlienta();

	abstract public void wyswietlKlientow();

}