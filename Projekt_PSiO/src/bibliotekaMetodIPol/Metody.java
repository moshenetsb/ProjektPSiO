package bibliotekaMetodIPol;

import osoba.*;
import produkty.*;
import strategiaGUI.*;
import java.util.ArrayList;
import logowanie.MenuLogowanie;
import loteria.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Metody {

	// Pola_statyczne______________________________________________________________________________
	private static GUIstrategia wybraneGUI;
	private static String loginAktywnejOsoby;
	
	private static Loteria loteria;

	private static ArrayList<Pracownik> listaOsobZarzadzajacych = new ArrayList<>();
	private static ArrayList<Klient> listaKlientow = new ArrayList<>();
	private static ArrayList<Produkty> listaProduktow = new ArrayList<>();

	// Gettery_i_Settery_pól_statycznych____________________________________________________

	public static GUIstrategia getWybraneGUI() {
		return wybraneGUI;
	}

	public static void setWybraneGUI(GUIstrategia wybraneGUI) {
		Metody.wybraneGUI = wybraneGUI;
	}

	public static String getLoginAktywnejOsoby() {
		return loginAktywnejOsoby;
	}

	public static void setLoginAktywnejOsoby(String loginAktywnejOsoby) {
		Metody.loginAktywnejOsoby = loginAktywnejOsoby;
	}

	public static Loteria getLoteria() {
		return loteria;
	}

	public static void setLoteria(Loteria loteria) {
		Metody.loteria = loteria;
	}

	public static ArrayList<Pracownik> getListaOsobZarzadzajacych() {
		return listaOsobZarzadzajacych;
	}

	public static void setListaOsobZarzadzajacych(ArrayList<Pracownik> listaOsobZarzadzajacych) {
		Metody.listaOsobZarzadzajacych = listaOsobZarzadzajacych;
	}

	public static ArrayList<Klient> getListaKlientow() {
		return listaKlientow;
	}

	public static void setListaKlientow(ArrayList<Klient> listaKlientow) {
		Metody.listaKlientow = listaKlientow;
	}

	public static ArrayList<Produkty> getListaProduktow() {
		return listaProduktow;
	}

	public static void setListaProduktow(ArrayList<Produkty> listaProduktow) {
		Metody.listaProduktow = listaProduktow;
	}

	// Inne metody
	public static Produkty znajdzProduktPoID(int idproduktu) {

		// listaProduktow = null; // testowanie assert
		assert (listaProduktow != null) : "Lista produktów jest nullem!!";

		for (Produkty produkt : listaProduktow) {
			if (produkt.getIdProduktu() == idproduktu)
				return produkt;
		}

		return null;
	}

	public static void updateSaldoKonta(double zmiana, String login) {
		Klient klient = Metody.getListaKlientow().get(MenuLogowanie.szukajIDLoginKlienta(login));
		klient.setSaldoKonta(klient.getSaldoKonta() + zmiana);
	}
	
	public static boolean czyWystarczyPieniedzy(String login, double ileTrzebaMiec) {
		Klient klient = Metody.getListaKlientow().get(MenuLogowanie.szukajIDLoginKlienta(login));
		if (klient.getSaldoKonta() >= ileTrzebaMiec)
			return true;
		
		return false;
	}

	public static boolean isValidData(JFrame frame1, String email, String haslo, String login, String nazwisko, String imie, String wiekStr) {
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

		if (MenuLogowanie.szukajIDEmailKlienta(email) == -1 && MenuLogowanie.szukajIDEmailZarzadzajacych(email) == -1)
			emailExists = false;

		if (MenuLogowanie.szukajIDLoginKlienta(login) == -1 && MenuLogowanie.szukajIDLoginZarzadzajacych(login) == -1)
			loginExists = false;

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

		if (liczbaZnakow < 8 || liczbaMalychZnakow < 3 || liczbaDuzychZnakow < 3 || liczbaSpecjalnych < 3) {
			JOptionPane.showMessageDialog(frame1,
					"Hasło nie spełnia standardów bezpieczeństwa.\nPowinno mieć minimum: 8 znaków (w tym po 3 znaki małe i duże), 3 różne znaki specjalne",
					"Błąd", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!isNumeric(wiekStr)) {
			JOptionPane.showMessageDialog(frame1, "Wiek musi być liczbą!", "Błąd", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
