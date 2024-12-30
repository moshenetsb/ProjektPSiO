package strategiaGUI;

import java.awt.event.*;
import javax.swing.*;
import bibliotekaMetodIPol.*;

public abstract class WspolneGUI implements GUIstrategia {
	// Składowe kłasy
	private JMenuBar menuBar;

	// Konstruktor
	public WspolneGUI(JFrame frame1) {
		frame1.getContentPane().removeAll();
		frame1.repaint();
		GUIcreate(frame1);
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
				Metody.setLoginAktywnejOsoby(null);
				frame1.setJMenuBar(null);
				Metody.setWybraneGUI(new LoginGUI(frame1));
			}
		});

	}

	// Getters and Setters
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

}
