package osoba;

import java.util.ArrayList;
import javax.swing.*;
import adres.Adres;
import zakupy.Zakupy;
import promocjaStrategia.*;
import produkty.*;
import bibliotekaMetodIPol.*;

public class Klient extends Osoba {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private Promocja promocjaKlienta;// PromocjaPodstawowa (0%) PromocjaStudenta(10%) PromocjaStalegoKlienta (20%)
	private ArrayList<Zakupy> historiaZakupow;
	private Zakupy koszyk;

	// Konstruktor
	public Klient(String email, String haslo, String login, String nazwisko, String imie, int wiek, Adres adres,
			double saldoKonta, Promocja promocjaKlienta, ArrayList<Zakupy> historiaZakupow, Zakupy koszyk) {
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

	public Zakupy getKoszyk() {
		return koszyk;
	}

	public void setKoszyk(Zakupy koszyk) {
		this.koszyk = koszyk;
	}

	// Inne metody
	@Override
	public String toString() {
		return "Klient [" + super.toString() + ", Promocje klienta: " + promocjaKlienta.promocja()
				+ "%, Historia zakupow: " + historiaZakupow + ", Koszyk klienta: " + koszyk + "]";
	}

	public void kup(JFrame frame1) {

		if (czyWystarczyTowarow(frame1)) {

			koszyk.setCenaDoZaplaty(koszyk.obliczCene() * (1 - this.promocjaKlienta.promocja() / 100.0));

			if (!Metody.czyWystarczyPieniedzy(super.getLogin(), koszyk.getCenaDoZaplaty())) {
				JOptionPane.showMessageDialog(frame1, "Brakuje pięniędzy! Doładuj konto i wróć.", "Proces kupowania",
						JOptionPane.ERROR_MESSAGE);

			} else {
				odswiezIloscProduktow();
				this.historiaZakupow.add(koszyk);
				this.koszyk = new Zakupy();
				JOptionPane.showMessageDialog(frame1, "Dziękujemy za zakupy!", "Proces kupowania",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	private void odswiezIloscProduktow() {

		for (Produkty produkt : koszyk.getListaProduktow()) {
			Produkty temp = Metody.znajdzProduktPoID(produkt.getIdProduktu());
			temp.setLiczbaProduktu(temp.getLiczbaProduktu() - produkt.getLiczbaProduktu());
		}

	}

	private boolean czyWystarczyTowarow(JFrame frame1) {

		// koszyk.setListaProduktow(null); // testowanie assert
		assert (koszyk.getListaProduktow() != null) : "Lista produktów w koszyku jest null!";

		for (Produkty produkt : koszyk.getListaProduktow()) {

			Produkty temp = Metody.znajdzProduktPoID(produkt.getIdProduktu());

			if (temp == null) {

				JOptionPane.showMessageDialog(frame1,
						"Produkt " + produkt.getNazwaProduktu()
								+ " już nie sprzedaje się w sklepie. On został automatycznie usunięty z koszyka.",
						"Proces kupowania", JOptionPane.INFORMATION_MESSAGE);
				koszyk.getListaProduktow().remove(koszyk.getListaProduktow().indexOf(produkt));

			} else if (produkt.getLiczbaProduktu() <= temp.getLiczbaProduktu()) {
				JOptionPane.showMessageDialog(frame1,
						"Brakuje produktu " + produkt.getNazwaProduktu()
								+ ". Usuń ten produkt z koszyka lub zmień ilość i powtórz kupowanie",
						"Proces kupowania", JOptionPane.ERROR_MESSAGE);

				// Pytanie o usunięcie produktu
				int czyUsunacTenProdukt = JOptionPane.showConfirmDialog(frame1,
						"Czy chcesz usunąć " + produkt.getNazwaProduktu() + "?", "Proces kupowania",
						JOptionPane.YES_NO_OPTION);

				if (czyUsunacTenProdukt == JOptionPane.YES_OPTION)
					koszyk.getListaProduktow().remove(koszyk.getListaProduktow().indexOf(produkt));

				return false;
			}
		}

		return true;
	}

}
