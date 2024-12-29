package TRZEBAUSUNAC;

import javax.swing.*;

import bibliotekaMetodIPol.*;

import java.awt.*;

public class GUI_Main extends JFrame {

	private JFrame frame;

	private GUI_Main() {
		// Tworzymy okno
		frame = new JFrame("Nasz sklep");
		frame.setSize(1120, 720);
		
		// Menu Główne
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// Menu Kierownika
		JMenu mnKierownik = new JMenu("Kierownik");
		menuBar.add(mnKierownik);
		// JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		// mnNewMenu.add(mntmNewMenuItem);

		// Menu Pracownika
		JMenu mnPracownik = new JMenu("Pracownik");
		menuBar.add(mnPracownik);

		// Menu Klienta
		JMenu mnKlient = new JMenu("Klient");
		menuBar.add(mnKlient);

		// Wyloguj
		JMenu mnWyloguj = new JMenu("Wyloguj");
		menuBar.add(mnWyloguj);
	}

}
