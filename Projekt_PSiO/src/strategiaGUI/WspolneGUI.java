package strategiaGUI;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import bibliotekaMetodIPol.*;
import inneGUI.*;

public abstract class WspolneGUI implements GUIstrategia {
	// Składowe kłasy
	private JMenuBar menuBar;

	// Konstruktor
	public WspolneGUI(JFrame frame1) {
		frame1.getContentPane().removeAll();
		GUIcreate(frame1);
		frame1.revalidate();
		frame1.repaint();
	}

	@Override
	public void GUIcreate(JFrame frame1) {
		frame1.setTitle("Nasz Sklep");
		frame1.setSize(900, 700);

		// Główne menu
		menuBar = new JMenuBar();
		frame1.setJMenuBar(menuBar);

		// Menu ogólne (dla wszystkich)
		JMenu mnOgolne = new JMenu("Ogólne");
		menuBar.add(mnOgolne);

		JMenuItem mntmMojeKonto = new JMenuItem("Moje konto");
		mnOgolne.add(mntmMojeKonto);

		JMenuItem mntmZapisz = new JMenuItem("Zapisz zmiany");
		mnOgolne.add(mntmZapisz);

		JMenuItem mntmWyloguj = new JMenuItem("Wyloguj");
		mnOgolne.add(mntmWyloguj);

		// Informacja z konta użytkownika
		mntmMojeKonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.getContentPane().removeAll();
				InfoOOsobieGUI strona = new InfoOOsobieGUI(frame1);
				strona.getLbTytul().setText("---MOJE KONTO---");
				JButton btnZmiany = new JButton("Zapisz zmiany");
				frame1.getContentPane().add(BorderLayout.SOUTH, btnZmiany);
				frame1.revalidate();
				frame1.repaint();

				// TODO dostęp do danych konta
			}
		});

		// Zapisywanie zmian obiektów
		mntmZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(frame1, "Czy chcesz zapisać zmiany?",
						"Potwierdzenie zapisywania danych", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION)
					ZapisywanieObiektow.zapiszDane(frame1);
			}
		});

		// Zapisywanie zmian po zamknięciu programu
		frame1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showConfirmDialog(frame1, "Czy chcesz zapisać zmiany?",
						"Potwierdzenie zapisywania danych", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					ZapisywanieObiektow.zapiszDane(frame1);
					frame1.dispose();
				} else if (confirm == JOptionPane.NO_OPTION)
					frame1.dispose();

			}
		});

		// Wylogowanie
		mntmWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int confirmWyloguj = JOptionPane.showConfirmDialog(frame1, "Czy na pewno chcesz się wylogować?",
						"Potwierdzenie wylogowania", JOptionPane.YES_NO_OPTION);

				if (confirmWyloguj == JOptionPane.YES_OPTION) {

					// Pytamy czy zapisać zmiany
					int confirmSave = JOptionPane.showConfirmDialog(frame1, "Czy chcesz zapisać zmiany?",
							"Potwierdzenie zapisywania danych", JOptionPane.YES_NO_OPTION);

					if (confirmSave == JOptionPane.YES_OPTION)
						ZapisywanieObiektow.zapiszDane(frame1);

					// Usuwamy możliwość zapisać dane przez zamknięcie okna
					for (WindowListener listener : frame1.getWindowListeners())
		                frame1.removeWindowListener(listener);

					// Zostawiamy tylko główny frame
					closeAllExceptMain(frame1);

					// Zmieniamy wypełnienie okna
					Metody.setLoginAktywnejOsoby(null);
					frame1.setJMenuBar(null);
					Metody.setWybraneGUI(new LoginGUI(frame1));

				}

			}
		});

	}

	private void closeAllExceptMain(JFrame oknoGlowne) {
		for (Window window : Window.getWindows()) {
			// Sprawdzamy, czy okno nie jest głównym i czy jest widoczne
			if (window != oknoGlowne && window.isVisible()) {
				// Zamykamy okno
				window.dispose();
			}
		}
	}

}
