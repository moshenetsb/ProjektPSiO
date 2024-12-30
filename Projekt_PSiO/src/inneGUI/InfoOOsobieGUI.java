package inneGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InfoOOsobieGUI {

	// Składowe kłasy
	private JPanel panel1;
	private JPasswordField haslo;
	private JTextField txfEmail, txfLogin, txfNazwisko, txfImie, txfWiek, txfPanstwo, txfKodPocztowy, txfRegion,
			txfMiasto, txfUlica, txfNumerBudynku;
	private JLabel lbTytul, lbEmail, lbLogin, lbHaslo, lbNazwisko, lbImie, lbWiek, lbAdres, lbEmpty, lbPanstwo,
			lbKodPocztowy, lbRegion, lbMiasto, lbUlica, lbNumerBudynku;

	// Konstruktor
	public InfoOOsobieGUI(JFrame frame1) {
		wypelnijGUI(frame1);
	}

	// Wypełnienie okna
	private void wypelnijGUI(JFrame frame1) {
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame1.getContentPane().add(BorderLayout.CENTER, panel1);
		panel1.setLayout(new GridLayout(14, 2));

		// Tytuł strony
		lbTytul = new JLabel("", SwingConstants.CENTER);
		panel1.add(lbTytul);
		lbEmpty = new JLabel();
		panel1.add(lbEmpty);

		// Email
		lbEmail = new JLabel("Email:");
		panel1.add(lbEmail);
		txfEmail = new JTextField(15);
		panel1.add(txfEmail);

		// Login
		lbLogin = new JLabel("Login:");
		panel1.add(lbLogin);
		txfLogin = new JTextField(15);
		panel1.add(txfLogin);

		// Hasło
		lbHaslo = new JLabel("Hasło:");
		panel1.add(lbHaslo);
		haslo = new JPasswordField(15);
		haslo.setEchoChar('*');
		panel1.add(haslo);

		// Nazwisko
		lbNazwisko = new JLabel("Nazwisko:");
		panel1.add(lbNazwisko);
		txfNazwisko = new JTextField(15);
		panel1.add(txfNazwisko);

		// Imię
		lbImie = new JLabel("Imię:");
		panel1.add(lbImie);
		txfImie = new JTextField(15);
		panel1.add(txfImie);

		// Wiek
		lbWiek = new JLabel("Wiek:");
		panel1.add(lbWiek);
		txfWiek = new JTextField(15);
		panel1.add(txfWiek);

		// Adres
		lbAdres = new JLabel("--ADRES--", SwingConstants.CENTER);
		panel1.add(lbAdres);
		lbEmpty = new JLabel();
		panel1.add(lbEmpty);

		// Państwo
		lbPanstwo = new JLabel("Państwo:");
		panel1.add(lbPanstwo);
		txfPanstwo = new JTextField(15);
		panel1.add(txfPanstwo);

		// Kod pocztowy
		lbKodPocztowy = new JLabel("Kod pocztowy:");
		panel1.add(lbKodPocztowy);
		txfKodPocztowy = new JTextField(15);
		panel1.add(txfKodPocztowy);

		// Region
		lbRegion = new JLabel("Region:");
		panel1.add(lbRegion);
		txfRegion = new JTextField(15);
		panel1.add(txfRegion);

		// Miasto
		lbMiasto = new JLabel("Miasto:");
		panel1.add(lbMiasto);
		txfMiasto = new JTextField(15);
		panel1.add(txfMiasto);

		// Ulica
		lbUlica = new JLabel("Ulica:");
		panel1.add(lbUlica);
		txfUlica = new JTextField(15);
		panel1.add(txfUlica);

		// Numer budynku
		lbNumerBudynku = new JLabel("Numer budynku:");
		panel1.add(lbNumerBudynku);
		txfNumerBudynku = new JTextField(15);
		panel1.add(txfNumerBudynku);

		// TODO dodać saldo konta, promocje klienta, pesel (zrobić, żeby były nie
		// zawsze)
	}

	// Getters and Setters
	public JPasswordField getHaslo() {
		return haslo;
	}

	public void setHaslo(JPasswordField haslo) {
		this.haslo = haslo;
	}

	public JTextField getTxfEmail() {
		return txfEmail;
	}

	public void setTxfEmail(JTextField txfEmail) {
		this.txfEmail = txfEmail;
	}

	public JTextField getTxfLogin() {
		return txfLogin;
	}

	public void setTxfLogin(JTextField txfLogin) {
		this.txfLogin = txfLogin;
	}

	public JTextField getTxfNazwisko() {
		return txfNazwisko;
	}

	public void setTxfNazwisko(JTextField txfNazwisko) {
		this.txfNazwisko = txfNazwisko;
	}

	public JTextField getTxfImie() {
		return txfImie;
	}

	public void setTxfImie(JTextField txfImie) {
		this.txfImie = txfImie;
	}

	public JTextField getTxfWiek() {
		return txfWiek;
	}

	public void setTxfWiek(JTextField txfWiek) {
		this.txfWiek = txfWiek;
	}

	public JTextField getTxfPanstwo() {
		return txfPanstwo;
	}

	public void setTxfPanstwo(JTextField txfPanstwo) {
		this.txfPanstwo = txfPanstwo;
	}

	public JTextField getTxfKodPocztowy() {
		return txfKodPocztowy;
	}

	public void setTxfKodPocztowy(JTextField txfKodPocztowy) {
		this.txfKodPocztowy = txfKodPocztowy;
	}

	public JTextField getTxfRegion() {
		return txfRegion;
	}

	public void setTxfRegion(JTextField txfRegion) {
		this.txfRegion = txfRegion;
	}

	public JTextField getTxfMiasto() {
		return txfMiasto;
	}

	public void setTxfMiasto(JTextField txfMiasto) {
		this.txfMiasto = txfMiasto;
	}

	public JTextField getTxfUlica() {
		return txfUlica;
	}

	public void setTxfUlica(JTextField txfUlica) {
		this.txfUlica = txfUlica;
	}

	public JTextField getTxfNumerBudynku() {
		return txfNumerBudynku;
	}

	public void setTxfNumerBudynku(JTextField txfNumerBudynku) {
		this.txfNumerBudynku = txfNumerBudynku;
	}

	public JLabel getLbTytul() {
		return lbTytul;
	}

	public void setLbTytul(JLabel lbTytul) {
		this.lbTytul = lbTytul;
	}

}
