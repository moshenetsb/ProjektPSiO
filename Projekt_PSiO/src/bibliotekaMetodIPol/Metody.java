package bibliotekaMetodIPol;

import osoba.*;
import produkty.*;
import strategiaGUI.*;
import java.util.ArrayList;;

public class Metody {

	// Pola_statyczne______________________________________________________________________________
	private static GUIstrategia wybraneGUI;;
	private static String loginAktywnejOsoby;

	private static ArrayList<OsobaZarzadzajaca> listaOsobZarzadzajacych = new ArrayList<>();
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

	public static ArrayList<OsobaZarzadzajaca> getListaOsobZarzadzajacych() {
		return listaOsobZarzadzajacych;
	}

	public static void setListaOsobZarzadzajacych(ArrayList<OsobaZarzadzajaca> listaOsobZarzadzajacych) {
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
	public static void wystwietlKlientow() {
		for (Klient klient : listaKlientow) {
			System.out.println(klient);
		}
	}
}
