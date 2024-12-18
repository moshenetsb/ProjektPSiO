package Logowanie;

import Metody.Metody;

public class Logowanie_EmailHaslo implements Logowanie {

	@Override
	public void logowanie() {

		// Odczytywanie email
		System.out.print("Podaj email: ");
		String email = Metody.scanner.nextLine();
		email = Metody.scanner.nextLine();

		// Sprawdzenie istnienia użytkowników z podanym email
		int idKontaPracownika = MenuLogowanie.czyIstniejeEmailPracownika(email);
		int idKontaKlienta = MenuLogowanie.czyIstniejeEmailKlienta(email);

		// Jeśli błędny email
		if (idKontaPracownika == -1 && idKontaKlienta == -1) {
			System.out.println("Konta z podanym email nie istnieje!");
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
