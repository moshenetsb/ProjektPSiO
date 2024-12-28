package osoba;

import adres.Adres;

public class Klient extends Osoba {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private String promocjeKlienta;
	private String[] historiaZakupow;
	private String ranga;
	// ranga Nowy, Średniozaawansowany, Stały

	// Konstruktor
	public Klient(String email, String haslo, String login, String nazwisko, String imie, int wiek, Adres adres,
			double saldoKonta, String promocjeKlienta, String[] historiaZakupow, String ranga) {
		super(email, haslo, login, nazwisko, imie, wiek, adres, saldoKonta);
		this.promocjeKlienta = promocjeKlienta;
		this.historiaZakupow = historiaZakupow;
		this.ranga = ranga;
	}

	// Getters and Setters
	public String getPromocjeKlienta() {
		return promocjeKlienta;
	}

	public void setPromocjeKlienta(String promocjeKlienta) {
		this.promocjeKlienta = promocjeKlienta;
	}

	public String[] getHistoriaZakupow() {
		return historiaZakupow;
	}

	public void setHistoriaZakupow(String[] historiaZakupow) {
		this.historiaZakupow = historiaZakupow;
	}

	public String ranga() {
		return ranga;
	}

	public void setRanga(String ranga) {
		this.ranga = ranga;
	}

	public String toString() {
		return "Klient: " + super.toString() + ", Promocje klienta: " + getPromocjeKlienta();
	}

	public void kup(int id) {

	}

}
