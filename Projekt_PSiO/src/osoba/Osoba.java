package osoba;

import adres.Adres;
import logowanie.MenuLogowanie;
import java.io.Serializable;
import javax.swing.*;

public abstract class Osoba implements Serializable {

	private static final long serialVersionUID = 1L;

	// Składowe kłasy
	private String email;
	private String haslo;
	private String login;
	private String nazwisko;
	private String imie;
	private int wiek;
	private Adres adres;
	private double saldoKonta;

	// Konstruktor
	public Osoba(String email, String haslo, String login, String nazwisko, String imie, int wiek, Adres adres,
			double saldoKonta) {
		this.email = email;
		this.haslo = haslo;
		this.login = login;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.wiek = wiek;
		this.adres = adres;
		this.saldoKonta = saldoKonta;
	}

	@Override
	public String toString() {
		return "Email: " + email + ", Haslo: " + haslo + ", Login: " + login + ", Nazwisko: " + nazwisko + ", Imie: "
				+ imie + ", Wiek: " + wiek + ", " + adres + ", Saldo konta: " + saldoKonta;
	}

	// Getters and Setters
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
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

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	// Metody
	public void updateSaldoKonta(double zmiana) {
		this.setSaldoKonta(this.getSaldoKonta() + zmiana);
	}

	// Sprawdzenie poprawności danych
	public static boolean isValidData(JFrame frame1, String email, String haslo, String login, String nazwisko,
			String imie, String wiekStr, boolean czyEdytowanie, Osoba osobaEdytowana) {
		if (email.isEmpty() || haslo.isEmpty() || login.isEmpty() || nazwisko.isEmpty() || imie.isEmpty()
				|| wiekStr.isEmpty()) {
			JOptionPane.showMessageDialog(frame1, "Wszystkie pola muszą być wypełnione!", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!email.contains("@")) {
			JOptionPane.showMessageDialog(frame1, "Brak @ w emailu", "Błąd", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		boolean emailExists = true;
		boolean loginExists = true;

		if (czyEdytowanie) {
			if ((MenuLogowanie.szukajIDEmailKlienta(email) == -1
					&& MenuLogowanie.szukajIDEmailZarzadzajacych(email) == -1)
					|| osobaEdytowana.getEmail().equals(email))
				emailExists = false;

			if ((MenuLogowanie.szukajIDLoginKlienta(login) == -1
					&& MenuLogowanie.szukajIDLoginZarzadzajacych(login) == -1)
					|| osobaEdytowana.getLogin().equals(login))
				loginExists = false;

		} else {
			if (MenuLogowanie.szukajIDEmailKlienta(email) == -1
					&& MenuLogowanie.szukajIDEmailZarzadzajacych(email) == -1)
				emailExists = false;

			if (MenuLogowanie.szukajIDLoginKlienta(login) == -1
					&& MenuLogowanie.szukajIDLoginZarzadzajacych(login) == -1)
				loginExists = false;
		}

		if (emailExists && loginExists) {
			JOptionPane.showMessageDialog(frame1, "Konto z podanym emailem i loginem już istnieje!", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (emailExists) {
			JOptionPane.showMessageDialog(frame1, "Konto z podanym emailem już istnieje!", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (loginExists) {
			JOptionPane.showMessageDialog(frame1, "Konto z podanym loginem już istnieje!", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String[] znakiSpecjalne = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")" };
		int liczbaSpecjalnych = 0;
		int liczbaZnakow = haslo.length();
		int liczbaMalychZnakow = 0;
		int liczbaDuzychZnakow = 0;

		for (String znak : znakiSpecjalne) {
			if (haslo.contains(znak)) {
				liczbaSpecjalnych++;
			}
		}

		for (int i = 0; i < haslo.length(); i++) {
			char znak = haslo.charAt(i);
			if (Character.isLowerCase(znak)) {
				liczbaMalychZnakow++;
			} else if (Character.isUpperCase(znak)) {
				liczbaDuzychZnakow++;
			}
		}

		if (liczbaZnakow < 8 || liczbaMalychZnakow < 3 || liczbaDuzychZnakow < 3 || liczbaSpecjalnych < 1) {
			JOptionPane.showMessageDialog(frame1,
					"Hasło nie spełnia standardów bezpieczeństwa.\nPowinno mieć minimum: 8 znaków (w tym po 3 znaki małe i duże), 1 znak specjalny",
					"Błąd", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!isNumeric(wiekStr)) {
			JOptionPane.showMessageDialog(frame1, "Wiek musi być liczbą!", "Błąd", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
