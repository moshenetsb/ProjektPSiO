package main;

import bibliotekaMetodIPol.*;
import osoba.*;
import strategiaGUI.*;

public class Main {

	public static void main(String[] args) {
		start();
		
	}

	private static void start() {
		WczytywanieObiektow.wczytajDane();

		// Pierwsze konto w sklepie, jeśli jeszcze nie było
		if (Metody.getListaOsobZarzadzajacych().isEmpty() && Metody.getListaKlientow().isEmpty())
			Metody.getListaOsobZarzadzajacych()
					.add(new Kierownik("admin@gmail.com", "admin", "admin", "Nazwisko", "Imie", 18, null, 0, "", 0));

		GUImain oknoGlowne = new GUImain();
	}

}
