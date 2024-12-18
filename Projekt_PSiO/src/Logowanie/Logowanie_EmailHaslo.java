package Logowanie;

public class Logowanie_EmailHaslo implements Logowanie {

	@Override
	public void logowanie() {

		// Odczytywanie email
		System.out.print("Podaj email: ");
		String email = MenuLogowanie.scanner.nextLine();
		email = MenuLogowanie.scanner.nextLine();

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
