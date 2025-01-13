package loteria;

import java.io.Serializable;
import java.util.Random;
import javax.swing.*;
import bibliotekaMetodIPol.Metody;

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
			Metody.updateSaldoKonta(-wartosc, login);
			if (liczbaUzytkownika == liczba) {
				// Dodajemy wygrane
				Metody.updateSaldoKonta(sumaDoWygrania, login);
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
}
