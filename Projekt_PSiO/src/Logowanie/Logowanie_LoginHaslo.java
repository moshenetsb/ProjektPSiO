package Logowanie;

import Metody.Metody;

public class Logowanie_LoginHaslo implements Logowanie {

	@Override
	public void logowanie() {

		// Odczytywanie loginu
		System.out.print("Podaj login: ");
		String login = Metody.scanner.nextLine();
		login = Metody.scanner.nextLine();

		// Sprawdzenie istnienia użytkowników z podanym loginem
		int idKontaPracownika = MenuLogowanie.czyIstniejeLoginPracownika(login);
		int idKontaKlienta = MenuLogowanie.czyIstniejeLoginKlienta(login);

		// Jeśli błędny login
		if (idKontaPracownika == -1 && idKontaKlienta == -1) {
			System.out.println("Podany login nie istnieje!");
			return;
		}

		// Odczytywanie hasła
		System.out.print("Podaj hasło: ");
		String haslo = Metody.scanner.nextLine();
		haslo = Metody.scanner.nextLine();

		// Logowanie klienta i sprawdzenie hasła
		if (idKontaPracownika == -1) {

			if (haslo == Metody.listaKlientow[idKontaKlienta].getHaslo()) {
				Metody.aktywnaOsoba = Metody.listaKlientow[idKontaKlienta];
				System.out.println("Załogowałeś się jako " + Metody.aktywnaOsoba.getImieNazwisko());
			}

			else {
				System.out.println("Zostało podane błędne hasło!");
				return;
			}

		}

		// Logowanie pracownika i sprawdzenie hasła
		else {
			if (haslo == Metody.listaPracownikow[idKontaPracownika].getHaslo()) {
				Metody.aktywnaOsoba = Metody.listaPracownikow[idKontaPracownika];
				System.out.println("Załogowałeś się jako " + Metody.aktywnaOsoba.getImieNazwisko());
			}

			else {
				System.out.println("Zostało podane błędne hasło!");
				return;
			}
		}

	}

}
