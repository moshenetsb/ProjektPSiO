package gui;

import logowanie.*;
import metody.*;
import osoba.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JDialog {
	// ___________TESTOWANIE_____________________________
	//public static void main(String[] args) {
	//	Metody.listaOsobZarzadzajacych.add(new Pracownik("ghf@gmail.com", "1234", "chleb", "Jan Padalecki", 18, null, 0, null, 0));
	//	Login login = new Login();
	//}

	// ____________________________________________________

	// Objekty_okna_logowania______________________________
	private JPasswordField haslo;
	private JTextField txfLoginEmail;
	private JLabel lbLoginEmail, lbHaslo;
	private JButton btnZaloguj, btnSposobLog;

	// ____________________________________________________

	// Konstruktor_________________________________________
	private Login() {
		this.setTitle("Logowanie");
		this.setSize(400, 350);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.setLayout(null);

		Metody.wczytajSposobLogowania();
		lbLoginEmail = new JLabel();
		if (MenuLogowanie.getPreferowaneLogowanie() instanceof Logowanie_LoginHaslo) {
			lbLoginEmail.setText("Login:");
		} else {
			lbLoginEmail.setText("Email:");
		}
		lbLoginEmail.setBounds(100, 25, 200, 20);
		txfLoginEmail = new JTextField();
		txfLoginEmail.setBounds(100, 50, 200, 40);

		this.add(lbLoginEmail);
		this.add(txfLoginEmail);

		lbHaslo = new JLabel("Hasło:");
		lbHaslo.setBounds(100, 100, 200, 20);
		haslo = new JPasswordField();
		haslo.setBounds(100, 125, 200, 40);

		this.add(lbHaslo);
		this.add(haslo);

		btnZaloguj = new JButton("Zaloguj");
		btnZaloguj.setBounds(100, 175, 200, 40);

		this.add(btnZaloguj);

		btnSposobLog = new JButton("Inny sposob logowania");
		btnSposobLog.setBounds(100, 225, 200, 40);

		this.add(btnSposobLog);

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
				Metody.zapiszSposobLogowania();

			}
		});

		// Logowanie
		btnZaloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuLogowanie.getPreferowaneLogowanie().logowanie(txfLoginEmail.getText(), new String(haslo.getPassword()));
			}
		});

		setVisible(true);
	}
	// ____________________________________________________

}
