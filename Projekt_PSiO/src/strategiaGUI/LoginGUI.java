package strategiaGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import bibliotekaMetodIPol.*;
import logowanie.*;

public class LoginGUI implements GUIstrategia {

	// Objekty_okna_logowania______________________________
	private JPanel panel1;
	private JPasswordField haslo;
	private JTextField txfLoginEmail;
	private JLabel lbLoginEmail, lbHaslo;
	private JButton btnZaloguj, btnSposobLog, btnRejestracja;
	// ____________________________________________________

	// Konstruktor
	public LoginGUI(JFrame frame1) {

		// frame1 = null; // testowanie assert
		assert (frame1 != null) : "Frame1 jest nullem!";

		try {
			frame1.setIconImage(ImageIO.read(new File("Grafika/login.jpg")));
		} catch (Exception e) {
			System.err.println("Błąd podczas wczytywania ikony: " + e.getMessage());
		}

		frame1.getContentPane().removeAll();
		GUIcreate(frame1);
		frame1.revalidate();
		frame1.repaint();
	}

	@Override
	public void GUIcreate(JFrame frame1) {
		frame1.setTitle("Logowanie");
		frame1.setSize(390, 200);

		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(0, 10, 0, 10));
		frame1.getContentPane().add(BorderLayout.CENTER, panel1);
		panel1.setLayout(new GridLayout(4, 1));

		WczytywanieObiektow.wczytajSposobLogowania();
		lbLoginEmail = new JLabel();
		if (MenuLogowanie.getPreferowaneLogowanie() instanceof Logowanie_LoginHaslo)
			lbLoginEmail.setText("Login:");
		else
			lbLoginEmail.setText("Email:");
		panel1.add(lbLoginEmail);

		txfLoginEmail = new JTextField(15);
		panel1.add(txfLoginEmail);

		lbHaslo = new JLabel("Hasło:");
		panel1.add(lbHaslo);

		haslo = new JPasswordField(15);
		haslo.setEchoChar('*');
		panel1.add(haslo);

		JPanel panel2 = new JPanel();
		panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame1.getContentPane().add(BorderLayout.SOUTH, panel2);

		btnZaloguj = new JButton("Zaloguj");
		panel2.add(btnZaloguj);

		btnSposobLog = new JButton("Inny sposob logowania");
		panel2.add(btnSposobLog);

		btnRejestracja = new JButton("Rejestracja");
		panel2.add(btnRejestracja);

		// Pokazywanie hasła po przesunięciu na niego wskażnika myszy
		haslo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				haslo.setEchoChar((char) 0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				haslo.setEchoChar('*');
			}
		});

		// Zmiana sposobu logowania
		btnSposobLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MenuLogowanie.getPreferowaneLogowanie() instanceof Logowanie_LoginHaslo) {
					MenuLogowanie.setPreferowaneLogowanie(new Logowanie_EmailHaslo());
					lbLoginEmail.setText("Email:");
				} else {
					MenuLogowanie.setPreferowaneLogowanie(new Logowanie_LoginHaslo());
					lbLoginEmail.setText("Login:");
				}
				ZapisywanieObiektow.zapiszSposobLogowania();

				// Oczyszczenie tekstu
				txfLoginEmail.setText("");
				haslo.setText("");

			}
		});

		// Logowanie
		btnZaloguj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuLogowanie.getPreferowaneLogowanie().logowanie(txfLoginEmail.getText(),
						new String(haslo.getPassword()), frame1);
			}
		});

		btnRejestracja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metody.setWybraneGUI(new Rejestracja(frame1));
			}
		});

	}

}
