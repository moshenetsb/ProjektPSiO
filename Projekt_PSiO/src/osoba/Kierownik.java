package osoba;

import adres.Adres;

public class Kierownik extends OsobaZarzadzajaca {

	public Kierownik(String email, String haslo, String login, String nazwiskoImie, int wiek, Adres[] adres,
			double saldoKonta, String pesel, float ocena) {
		super(email, haslo, login, nazwiskoImie, wiek, adres, saldoKonta, pesel, ocena);
	}

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
