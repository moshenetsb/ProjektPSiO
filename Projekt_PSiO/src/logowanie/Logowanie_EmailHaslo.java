package logowanie;

import javax.swing.JOptionPane;

import bibliotekaMetodIPol.Metody;
import osoba.Kierownik;

public class Logowanie_EmailHaslo implements Logowanie {

	@Override
	public void logowanie(String loginEmail, String haslo) {

		// Sprawdzenie istnienia użytkowników z podanym email
		int idKontaOsobyZarzadzajacej = MenuLogowanie.szukajIDEmailZarzadzajacych(loginEmail);
		int idKontaKlienta = MenuLogowanie.szukajIDEmailKlienta(loginEmail);

		// Jeśli błędny email
		if (idKontaOsobyZarzadzajacej == -1 && idKontaKlienta == -1) {
			JOptionPane.showMessageDialog(null, "Podany email nie istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Logowanie klienta i sprawdzenie hasła
		if (idKontaOsobyZarzadzajacej == -1) {

			if (haslo.equals(Metody.getListaKlientow().get(idKontaKlienta).getHaslo())) {
				Metody.setAktywnaOsobaDostep("klient");
				Metody.setLoginAktywnejOsoby(Metody.getListaKlientow().get(idKontaKlienta).getLogin());
				JOptionPane.showMessageDialog(null,
						"Witamy, " + Metody.getListaKlientow().get(idKontaKlienta).getImie() + " "
								+ Metody.getListaKlientow().get(idKontaKlienta).getNazwisko(),
						"Informacja logowania", JOptionPane.INFORMATION_MESSAGE);

			}

			else {
				JOptionPane.showMessageDialog(null, "Zostało podane błędne hasło!", "Błąd", JOptionPane.ERROR_MESSAGE);
				return;
			}

		}

		// Logowanie osoby zarządzającej i sprawdzenie hasła
		else {
			if (haslo.equals(Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej).getHaslo())) {
				Metody.setLoginAktywnejOsoby(
						Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej).getLogin());
				if (Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej) instanceof Kierownik)
					Metody.setAktywnaOsobaDostep("kierownik");
				else
					Metody.setAktywnaOsobaDostep("pracownik");
				JOptionPane.showMessageDialog(null,
						"Witamy, " + Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej).getImie() + " "
								+ Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej).getNazwisko(),
						"Informacja logowania", JOptionPane.INFORMATION_MESSAGE);

			}

			else {
				JOptionPane.showMessageDialog(null, "Zostało podane błędne hasło!", "Błąd", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
