package Logowanie;

public class Logowanie_LoginHaslo implements Logowanie {

	@Override
	public void logowanie() {

		// Odczytywanie loginu
		System.out.print("Podaj login: ");
		String login = MenuLogowanie.scanner.nextLine();
		login = MenuLogowanie.scanner.nextLine();

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
		String haslo = MenuLogowanie.scanner.nextLine();
		haslo = MenuLogowanie.scanner.nextLine();

		// Logowanie klienta i sprawdzenie hasła
		if (idKontaPracownika == -1) {

			if (haslo == kontaKlientow[idKontaKlienta].getHaslo()) {
				aktywnaOsoba = kontaKlientow[idKontaKlienta];
				System.out.println("Załogowałeś się jako " + aktywnaOsoba.getImieNazwisko());
			}

			else {
				System.out.println("Zostało podane błędne hasło!");
				return;
			}

		}

		// Logowanie pracownika i sprawdzenie hasła
		else {
			if (haslo == kontaPracownikow[idKontaPracownika].getHaslo()) {
				aktywnaOsoba = kontaPracownikow[idKontaPracownika];
				System.out.println("Załogowałeś się jako " + aktywnaOsoba.getImieNazwisko());
			}

			else {
				System.out.println("Zostało podane błędne hasło!");
				return;
			}
		}

	}

}
