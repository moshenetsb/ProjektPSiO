package logowanie;

import osoba.*;
import strategiaGUI.*;
import javax.swing.*;
import bibliotekaMetodIPol.Metody;

public class Logowanie_LoginHaslo implements Logowanie {

	@Override
	public void logowanie(String loginEmail, String haslo, JFrame frame1) {

		// Sprawdzenie istnienia użytkowników z podanym loginem
		int idKontaOsobyZarzadzajacej = MenuLogowanie.szukajIDLoginZarzadzajacych(loginEmail);
		int idKontaKlienta = MenuLogowanie.szukajIDLoginKlienta(loginEmail);

		// Jeśli błędny login
		if (idKontaOsobyZarzadzajacej == -1 && idKontaKlienta == -1) {
			JOptionPane.showMessageDialog(frame1, "Podany login nie istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Logowanie klienta i sprawdzenie hasła
		if (idKontaOsobyZarzadzajacej == -1) {

			if (haslo.equals(Metody.getListaKlientow().get(idKontaKlienta).getHaslo())) {
				Metody.setWybraneGUI(new KlientGUI(frame1));
				Metody.setLoginAktywnejOsoby(loginEmail);
				JOptionPane.showMessageDialog(frame1,
						"Witamy, " + Metody.getListaKlientow().get(idKontaKlienta).getImie() + " "
								+ Metody.getListaKlientow().get(idKontaKlienta).getNazwisko(),
						"Informacja logowania", JOptionPane.INFORMATION_MESSAGE);
			}

			else {
				JOptionPane.showMessageDialog(frame1, "Zostało podane błędne hasło!", "Błąd", JOptionPane.ERROR_MESSAGE);
				return;
			}

		}

		// Logowanie osoby zarządzającej i sprawdzenie hasła
		else {
			if (haslo.equals(Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej).getHaslo())) {
				Metody.setLoginAktywnejOsoby(loginEmail);
				if (Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej) instanceof Kierownik)
					Metody.setWybraneGUI(new KierownikGUI(frame1));
				else
					Metody.setWybraneGUI(new PracownikGUI(frame1));
				JOptionPane.showMessageDialog(frame1,
						"Witamy, " + Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej).getImie() + " "
								+ Metody.getListaOsobZarzadzajacych().get(idKontaOsobyZarzadzajacej).getNazwisko(),
						"Informacja logowania", JOptionPane.INFORMATION_MESSAGE);

			}

			else {
				JOptionPane.showMessageDialog(frame1, "Zostało podane błędne hasło!", "Błąd", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}

}
