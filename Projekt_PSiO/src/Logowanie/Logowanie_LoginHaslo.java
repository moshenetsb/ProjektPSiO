package Logowanie;

import Metody.Metody;
import osoba.*;
import javax.swing.*;

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

			if (haslo.equals(Metody.listaKlientow.get(idKontaKlienta).getHaslo())) {
				Metody.aktywnaOsobaDostep = "klient";
				Metody.idAktywnejOsoby = idKontaKlienta;
				JOptionPane.showMessageDialog(null, "Witamy, " + Metody.listaKlientow.get(Metody.idAktywnejOsoby).getImieNazwisko());
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
