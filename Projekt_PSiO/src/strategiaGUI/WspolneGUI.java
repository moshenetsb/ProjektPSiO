package strategiaGUI;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import bibliotekaMetodIPol.*;
import inneGUI.*;
import osoba.Osoba;
import inneGUI.InfoOOsobieGUI;
import logowanie.MenuLogowanie;

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

		try {
			frame1.setIconImage(ImageIO.read(new File("./Grafika/dolarZielony.png")));
		} catch (Exception e) {
			System.err.println("Błąd podczas wczytywania ikony: " + e.getMessage());
		}

		// Główne menu
		menuBar = new JMenuBar();
		frame1.setJMenuBar(menuBar);

		// Menu ogólne (dla wszystkich)
		JMenu mnOgolne = new JMenu("Ogólne");
		menuBar.add(mnOgolne);

		JMenuItem mntmMojeKonto = new JMenuItem("Zrobienie konta");
		mnOgolne.add(mntmMojeKonto);

		JMenuItem mntmMojeDane = new JMenuItem("Moje konto");
		mnOgolne.add(mntmMojeDane);

		JMenuItem mntmZapisz = new JMenuItem("Zapisz zmiany");
		mnOgolne.add(mntmZapisz);

		JMenuItem mntmWyloguj = new JMenuItem("Wyloguj");
		mnOgolne.add(mntmWyloguj);

		mntmMojeDane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				frame1.getContentPane().removeAll();

				InfoOOsobieGUI strona = new InfoOOsobieGUI(frame1);
				strona.getLbTytul().setText("   MOJE KONTO   ");
				strona.getLbTytul().setFont(new Font("Arial", Font.BOLD, 24));
				strona.getLbTytul().setForeground(Color.GREEN);

				frame1.getContentPane().add(panel, BorderLayout.SOUTH);

				Osoba osoba;
				if (Metody.getWybraneGUI() instanceof KlientGUI)
					osoba = Metody.getListaKlientow().get(MenuLogowanie.szukajIDLoginKlienta(Metody.getLoginAktywnejOsoby()));
				else
					osoba = Metody.getListaOsobZarzadzajacych().get(MenuLogowanie.szukajIDLoginZarzadzajacych(Metody.getLoginAktywnejOsoby()));

				strona.wyswietlInformacje(osoba);

				frame1.revalidate();
				frame1.repaint();
			}
		});

		// Informacja z konta użytkownika
		mntmMojeKonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				frame1.getContentPane().removeAll();

				InfoOOsobieGUI strona = new InfoOOsobieGUI(frame1);
				strona.getLbTytul().setText("   MOJE KONTO   ");
				strona.getLbTytul().setFont(new Font("Arial", Font.BOLD, 24)); // Изменяем шрифт на Arial, жирный и
																				// размер 24
				strona.getLbTytul().setForeground(Color.GREEN);

				JButton saveButton = new JButton("Zapisz zmiany");
				saveButton.setBackground(new Color(67, 160, 71)); // Зеленый фон
				saveButton.setForeground(Color.WHITE); // Белый текст
				saveButton.setFont(new Font("Arial", Font.BOLD, 14)); // Жирный шрифт
				saveButton.setFocusPainted(false);
				panel.add(saveButton);

				frame1.getContentPane().add(panel, BorderLayout.SOUTH);

				// Osoba osoba = Metody.getAktywnaOsoba();

				frame1.revalidate();
				frame1.repaint();

			}
		});
		// TODO dostęp do danych konta

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