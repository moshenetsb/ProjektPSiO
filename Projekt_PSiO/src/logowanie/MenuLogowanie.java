package logowanie;

import metody.Metody;

public class MenuLogowanie {

	// Pola statyczne_______________________________________________
	private static Logowanie preferowaneLogowanie;

	public static Logowanie getPreferowaneLogowanie() {
		return preferowaneLogowanie;
	}

	public static void setPreferowaneLogowanie(Logowanie preferowaneLogowanie) {
		MenuLogowanie.preferowaneLogowanie = preferowaneLogowanie;
	}
	// ________________________________________________________________

	// Zwraca -1, jeśli login nie istnieje lub indeks osoby w tablicy, jeśli
	// istnieje
	public static int czyIstniejeLoginOsobyZarzadzajacej(String login) {

		for (int i = 0; i < Metody.listaOsobZarzadzajacych.size(); i++) {
			if (login.equals(Metody.listaOsobZarzadzajacych.get(i).getLogin()))
				return i;
		}

		return -1;
	}

	// Zwraca -1, jeśli login nie istnieje lub indeks osoby w tablicy, jeśli
	// istnieje
	public static int czyIstniejeLoginKlienta(String login) {

		for (int i = 0; i < Metody.listaKlientow.size(); i++) {
			if (login.equals(Metody.listaKlientow.get(i).getLogin()))
				return i;
		}

		return -1;
	}

	// Zwraca -1, jeśli email nie istnieje lub indeks osoby w tablicy, jeśli
	// istnieje
	public static int czyIstniejeEmailOsobyZarzadzajacej(String email) {

		for (int i = 0; i < Metody.listaOsobZarzadzajacych.size(); i++) {
			if (email.equals(Metody.listaOsobZarzadzajacych.get(i).getEmail()))
				return i;
		}

		return -1;
	}

	// Zwraca -1, jeśli email nie istnieje lub indeks osoby w tablicy, jeśli
	// istnieje
	public static int czyIstniejeEmailKlienta(String email) {

		for (int i = 0; i < Metody.listaKlientow.size(); i++) {
			if (email.equals(Metody.listaKlientow.get(i).getEmail()))
				return i;
		}

		return -1;
	}
	
}
