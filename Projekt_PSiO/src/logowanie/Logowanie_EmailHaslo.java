package logowanie;

import javax.swing.JOptionPane;

import metody.Metody;
import osoba.Kierownik;

public class Logowanie_EmailHaslo implements Logowanie {

	@Override
	public void logowanie(String loginEmail, String haslo) {

		// Sprawdzenie istnienia użytkowników z podanym email
		int idKontaOsobyZarzadzajacej = MenuLogowanie.czyIstniejeEmailOsobyZarzadzajacej(loginEmail);
		int idKontaKlienta = MenuLogowanie.czyIstniejeEmailKlienta(loginEmail);

		// Jeśli błędny email
		if (idKontaOsobyZarzadzajacej == -1 && idKontaKlienta == -1) {
			JOptionPane.showMessageDialog(null, "Podany email nie istnieje!");
			return;
		}

		// Logowanie klienta i sprawdzenie hasła
		if (idKontaOsobyZarzadzajacej == -1) {

			if (haslo.equals(Metody.listaKlientow.get(idKontaKlienta).getHaslo())) {
				Metody.aktywnaOsobaDostep = "klient";
				Metody.idAktywnejOsoby = idKontaKlienta;
				JOptionPane.showMessageDialog(null,
						"Witamy, " + Metody.listaKlientow.get(idKontaKlienta).getImieNazwisko());

			}

			else {
				JOptionPane.showMessageDialog(null, "Zostało podane błędne hasło!");
				return;
			}

		}

		// Logowanie osoby zarządzającej i sprawdzenie hasła
		else {
			if (haslo.equals(Metody.listaOsobZarzadzajacych.get(idKontaOsobyZarzadzajacej).getHaslo())) {
				Metody.idAktywnejOsoby = idKontaOsobyZarzadzajacej;
				if (Metody.listaOsobZarzadzajacych.get(Metody.idAktywnejOsoby) instanceof Kierownik)
					Metody.aktywnaOsobaDostep = "kierownik";
				else
					Metody.aktywnaOsobaDostep = "pracownik";
				JOptionPane.showMessageDialog(null, "Witamy, " + Metody.listaOsobZarzadzajacych.get(Metody.idAktywnejOsoby).getImieNazwisko());

			}

			else {
				JOptionPane.showMessageDialog(null, "Zostało podane błędne hasło!");
				return;
			}
		}
	}

}
