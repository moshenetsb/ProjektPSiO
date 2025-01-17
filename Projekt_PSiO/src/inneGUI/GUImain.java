package inneGUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import bibliotekaMetodIPol.*;
import strategiaGUI.*;

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
		frame1.setVisible(true);
	}

	public static void ustawWyswietlelieHasla(JPasswordField hasloField) {
		// Pokazywanie hasła po przesunięciu na niego wskażnika myszy
		hasloField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hasloField.setEchoChar((char) 0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hasloField.setEchoChar('*');
			}
		});
	}
}
