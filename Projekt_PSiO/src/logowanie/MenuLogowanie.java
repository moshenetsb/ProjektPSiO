package logowanie;

import bibliotekaMetodIPol.Metody;

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
	public static int szukajIDLoginZarzadzajacych(String login) {

		for (int i = 0; i < Metody.getListaOsobZarzadzajacych().size(); i++) {
			if (login.equals(Metody.getListaOsobZarzadzajacych().get(i).getLogin()))
				return i;
		}

		return -1;
	}

	// Zwraca -1, jeśli login nie istnieje lub indeks osoby w tablicy, jeśli
	// istnieje
	public static int szukajIDLoginKlienta(String login) {

		for (int i = 0; i < Metody.getListaKlientow().size(); i++) {
			if (login.equals(Metody.getListaKlientow().get(i).getLogin()))
				return i;
		}

		return -1;
	}

	// Zwraca -1, jeśli email nie istnieje lub indeks osoby w tablicy, jeśli
	// istnieje
	public static int szukajIDEmailZarzadzajacych(String email) {

		for (int i = 0; i < Metody.getListaOsobZarzadzajacych().size(); i++) {
			if (email.equals(Metody.getListaOsobZarzadzajacych().get(i).getEmail()))
				return i;
		}

		return -1;
	}

	// Zwraca -1, jeśli email nie istnieje lub indeks osoby w tablicy, jeśli
	// istnieje
	public static int szukajIDEmailKlienta(String email) {

		for (int i = 0; i < Metody.getListaKlientow().size(); i++) {
			if (email.equals(Metody.getListaKlientow().get(i).getEmail()))
				return i;
		}

		return -1;
	}
	
}
