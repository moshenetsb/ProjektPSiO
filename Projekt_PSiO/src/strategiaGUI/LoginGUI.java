package strategiaGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bibliotekaMetodIPol.*;
import logowanie.*;

public class LoginGUI implements GUIstrategia {

	// Objekty_okna_logowania______________________________
	private JPanel panel1;
	private JPasswordField haslo;
	private JTextField txfLoginEmail;
	private JLabel lbLoginEmail, lbHaslo;
	private JButton btnZaloguj, btnSposobLog;
	// ____________________________________________________

	// Konstruktor
	public LoginGUI(JFrame frame1) {
		frame1.getContentPane().removeAll();
		frame1.repaint();
		GUIcreate(frame1);
	}

	@Override
	public void GUIcreate(JFrame frame1) {
		frame1.setTitle("Logowanie");
		frame1.setSize(400, 200);

		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame1.getContentPane().add(BorderLayout.CENTER, panel1);
		panel1.setLayout(new GridLayout(3, 2));

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
		panel1.add(haslo);

		btnZaloguj = new JButton("Zaloguj");
		panel1.add(btnZaloguj);

		btnSposobLog = new JButton("Inny sposob logowania");
		panel1.add(btnSposobLog);

		// Zmiana sposobu logowania
		btnSposobLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MenuLogowanie.getPreferowaneLogowanie() instanceof Logowanie_LoginHaslo) {
					MenuLogowanie.setPreferowaneLogowanie(new Logowanie_EmailHaslo());
					lbLoginEmail.setText("Email:");
				} else {
					MenuLogowanie.setPreferowaneLogowanie(new Logowanie_LoginHaslo());
					lbLoginEmail.setText("Login:");
				}
				ZapisywanieObiektow.zapiszSposobLogowania();

			}
		});

		// Logowanie
		btnZaloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuLogowanie.getPreferowaneLogowanie().logowanie(txfLoginEmail.getText(),
						new String(haslo.getPassword()), frame1);
			}
		});

	}

}
