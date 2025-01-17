package bibliotekaMetodIPol;

import osoba.*;
import produkty.*;
import strategiaGUI.*;
import java.util.ArrayList;

import loteria.*;

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

}
