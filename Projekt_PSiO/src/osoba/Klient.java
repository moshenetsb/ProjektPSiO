package osoba;

import java.util.ArrayList;
import adres.Adres;
import zakupy.Zakupy;
import promocjaStrategia.*;

public class Klient extends Osoba {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private Promocja promocjaKlienta;// PromocjaPodstawowa (0%) PromocjaStudenta(10%) PromocjaStalegoKlienta (20%)
	private ArrayList<Zakupy> historiaZakupow;
	private ArrayList<Zakupy> koszyk;

	// Konstruktor
	public Klient(String email, String haslo, String login, String nazwisko, String imie, int wiek, Adres adres,
			double saldoKonta, Promocja promocjaKlienta, ArrayList<Zakupy> historiaZakupow, ArrayList<Zakupy> koszyk) {
		super(email, haslo, login, nazwisko, imie, wiek, adres, saldoKonta);
		this.promocjaKlienta = promocjaKlienta;
		this.historiaZakupow = historiaZakupow;
		this.koszyk = koszyk;
	}

	// Getters and Setters
	public Promocja getPromocjaKlienta() {
		return promocjaKlienta;
	}

	public void setPromocjaKlienta(Promocja promocjaKlienta) {
		this.promocjaKlienta = promocjaKlienta;
	}

	public ArrayList<Zakupy> getHistoriaZakupow() {
		return historiaZakupow;
	}

	public void setHistoriaZakupow(ArrayList<Zakupy> historiaZakupow) {
		this.historiaZakupow = historiaZakupow;
	}

	public ArrayList<Zakupy> getKoszyk() {
		return koszyk;
	}

	public void setKoszyk(ArrayList<Zakupy> koszyk) {
		this.koszyk = koszyk;
	}

	// Inne metody
	@Override
	public String toString() {
		return "Klient [" + super.toString() + ", Promocje klienta: " + promocjaKlienta.promocja() + "%, Historia zakupow: "
				+ historiaZakupow + ", Koszyk klienta: " + koszyk + "]";
	}

	public void kup() {
		
	}

}
