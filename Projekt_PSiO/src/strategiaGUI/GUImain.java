package strategiaGUI;

import javax.swing.*;
import bibliotekaMetodIPol.*;


public class GUImain {

	// Składowe kłasy
	private JFrame frame1;

	// Konstruktor
	public GUImain() {
		frame1 = new JFrame();
		Metody.setWybraneGUI(new LoginGUI(frame1));
		wspolneDlaGUI();
	}

	// Metody
	private void wspolneDlaGUI() {
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setResizable(false);
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}
}
