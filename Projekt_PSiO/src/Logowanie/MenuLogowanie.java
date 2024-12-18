package Logowanie;

import Metody.Metody;
import java.util.ArrayList;

public class MenuLogowanie {
	
	// Pola statyczne_______________________________________________
	public static Logowanie preferowaneLogowanie = null;
	// ________________________________________________________________
	
	public static void menuLogowania() {
		System.out.print("____________Logowanie____________\n" 
				+ "1) Logowanie\n"
				+ "2) Modyfikuj preferowany sposób logowania\n" 
				+ "3) Zakońć program\n" 
				+ "_________________________________\n");

		System.out.print("Wybierz opcje: ");
		int wybor = Metody.scanner.nextInt();
		
		while (wybor < 1 || wybor > 3) {
			System.out.println("Została wybrana nieistniejąca opcja!");
			
			System.out.print("Wybierz opcje: ");
			wybor = Metody.scanner.nextInt();
		}
		
		switch(wybor) {
		case 1:
			if (preferowaneLogowanie == null) {
				System.out.println("Dla początku wybierz sposób logowania!");
				wyborSposobuLogowania(false);
			}
			preferowaneLogowanie.logowanie();
			break;
			
		case 2:
			wyborSposobuLogowania(true);
			break;
			
		case 3:
			System.out.println("Program został zakończony");
			Metody.czyKoniecProgramu = true;
			break;
		}
		
	}
	
	// Zmienna czyMoznaWroczyc pokazuje, czy jest dostępny punkt 3 menu
	private static void wyborSposobuLogowania(boolean czyMoznaWroczyc) {
		
		int iloscOpcji = 2; // pokazuje iłość dostępnych opcji menu
		
		System.out.print("_____Wybierz_preferowany_sposób_logowania_____\n"
				+ "1) Logowanie (login i hasło)\n"
				+ "2) Logowanie (emain i hasło)\n");
		
		if (czyMoznaWroczyc) {
			System.out.print("3) Wróć do menu logowania\n");
			iloscOpcji = 3;
		}
			
		System.out.print("_______________________________________________\n");
		
		System.out.print("Wybierz opcje: ");
		int wybor = Metody.scanner.nextInt();
		
		while (wybor < 1 || wybor > iloscOpcji) {
			System.out.println("Została wybrana nieistniejąca opcja!");
			
			System.out.print("Wybierz opcje: ");
			wybor = Metody.scanner.nextInt();
		}
		
		switch(wybor) {
		case 1:
			preferowaneLogowanie = new Logowanie_LoginHaslo();
			break;
		case 2:
			preferowaneLogowanie = new Logowanie_EmailHaslo();
			break;
		case 3:
			menuLogowania();
			break;
		}
	}
	
	
	// Zwraca -1, jeśli login nie istnieje lub indeks osoby w tablicy, jeśli istnieje
	public static int czyIstniejeLoginPracownika(String login) {
		int czyIstnieje = -1;
		
		for (int i = 0; i < Metody.listaPracownikow.size(); i++) {
			if (login == Metody.listaPracownikow[i].getLogin())
				return i;
		}
		
		return -1;
	}
	
	// Zwraca -1, jeśli login nie istnieje lub indeks osoby w tablicy, jeśli istnieje
	public static int czyIstniejeLoginKlienta(String login) {
		int czyIstnieje = -1;
		
		for (int i = 0; i < Metody.listaKlientow.size(); i++) {
			if (login == Metody.listaKlientow[i].getLogin())
				return i;
		}
		
		return -1;
	}
		
	// Zwraca -1, jeśli email nie istnieje lub indeks osoby w tablicy, jeśli istnieje
		public static int czyIstniejeEmailPracownika(String email) {
			int czyIstnieje = -1;
			
			for (int i = 0; i < Metody.listaPracownikow.size(); i++) {
				if (email == Metody.listaPracownikow[i].getEmail())
					return i;
			}
			
			return -1;
		}
		
		// Zwraca -1, jeśli email nie istnieje lub indeks osoby w tablicy, jeśli istnieje
		public static int czyIstniejeEmailKlienta(String email) {
			int czyIstnieje = -1;
			
			for (int i = 0; i < Metody.listaKlientow.size(); i++) {
				if (email == Metody.listaKlientow[i].getEmail())
					return i;
			}
			
			return -1;
		}
	
}
