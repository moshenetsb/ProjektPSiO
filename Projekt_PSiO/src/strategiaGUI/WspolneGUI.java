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
		frame1.setSize(900, 700); // TODO ustawić potrzebny rozmiar

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
				ZapisywanieObiektow.zapiszDane();
			}
		});

		// Wylogowanie
		mntmWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeAllExceptMain(frame1);
				
				Metody.setLoginAktywnejOsoby(null);
				frame1.setJMenuBar(null);
				Metody.setWybraneGUI(new LoginGUI(frame1));
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
