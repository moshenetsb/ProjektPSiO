package main;

import bibliotekaMetodIPol.*;
import inneGUI.*;
import osoba.*;

public class Main {

	public static void main(String[] args) {
		//Metody.getListaKlientow().add(new Klient("admin@gmail.com", "klient", "klient", "Nazwisko", "Imie", 18, null, 0, null, null, null));

		start();
		
	}

	private static void start() {
		WczytywanieObiektow.wczytajDane();

		// Pierwsze konto w sklepie, jeśli jeszcze nie było
		if (Metody.getListaOsobZarzadzajacych().isEmpty() && Metody.getListaKlientow().isEmpty())
			Metody.getListaOsobZarzadzajacych()
					.add(new Kierownik("admin@gmail.com", "admin", "admin", "Nazwisko", "Imie", 18, null, 0, ""));

		@SuppressWarnings("unused")
		GUImain oknoGlowne = new GUImain();
	}

}
