package osoba;

import adres.Adres;
import java.io.Serializable;

public abstract class Osoba implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String haslo;
	private String login;
	private String NazwiskoImie;
	private int wiek;
	private Adres[] adres;

	private double saldoKonta;

	public Osoba(String email, String haslo, String login, String nazwiskoImie, int wiek, Adres[] adres,
			double saldoKonta) {
		this.email = email;
		this.haslo = haslo;
		this.login = login;
		NazwiskoImie = nazwiskoImie;
		this.wiek = wiek;
		this.adres = adres;
		this.saldoKonta = saldoKonta;
	}

	public String toString() {
		return "Klient: Nazwisko: " + NazwiskoImie + " adres: " + adres + " email: " + email + " wiek: " + wiek
				+ " saldo konta : " + saldoKonta;
	}

	public String getImieNazwisko() {
		return NazwiskoImie;
	}

	public void setNaziwskoImie(String NazwiskoImie) {
		this.NazwiskoImie = NazwiskoImie;
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

	public Adres[] getAdres() {
		return adres;
	}

	public void setAdres(Adres[] adres) {
		this.adres = adres;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
