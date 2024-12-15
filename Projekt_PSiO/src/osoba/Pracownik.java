package Osoba;

import Adres.Adres;

public class Pracownik extends Osoba {
	
	double pensja;
	int pesel;
	double czyKierownik;
	double ranking;

	public Pracownik(String imieNazwisko, int wiek, Adres adres, String email, double saldoKonta) {
		super(imieNazwisko, wiek, adres, email, saldoKonta);
		// TODO Auto-generated constructor stub
	}
	
	public double getPensja() {
		return pensja;
	}

	public void setPensja(double pensja) {
		this.pensja = pensja;
	}


	public int getPesel() {
		return pesel;
	}

	public void setPesel(int pesel) {
		this.pesel = pesel;
	}

	public double getCzyKierownik() {
		return czyKierownik;
	}

	public void setCzyKierownik(double czyKierownik) {
		this.czyKierownik = czyKierownik;
	}

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "Pracownik [pensja=" + pensja + ", pesel=" + pesel + ", czyKierownik=" + czyKierownik + ", ranking="
				+ ranking + "]";
	}

}
