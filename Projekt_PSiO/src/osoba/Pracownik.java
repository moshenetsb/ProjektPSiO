package osoba;

import adres.Adres;

public class Pracownik extends Osoba {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private String pesel;

	// Konstruktor
	public Pracownik(String email, String haslo, String login, String nazwisko, String imie, int wiek,
			Adres adres, double saldoKonta, String pesel) {
		super(email, haslo, login, nazwisko, imie, wiek, adres, saldoKonta);
		this.pesel = pesel;
	}

	// Inne metody
	@Override
	public String toString() {
		return "Pracownik [" + super.toString() + ", PESEL: " + pesel+ "]";
	}

	// Getters and Setters
	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

}