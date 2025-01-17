package loteria;

import java.io.Serializable;
import java.util.Random;
import javax.swing.*;
import bibliotekaMetodIPol.Metody;
import logowanie.MenuLogowanie;
import osoba.Klient;

public class Loteria implements Serializable {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private int minLiczba;
	private int maxLiczba;
	private double wartosc;
	private double sumaDoWygrania;

	// Konstruktor
	public Loteria(int minLiczba, int maxLiczba, double wartosc, double sumaDoWygrania) {
		this.minLiczba = minLiczba;
		this.maxLiczba = maxLiczba;
		this.wartosc = wartosc;
		this.sumaDoWygrania = sumaDoWygrania;
	}

	// Metody
	public void grajLoteria(JFrame frame1, int liczbaUzytkownika, String login) {
		Random rand = new Random();
		int liczba = rand.nextInt(maxLiczba - minLiczba + 1) + minLiczba;

		if (liczbaUzytkownika >= minLiczba && liczbaUzytkownika <= maxLiczba) {
			// Opłata za loterie
			Klient klient = Metody.getListaKlientow().get(MenuLogowanie.szukajIDLoginKlienta(login));
			klient.updateSaldoKonta(-wartosc);
			if (liczbaUzytkownika == liczba) {
				// Dodajemy wygrane
				klient.updateSaldoKonta(sumaDoWygrania);
				JOptionPane.showMessageDialog(frame1, "Gratulujemy! Wygrałeś " + sumaDoWygrania + " PLN.", "Loteria",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(frame1,
						"Niestety źłe zgadłeś. Spróbój jeszcze raz.\n" + "Wyłosowaliśmy liczbę " + liczba
								+ ", a ty podałeś " + liczbaUzytkownika + ".",
						"Loteria", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frame1, "Podałeś liczbę nie należącą do zakresu liczb loterii!", "Loteria",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// Getters and setters
	public int getMaxLiczba() {
		return maxLiczba;
	}

	public void setMaxLiczba(int maxLiczba) {
		this.maxLiczba = maxLiczba;
	}

	public int getMinLiczba() {
		return minLiczba;
	}

	public void setMinLiczba(int minLiczba) {
		this.minLiczba = minLiczba;
	}

	public double getWartosc() {
		return wartosc;
	}

	public void setWartosc(double wartosc) {
		this.wartosc = wartosc;
	}

	public double getSumaDoWygrania() {
		return sumaDoWygrania;
	}

	public void setSumaDoWygrania(double sumaDoWygrania) {
		this.sumaDoWygrania = sumaDoWygrania;
	}

	// Metody
	public static boolean isValidData(JFrame frame1, String minLiczba, String maxLiczba, String wartosc,
			String sumaDoWygrania) {
		if (minLiczba.isEmpty() || maxLiczba.isEmpty() || wartosc.isEmpty() || sumaDoWygrania.isEmpty()) {
			JOptionPane.showMessageDialog(frame1, "Wszystkie pola muszą być wypełnione!", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!(isInt(minLiczba) || isInt(maxLiczba) || isDouble(wartosc) || isDouble(sumaDoWygrania))) {
			JOptionPane.showMessageDialog(frame1, "Wszystkie pola muszą być liczbami", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
