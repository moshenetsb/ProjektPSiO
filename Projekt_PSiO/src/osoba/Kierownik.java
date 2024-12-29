package osoba;

import adres.Adres;

public class Kierownik extends OsobaZarzadzajaca {

	private static final long serialVersionUID = 1L;

	//Konstruktor
	public Kierownik(String email, String haslo, String login, String nazwisko, String imie, int wiek, Adres adres,
			double saldoKonta, String pesel, float ocena) {
		super(email, haslo, login, nazwisko, imie, wiek, adres, saldoKonta, pesel, ocena);
	}

	@Override
	public String toString() {
		return "Kierownik [" + super.toString() + "]";
	}

	// Przesłonięcie metod abstrakcyjnych
	@Override
	public void wyswietlProdukty() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wyswietlStanProduktu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wyswietlStanKlienta() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wyswietlKlientow() {
		// TODO Auto-generated method stub

	}

}
