package logowanie;

import osoba.*;
import javax.swing.*;

import metody.Metody;

public class Logowanie_LoginHaslo implements Logowanie {

	@Override
	public void logowanie(String loginEmail, String haslo) {

		// Sprawdzenie istnienia użytkowników z podanym loginem
		int idKontaOsobyZarzadzajacej = MenuLogowanie.czyIstniejeLoginOsobyZarzadzajacej(loginEmail);
		int idKontaKlienta = MenuLogowanie.czyIstniejeLoginKlienta(loginEmail);

		// Jeśli błędny login
		if (idKontaOsobyZarzadzajacej == -1 && idKontaKlienta == -1) {
			JOptionPane.showMessageDialog(null, "Podany login nie istnieje!");
			return;
		}

		// Logowanie klienta i sprawdzenie hasła
		if (idKontaOsobyZarzadzajacej == -1) {

			if (haslo.equals(Metody.getListaKlientow().get(idKontaKlienta).getHaslo())) {
				Metody.setAktywnaOsobaDostep("klient");
				Metody.setLoginAktywnejOsoby(loginEmail);
				JOptionPane.showMessageDialog(null, "Witamy, " + Metody.getListaKlientow().get(idKontaKlienta).getImieNazwisko());
			}

			else {
				JOptionPane.showMessageDialog(null, "Zostało podane błędne hasło!");
				return;
			}

		}

		// Logowanie osoby zarządzającej i sprawdzenie hasła
		else {
			if (haslo.equals(Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej).getHaslo())) {
				Metody.setLoginAktywnejOsoby(loginEmail);
				if (Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej) instanceof Kierownik)
					Metody.setAktywnaOsobaDostep("kierownik");
				else
					Metody.setAktywnaOsobaDostep("pracownik");
				JOptionPane.showMessageDialog(null, "Witamy, " + Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej).getImieNazwisko());
			
			}

			else {
				JOptionPane.showMessageDialog(null, "Zostało podane błędne hasło!");
				return;
			}
		}

	}

}
