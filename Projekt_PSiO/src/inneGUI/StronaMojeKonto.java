package inneGUI;

import osoba.*;
import strategiaGUI.KlientGUI;
import strategiaGUI.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import adres.Adres;
import bibliotekaMetodIPol.Metody;
import logowanie.MenuLogowanie;

public class StronaMojeKonto {

	// Składowe kłasy
	private JFrame frame1;
	private Osoba osoba;
	private JPanel panelCenter, panel1, panel2, panelNorth;
	private JPasswordField haslo;
	private JTextField txfEmail, txfLogin, txfNazwisko, txfImie, txfWiek, txfPanstwo, txfKodPocztowy, txfRegion,
			txfMiasto, txfUlica, txfNumerBudynku, txfPromocja, txfPESEL;
	private JLabel lbTytul, lbEmail, lbLogin, lbHaslo, lbNazwisko, lbImie, lbWiek, lbAdres, lbPanstwo, lbKodPocztowy,
			lbRegion, lbMiasto, lbUlica, lbNumerBudynku, lbPromocja, lbPESEL, lbMojeDane;

	// Konstruktor
	public StronaMojeKonto(JFrame frame1, Osoba osoba) {
		frame1.getContentPane().removeAll();

		wypelnijGUI(frame1);

		frame1.revalidate();
		frame1.repaint();

		this.wyswietlInformacje(osoba);

		this.frame1 = frame1;
		this.osoba = osoba;
	}

	// Wypełnienie okna
	private void wypelnijGUI(JFrame frame1) {

		panelCenter = new JPanel();
		panelCenter.setBorder(new EmptyBorder(5, 60, 20, 60));
		panelCenter.setLayout(new GridLayout(1, 2, 20, 0));

		JScrollPane scrollPane = new JScrollPane(panelCenter);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame1.getContentPane().add(BorderLayout.CENTER, scrollPane);

		panel1 = new JPanel();
		panelCenter.add(panel1);
		panel1.setBorder(new EmptyBorder(5, 0, 10, 10));
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		panel2 = new JPanel();
		panelCenter.add(panel2);
		panel2.setBorder(new EmptyBorder(5, 10, 10, 0));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

		Font customFont = new Font("Arial", Font.BOLD, 16);

		this.txfImie = createStyledTextField("Wpisz imie");
		this.txfNazwisko = createStyledTextField("Wpisz nazwisko");
		this.txfEmail = createStyledTextField("Wpisz email");
		this.txfLogin = createStyledTextField("Wpisz login");
		this.txfWiek = createStyledTextField("Wpisz wiek");
		this.txfPESEL = createStyledTextField("Wpisz PESEL");
		this.txfPromocja = createStyledTextField("Promocja klienta");
		this.txfPanstwo = createStyledTextField("Wpisz państwo");
		this.txfKodPocztowy = createStyledTextField("Wpisz kod pocztowy");
		this.txfRegion = createStyledTextField("Wpisz region");
		this.txfMiasto = createStyledTextField("Wpisz swoje miasto");
		this.txfUlica = createStyledTextField("Wpisz ulicę");
		this.txfNumerBudynku = createStyledTextField("Wpisz numer budynku");

		// Tytuł strony
		panelNorth = new JPanel();
		lbTytul = new JLabel("MOJE KONTO", SwingConstants.CENTER);
		panelNorth.add(lbTytul);
		lbTytul.setFont(new Font("Arial", Font.BOLD, 24));
		lbTytul.setForeground(Color.GREEN);
		panelNorth.setBorder(new EmptyBorder(0, 0, 20, 0));
		frame1.getContentPane().add(panelNorth, BorderLayout.NORTH);

		lbMojeDane = new JLabel("MOJE DANE", SwingConstants.CENTER);
		lbMojeDane.setFont(customFont);
		lbMojeDane.setBorder(new EmptyBorder(10, 20, 20, 30));
		panel1.add(lbMojeDane);

		// Email
		lbEmail = new JLabel("Email:");
		lbEmail.setFont(customFont);
		lbEmail.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel1.add(lbEmail);
		panel1.add(this.txfEmail);

		// Login
		lbLogin = new JLabel("Login:");
		lbLogin.setFont(customFont);
		lbLogin.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel1.add(lbLogin);
		panel1.add(this.txfLogin);

		// Hasło
		lbHaslo = new JLabel("Hasło:");
		lbHaslo.setFont(customFont);
		lbHaslo.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel1.add(lbHaslo);
		haslo = createStyledPasswordField("Wpisz hasło");
		haslo.setEchoChar('*');
		panel1.add(haslo);
		GUImain.ustawWyswietlelieHasla(haslo);

		// Nazwisko
		lbNazwisko = new JLabel("Nazwisko:");
		lbNazwisko.setFont(customFont);
		lbNazwisko.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel1.add(lbNazwisko);
		panel1.add(this.txfNazwisko);

		// Imię
		lbImie = new JLabel("Imię:");
		lbImie.setFont(customFont);
		lbImie.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel1.add(lbImie);
		panel1.add(this.txfImie);

		// Wiek
		lbWiek = new JLabel("Wiek:");
		lbWiek.setFont(customFont);
		lbWiek.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel1.add(lbWiek);
		panel1.add(this.txfWiek);

		if (Metody.getWybraneGUI() instanceof PracownikGUI) {
			// PESEL
			lbPESEL = new JLabel("PESEL:");
			lbPESEL.setFont(customFont);
			lbPESEL.setBorder(new EmptyBorder(10, 0, 10, 0));
			panel1.add(lbPESEL);
			panel1.add(this.txfPESEL);
		} else {
			// Promocja
			lbPromocja = new JLabel("Promocja:");
			lbPromocja.setFont(customFont);
			lbPromocja.setBorder(new EmptyBorder(10, 0, 10, 0));
			txfPromocja.setEditable(false);
			panel1.add(lbPromocja);
			panel1.add(this.txfPromocja);
		}

		// Adres
		lbAdres = new JLabel("ADRES ZAMIESZKANIA", SwingConstants.CENTER);
		lbAdres.setFont(customFont);
		lbAdres.setBorder(new EmptyBorder(10, 20, 20, 30));
		panel2.add(lbAdres);

		// Państwo
		lbPanstwo = new JLabel("Państwo:");
		lbPanstwo.setFont(customFont);
		lbPanstwo.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel2.add(lbPanstwo);
		panel2.add(this.txfPanstwo);

		// Kod pocztowy
		lbKodPocztowy = new JLabel("Kod pocztowy:");
		lbKodPocztowy.setFont(customFont);
		lbKodPocztowy.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel2.add(lbKodPocztowy);
		panel2.add(this.txfKodPocztowy);

		// Region
		lbRegion = new JLabel("Region:");
		lbRegion.setFont(customFont);
		lbRegion.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel2.add(lbRegion);
		panel2.add(this.txfRegion);

		// Miasto
		lbMiasto = new JLabel("Miasto:");
		lbMiasto.setFont(customFont);
		lbMiasto.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel2.add(lbMiasto);
		panel2.add(this.txfMiasto);

		// Ulica
		lbUlica = new JLabel("Ulica:");
		lbUlica.setFont(customFont);
		lbUlica.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel2.add(lbUlica);
		panel2.add(this.txfUlica);

		// Numer budynku
		lbNumerBudynku = new JLabel("Numer budynku:");
		lbNumerBudynku.setFont(customFont);
		lbNumerBudynku.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel2.add(lbNumerBudynku);
		panel2.add(this.txfNumerBudynku);

		// Przyciski do zapisywania danych
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JButton saveMyInfoButton = new JButton("Edytuj dane osobowe");
		saveMyInfoButton.setBackground(new Color(67, 160, 71)); // Зеленый фон
		saveMyInfoButton.setForeground(Color.WHITE); // Белый текст
		saveMyInfoButton.setFont(new Font("Arial", Font.BOLD, 14)); // Жирный шрифт
		saveMyInfoButton.setFocusPainted(false);
		panel.add(saveMyInfoButton);

		JButton saveAdresButton = new JButton("Edytuj adres zamieszkania");
		saveAdresButton.setBackground(new Color(67, 160, 71)); // Зеленый фон
		saveAdresButton.setForeground(Color.WHITE); // Белый текст
		saveAdresButton.setFont(new Font("Arial", Font.BOLD, 14)); // Жирный шрифт
		saveAdresButton.setFocusPainted(false);
		panel.add(saveAdresButton);

		frame1.getContentPane().add(panel, BorderLayout.SOUTH);

		saveMyInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = txfEmail.getText().trim();
				String haslo1 = new String(haslo.getPassword()).trim();
				String login = txfLogin.getText().trim();
				String nazwisko = txfNazwisko.getText().trim();
				String imie = txfImie.getText().trim();
				String wiek = txfWiek.getText().trim();

				if (Osoba.isValidData(frame1, email, haslo1, login, nazwisko, imie, wiek, true, osoba)) {

					if (Metody.getWybraneGUI() instanceof PracownikGUI) {
						String pesel = txfPESEL.getText().trim();
						if (Pracownik.isValidDataPracownik(frame1, pesel)) {
							Pracownik pracownik;

							if (Metody.getWybraneGUI() instanceof KierownikGUI) {

								pracownik = new Kierownik(email, haslo1, login, nazwisko, imie, Integer.parseInt(wiek),
										osoba.getAdres(), osoba.getSaldoKonta(), pesel);

							} else {

								pracownik = new Pracownik(email, haslo1, login, nazwisko, imie, Integer.parseInt(wiek),
										osoba.getAdres(), osoba.getSaldoKonta(), pesel);

							}
							Metody.getListaOsobZarzadzajacych()
									.set(MenuLogowanie.szukajIDLoginZarzadzajacych(osoba.getLogin()), pracownik);

						}

					} else if (Metody.getWybraneGUI() instanceof KlientGUI) {
						Klient klient;

						klient = new Klient(email, haslo1, login, nazwisko, imie, Integer.parseInt(wiek),
								osoba.getAdres(), osoba.getSaldoKonta(), ((Klient) osoba).getPromocjaKlienta());

						Metody.getListaKlientow().set(MenuLogowanie.szukajIDLoginKlienta(login), klient);
					}
					Metody.setLoginAktywnejOsoby(login);
					JOptionPane.showMessageDialog(frame1, "Dane osobowe zostały zmienione", "Informacja zmianach",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		saveAdresButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String panstwo = txfPanstwo.getText().trim();
				String kodPocztowy = txfKodPocztowy.getText().trim();
				String region = txfRegion.getText().trim();
				String miasto = txfMiasto.getText().trim();
				String ulica = txfUlica.getText().trim();
				String numerBudynku = txfNumerBudynku.getText().trim();

				if (Adres.isValidData(frame1, panstwo, kodPocztowy, region, miasto, ulica, numerBudynku)) {
					osoba.setAdres(new Adres(panstwo, kodPocztowy, region, miasto, ulica, numerBudynku));
					JOptionPane.showMessageDialog(frame1, "Adres zamieszkania został zmieniony", "Informacja zmianach",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	private static JTextField createStyledTextField(String placeholder) {
		JTextField textField = new JTextField(15);

		// Добавляем отступы внутри текстового поля
		textField.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200), 2, true), // Внешняя рамка с
																									// закруглением
				new EmptyBorder(5, 10, 5, 10) // Внутренний отступ

		));

		// textField.setMargin(new Insets(10, 20, 10, 20));

		// Задаем шрифт
		textField.setFont(new Font("Arial", Font.PLAIN, 14));

		// Цвет текста и фона
		textField.setBackground(new Color(245, 245, 245)); // Светло-серый фон
		textField.setForeground(Color.BLACK);
		/*
		 * // Добавляем placeholder (подсказку) textField.setText(placeholder);
		 * textField.setForeground(Color.BLACK);
		 * 
		 * // Сброс текста при фокусе textField.addFocusListener(new
		 * java.awt.event.FocusAdapter() {
		 * 
		 * @Override public void focusGained(java.awt.event.FocusEvent e) { if
		 * (textField.getText().equals(placeholder)) { textField.setText("");
		 * textField.setForeground(Color.BLACK); } }
		 * 
		 * @Override public void focusLost(java.awt.event.FocusEvent e) { if
		 * (textField.getText().isEmpty()) { textField.setText(placeholder);
		 * textField.setForeground(Color.GRAY); } } });
		 */
		return textField;
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
		passwordField.setForeground(Color.BLACK);
		/*
		 * // Установка подсказки (placeholder) passwordField.setText(placeholder);
		 * passwordField.setEchoChar((char) 0); // Убираем звёздочки, чтобы подсказка
		 * была видна
		 * 
		 * // Добавляем слушатели для обработки фокуса
		 * passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
		 * 
		 * @Override public void focusGained(java.awt.event.FocusEvent e) { if (new
		 * String(passwordField.getPassword()).equals(placeholder)) {
		 * passwordField.setText(""); passwordField.setForeground(Color.BLACK);
		 * passwordField.setEchoChar('*'); // Включаем отображение звёздочек } }
		 * 
		 * @Override public void focusLost(java.awt.event.FocusEvent e) { if (new
		 * String(passwordField.getPassword()).isEmpty()) {
		 * passwordField.setText(placeholder); passwordField.setForeground(Color.GRAY);
		 * passwordField.setEchoChar((char) 0); // Скрываем звёздочки для подсказки } }
		 * });
		 */
		return passwordField;
	}

	private void wyswietlInformacje(Osoba osoba) {
		if (osoba != null) {
			txfImie.setText(osoba.getImie());
			txfNazwisko.setText(osoba.getNazwisko());
			txfEmail.setText(osoba.getEmail());
			haslo.setText(osoba.getHaslo());
			txfLogin.setText(osoba.getLogin());
			txfWiek.setText(String.valueOf(osoba.getWiek()));

			if (Metody.getWybraneGUI() instanceof KlientGUI) {
				txfPromocja.setText(Metody.typPromocji(((Klient) osoba).getPromocjaKlienta()));
			} else {
				txfPESEL.setText(((Pracownik) osoba).getPesel());
			}

			if (osoba.getAdres() != null) {
				txfPanstwo.setText(osoba.getAdres().getPanstwo());
				txfKodPocztowy.setText(osoba.getAdres().getKodPocztowy());
				txfRegion.setText(osoba.getAdres().getRegion());
				txfMiasto.setText(osoba.getAdres().getMiasto());
				txfUlica.setText(osoba.getAdres().getUlica());
				txfNumerBudynku.setText(osoba.getAdres().getNumerBudynku());
			} else {
				JOptionPane.showMessageDialog(frame1, "Prosimy o podanie adresu zamieszkania.", "Informacja o koncie",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(frame1, "Brak danych użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
		}
	}

}
