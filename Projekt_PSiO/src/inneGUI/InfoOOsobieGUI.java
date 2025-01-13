package inneGUI;

import osoba.Osoba;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.*;

public class InfoOOsobieGUI {

	// Składowe kłasy
	private JFrame frame1;
	private JPanel panel1;
	private JPasswordField haslo;
	private JTextField txfEmail, txfLogin, txfNazwisko, txfImie, txfWiek, txfPanstwo, txfKodPocztowy, txfRegion,
			txfMiasto, txfUlica, txfNumerBudynku;
	private JLabel lbTytul, lbEmail, lbLogin, lbHaslo, lbNazwisko, lbImie, lbWiek, lbAdres, lbEmpty, lbPanstwo,
			lbKodPocztowy, lbRegion, lbMiasto, lbUlica, lbNumerBudynku;

	// Konstruktor
	public InfoOOsobieGUI(JFrame frame1) {
		wypelnijGUI(frame1);
		this.frame1 = frame1;
	}

	// Wypełnienie okna
	private void wypelnijGUI(JFrame frame1) {
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame1.getContentPane().add(BorderLayout.CENTER, panel1);
		panel1.setLayout(new GridLayout(14, 2, 10, 15));

		Font customFont = new Font("Arial", Font.BOLD, 16);

		this.txfImie = createStyledTextField("Wpisz imie");
		this.txfNazwisko = createStyledTextField("Wpisz nazwisko");
		this.txfEmail = createStyledTextField("Wpisz email");
		this.txfLogin = createStyledTextField("Wpisz login");
		this.txfWiek = createStyledTextField("Wpisz wiek");
		this.txfPanstwo = createStyledTextField("Wpisz państwo");
		this.txfKodPocztowy = createStyledTextField("Wpisz kod pocztowy");
		this.txfRegion = createStyledTextField("Wpisz region");
		this.txfMiasto = createStyledTextField("Wpisz swoje miasto");
		this.txfUlica = createStyledTextField("Wpisz ulicę");
		this.txfNumerBudynku = createStyledTextField("Wpisz numer budynku");

		// Tytuł strony
		lbTytul = new JLabel("", SwingConstants.CENTER);
		panel1.add(lbTytul);
		lbEmpty = new JLabel();
		panel1.add(lbEmpty);

		// Email
		lbEmail = new JLabel("Email:");
		lbEmail.setFont(customFont);
		panel1.add(lbEmail);
		panel1.add(this.txfEmail);

		// Login
		lbLogin = new JLabel("Login:");
		lbLogin.setFont(customFont);
		panel1.add(lbLogin);
		panel1.add(this.txfLogin);

		// Hasło
		lbHaslo = new JLabel("Hasło:");
		lbHaslo.setFont(customFont);
		panel1.add(lbHaslo);
		haslo = createStyledPasswordField("Wpisz hasło");
		haslo.setEchoChar('*');
		panel1.add(haslo);
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

		// Nazwisko
		lbNazwisko = new JLabel("Nazwisko:");
		lbNazwisko.setFont(customFont);
		panel1.add(lbNazwisko);
		panel1.add(this.txfNazwisko);

		// Imię
		lbImie = new JLabel("Imię:");
		lbImie.setFont(customFont);
		panel1.add(lbImie);
		panel1.add(this.txfImie);

		// Wiek
		lbWiek = new JLabel("Wiek:");
		lbWiek.setFont(customFont);
		panel1.add(lbWiek);
		panel1.add(this.txfWiek);

		// Adres
		lbAdres = new JLabel("   ADRES   ", SwingConstants.CENTER);
		lbAdres.setFont(customFont);
		panel1.add(lbAdres);
		lbEmpty = new JLabel();
		panel1.add(lbEmpty);

		// Państwo
		lbPanstwo = new JLabel("Państwo:");
		lbPanstwo.setFont(customFont);
		panel1.add(lbPanstwo);
		panel1.add(this.txfPanstwo);

		// Kod pocztowy
		lbKodPocztowy = new JLabel("Kod pocztowy:");
		lbKodPocztowy.setFont(customFont);
		panel1.add(lbKodPocztowy);
		panel1.add(this.txfKodPocztowy);

		// Region
		lbRegion = new JLabel("Region:");
		lbRegion.setFont(customFont);
		panel1.add(lbRegion);
		panel1.add(this.txfRegion);

		// Miasto
		lbMiasto = new JLabel("Miasto:");
		lbMiasto.setFont(customFont);
		panel1.add(lbMiasto);
		panel1.add(this.txfMiasto);

		// Ulica
		lbUlica = new JLabel("Ulica:");
		lbUlica.setFont(customFont);
		panel1.add(lbUlica);
		panel1.add(this.txfUlica);

		// Numer budynku
		lbNumerBudynku = new JLabel("Numer budynku:");
		lbNumerBudynku.setFont(customFont);
		panel1.add(lbNumerBudynku);
		panel1.add(this.txfNumerBudynku);

		// TODO dodać saldo konta, promocje klienta, pesel (zrobić, żeby były nie
		// zawsze)
	}

	private static JTextField createStyledTextField(String placeholder) {
		JTextField textField = new JTextField(15);

		// Добавляем отступы внутри текстового поля
		textField.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200), 2, true), // Внешняя рамка с
																									// закруглением
				new EmptyBorder(5, 10, 5, 10) // Внутренний отступ
		));

		// Задаем шрифт
		textField.setFont(new Font("Arial", Font.PLAIN, 14));

		// Цвет текста и фона
		textField.setBackground(new Color(245, 245, 245)); // Светло-серый фон
		textField.setForeground(Color.BLACK);

		// Добавляем placeholder (подсказку)
		textField.setText(placeholder);
		textField.setForeground(Color.GRAY);

		// Сброс текста при фокусе
		textField.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setText(placeholder);
					textField.setForeground(Color.GRAY);
				}
			}
		});

		return textField;
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

	private static JPasswordField createStyledPasswordField(String placeholder) {
		JPasswordField passwordField = new JPasswordField(15);

		// Добавляем отступы внутри текстового поля
		passwordField.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200), 2, true), // Внешняя рамка с
																										// закруглением
				new EmptyBorder(5, 10, 5, 10) // Внутренний отступ
		));

		// Задаем шрифт
		passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

		// Цвет текста и фона
		passwordField.setBackground(new Color(245, 245, 245)); // Светло-серый фон
		passwordField.setForeground(Color.GRAY);

		// Установка подсказки (placeholder)
		passwordField.setText(placeholder);
		passwordField.setEchoChar((char) 0); // Убираем звёздочки, чтобы подсказка была видна

		// Добавляем слушатели для обработки фокуса
		passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (new String(passwordField.getPassword()).equals(placeholder)) {
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
					passwordField.setEchoChar('*'); // Включаем отображение звёздочек
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (new String(passwordField.getPassword()).isEmpty()) {
					passwordField.setText(placeholder);
					passwordField.setForeground(Color.GRAY);
					passwordField.setEchoChar((char) 0); // Скрываем звёздочки для подсказки
				}
			}
		});

		return passwordField;
	}

	public void wyswietlInformacje(Osoba osoba) {
		if (osoba != null) {
			txfImie.setText(osoba.getImie());
			txfNazwisko.setText(osoba.getNazwisko());
			txfEmail.setText(osoba.getEmail());
			haslo.setText(osoba.getHaslo());
			txfLogin.setText(osoba.getLogin());
			txfWiek.setText(String.valueOf(osoba.getWiek()));

		} else {
			JOptionPane.showMessageDialog(frame1, "Brak danych użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
		}
	}

}
