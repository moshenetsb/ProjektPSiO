package Osoba;

import Adres.Adres;

public abstract class Osoba {
	private String imieNazwisko;
	private int wiek;
	private Adres adres;
	private String email;
	private double saldoKonta;
	
	public Osoba(String imieNazwisko,int wiek,Adres adres,String email,double saldoKonta) {
		this.imieNazwisko=imieNazwisko;
		this.wiek=wiek;
		this.adres=adres;
		this.email=email;
		this.saldoKonta=saldoKonta;
	}
	
	
	public String toString() {
		return "Klient: Nazwisko: " + imieNazwisko +
				" adres: "+adres +
				" email: " + email +
				" wiek: " + wiek +
				" saldo konta : " + saldoKonta;
	}
	
	
	public String getImieNazwisko() {
		return imieNazwisko;
	}
	
	
	public void setImieNazwisko(String imieNazwisko) {
		this.imieNazwisko = imieNazwisko;
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

}
