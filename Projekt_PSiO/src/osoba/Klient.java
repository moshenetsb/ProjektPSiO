package osoba;

import java.util.ArrayList;
import adres.Adres;
import zakupy.Zakupy;

public class Klient extends Osoba {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private String promocjeKlienta;// ranga Nowy, Średniozaawansowany, Stały
	private ArrayList<Zakupy> historiaZakupow;

	// Konstruktor
	public Klient(String email, String haslo, String login, String nazwisko, String imie, int wiek, Adres adres,
			double saldoKonta, String promocjeKlienta, ArrayList<Zakupy> historiaZakupow) {
		super(email, haslo, login, nazwisko, imie, wiek, adres, saldoKonta);
		this.promocjeKlienta = promocjeKlienta;
		this.historiaZakupow = historiaZakupow;
	}

	// Getters and Setters
	public String getPromocjeKlienta() {
		return promocjeKlienta;
	}

	public void setPromocjeKlienta(String promocjeKlienta) {
		this.promocjeKlienta = promocjeKlienta;
	}

	public ArrayList<Zakupy> getHistoriaZakupow() {
		return historiaZakupow;
	}

	public void setHistoriaZakupow(ArrayList<Zakupy> historiaZakupow) {
		this.historiaZakupow = historiaZakupow;
	}

	// Inne metody
	@Override
	public String toString() {
		return "Klient [" + super.toString() +", Promocje klienta: " + promocjeKlienta + ", Historia zakupow: " + historiaZakupow + "]";
	}

	public void kup(int id) {

	}

	

}
