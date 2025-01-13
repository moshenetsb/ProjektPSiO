package osoba;

import adres.Adres;

public class Kierownik extends Pracownik {

	private static final long serialVersionUID = 1L;

	// Konstruktor
	public Kierownik(String email, String haslo, String login, String nazwisko, String imie, int wiek, Adres adres,
			double saldoKonta, String pesel) {
		super(email, haslo, login, nazwisko, imie, wiek, adres, saldoKonta, pesel);
	}

	@Override
	public String toString() {
		return "Rola: kierownik, " + super.toString();
	}

}
