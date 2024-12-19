package osoba;

import adres.Adres;
import Metody.*;

public class Kierownik extends OsobaZarzadzajaca {

	public Kierownik(String email, String haslo, String login, String nazwiskoImie, int wiek, Adres[] adres,
			double saldoKonta, String pesel, float ocena) {
		super(email, haslo, login, nazwiskoImie, wiek, adres, saldoKonta, pesel, ocena);
		
	}

	public String toString() {
		return "Kierownik " + super.toString();
	}

	@Override
	public void wyswietlProdukty() {
		Metody.wystwietlProdukty();
		
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
		Metody.wystwietlKlientow();
	}
	
	
	
	

}
