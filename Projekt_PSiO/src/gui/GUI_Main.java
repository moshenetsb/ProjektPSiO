package gui;

import javax.swing.*;

import metody.*;

import java.awt.*;

public class GUI_Main extends JFrame {

	private JFrame frame;

	private GUI_Main() {
		// Tworzymy okno
		frame = new JFrame("Nasz sklep");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(896, 576));
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

	public static void main(String[] args) {
		// Metody.wczytajDane();
		GUI_Main guiMain = new GUI_Main();
		guiMain.frame.setVisible(true);

		// Metody.zapiszDane();
	}
}
