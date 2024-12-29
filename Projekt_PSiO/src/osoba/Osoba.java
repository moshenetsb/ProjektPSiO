package osoba;

import adres.Adres;
import java.io.Serializable;

public abstract class Osoba implements Serializable {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private String email;
	private String haslo;
	private String login;
	private String nazwisko;
	private String imie;
	private int wiek;
	private Adres adres;
	private double saldoKonta;

	// Konstruktor
	public Osoba(String email, String haslo, String login, String nazwisko, String imie, int wiek, Adres adres,
			double saldoKonta) {
		this.email = email;
		this.haslo = haslo;
		this.login = login;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.wiek = wiek;
		this.adres = adres;
		this.saldoKonta = saldoKonta;
	}

	@Override
	public String toString() {
		return "Email: " + email + ", Haslo: " + haslo + ", Login: " + login + ", Nazwisko: " + nazwisko + ", Imie: "
				+ imie + ", Wiek: " + wiek + ", " + adres + ", Saldo konta: " + saldoKonta;
	}


	// Getters and Setters
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public int getWiek() {
		return wiek;
	}

	public void setWiek(int wiek) {
		this.wiek = wiek;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSaldoKonta() {
		return saldoKonta;
	}

	public void setSaldoKonta(double saldoKonta) {
		this.saldoKonta = saldoKonta;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
