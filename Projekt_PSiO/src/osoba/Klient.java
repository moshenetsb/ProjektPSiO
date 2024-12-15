package Osoba;

import java.util.Arrays;

import Adres.Adres;

public class Klient extends Osoba{
	
	String promocjeKlienta;
	String[] historiaZakupow;

	public Klient(String imieNazwisko, int wiek, Adres adres, String email, double saldoKonta,String promocjeKlienta,String[] historiaZakupow) {
		super(imieNazwisko, wiek, adres, email, saldoKonta);
		this.promocjeKlienta=promocjeKlienta;
		this.historiaZakupow=historiaZakupow;
	}

	@Override
	public String toString() {
		return "Klient [promocjeKlienta=" + promocjeKlienta + ", historiaZakupow=" + Arrays.toString(historiaZakupow)
				+ "]";
	}
	

}
