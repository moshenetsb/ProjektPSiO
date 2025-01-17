package strategiaGUI;

import javax.imageio.ImageIO;

import javax.swing.*;

import Obserwator.Observer;
import adres.Adres;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import bibliotekaMetodIPol.*;
import produkty.*;
import promocjaStrategia.PromocjaPodstawowa;
import zakupy.Zakupy;

import java.util.ArrayList;
import logowanie.*;
import osoba.Klient;
import osoba.Osoba;

public class KlientGUI extends WspolneGUI implements Observer {

	// TODO zrobić klienta (dodawanie produktów do koszyka, wyświetlenie koszyka i
	// kupowanie ze strone koszyka funkcja "KUP" w klasie Klient)

	private JLabel lbSaldoKonta;
	private boolean expanded = false;
	private JPanel contentPanel;
	private JPanel homePanel;
	private JScrollPane scrollPane;
	private ArrayList<Produkty> products = Metody.getListaProduktow();
	private ArrayList<Produkty> productsGaming = new ArrayList<Produkty>();
	private ArrayList<Produkty> productsFotografia = new ArrayList<Produkty>();
	private ArrayList<Produkty> productsMieszane = new ArrayList<Produkty>();
	// private Klient klient;
	// private ArrayList<Produkty> koszyk = klient.getKoszyk().getListaProduktow();

	// Konstruktor
	public KlientGUI(JFrame frame1) {
		super(frame1);

		JTabbedPane tabPanel = new JTabbedPane();

		homePanel = new JPanel();
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		// contentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		sortArrayList();

		homePanel.add(createSingleItemSection("Wysprzedaż", productsGaming));

		contentPanel.add(createKategoria("Gaming", productsGaming));
		contentPanel.add(createKategoria("Fotografia", productsFotografia));
		contentPanel.add(createKategoria("Mieszane", productsMieszane));

		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		tabPanel.addTab("Widok Główny", homePanel);
		tabPanel.addTab("Produkty", scrollPane);
		frame1.add(tabPanel, BorderLayout.CENTER);
		// frame1.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void GUIcreate(JFrame frame1) {
		super.GUIcreate(frame1);
		createManagementMenu(frame1);

		// TODO Auto-generated method stub

	}

	private void createManagementMenu(JFrame frame1) {
		JMenuBar menuBar = frame1.getJMenuBar();

		JMenu mnProdukty = new JMenu("Produkty");
		menuBar.add(mnProdukty);

		JMenuItem mntmWszystkieProdukty = new JMenuItem("Wszystkie produkty");
		mnProdukty.add(mntmWszystkieProdukty);

		JMenuItem mntmUlubioneProdukty = new JMenuItem("Ulubione produkty");
		mnProdukty.add(mntmUlubioneProdukty);

		JMenu mnKoszyk = new JMenu("Koszyk");
		menuBar.add(mnKoszyk);

		JMenuItem mntmPokazKoszyk = new JMenuItem("Pokaż koszyk");
		mnKoszyk.add(mntmPokazKoszyk);
		// mntmPokazKoszyk.addActionListener(e -> pokazKoszyk(frame1));

		JMenu mnKonto = new JMenu("Konto");
		menuBar.add(mnKonto);

		lbSaldoKonta = new JLabel();
		refreshSaldoKonta(lbSaldoKonta);
		mnKonto.add(lbSaldoKonta);

		JMenuItem mntmDoladujKonto = new JMenuItem("Doładuj konto");
		mnKonto.add(mntmDoladujKonto);
		mntmDoladujKonto.addActionListener(e -> doladujKonto(frame1));

		JMenuItem mntmLoteria = new JMenuItem("Loteria");
		mnKonto.add(mntmLoteria);
		mntmLoteria.addActionListener(e -> loteria(frame1));
	}

	private void doladujKonto(JFrame frame1) {
		JTextField kwotaField = new JTextField(10);
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(new JLabel("Kwota doładowania:"));
		panel.add(kwotaField);

		int result = JOptionPane.showConfirmDialog(frame1, panel, "Doładowanie konta", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			try {
				Klient klient = Metody.getListaKlientow().get(MenuLogowanie.szukajIDLoginKlienta(Metody.getLoginAktywnejOsoby()));
				klient.updateSaldoKonta(Double.parseDouble(kwotaField.getText()));

				refreshSaldoKonta(lbSaldoKonta);

				JOptionPane.showMessageDialog(frame1, "Konto zostało doładowane pomyślnie. Dziękujemy!",
						"Info doładowania", JOptionPane.INFORMATION_MESSAGE);

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame1, "Kwota musi być liczbą!", "Błąd formatu",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void loteria(JFrame frame1) {
		if (loteriaCzyChceszGrac(frame1)) {
			graWLoterie(frame1);
			refreshSaldoKonta(lbSaldoKonta);

		}

	}

	private boolean loteriaCzyChceszGrac(JFrame frame1) {
		JPanel panel = new JPanel(new GridLayout(4, 1));
		panel.add(new JLabel("Witamy w naszej loterii!"));
		panel.add(new JLabel("Nasza maszyna wylosuje liczbę od " + Metody.getLoteria().getMinLiczba() + " do "
				+ Metody.getLoteria().getMaxLiczba() + ", jeśli zgadniesz ją, to otrzymasz "
				+ Metody.getLoteria().getSumaDoWygrania() + " PLN."));
		panel.add(new JLabel("Jedna gra kosztuje " + Metody.getLoteria().getWartosc() + " PLN."));
		panel.add(new JLabel("Chcesz zagrać?"));

		int result = JOptionPane.showConfirmDialog(frame1, panel, "Loteria", JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == JOptionPane.YES_OPTION) {

			// Sprawdzamy, czy wystarczy pieniędzy
			Klient klient = Metody.getListaKlientow().get(MenuLogowanie.szukajIDLoginKlienta(Metody.getLoginAktywnejOsoby()));
			if (!klient.czyWystarczyPieniedzy(Metody.getLoteria().getWartosc())) {
				JOptionPane.showMessageDialog(frame1, "Brakuje pieniędzy dla gry! Doładuj konto i wróć.", "Loteria",
						JOptionPane.ERROR_MESSAGE);
				return false;

			} else
				return true;
		}
		return false;
	}

	private void graWLoterie(JFrame frame1) {

		JTextField liczbaField = new JTextField(10);
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(new JLabel("Zgadnij liczbę od " + Metody.getLoteria().getMinLiczba() + " do "
				+ Metody.getLoteria().getMaxLiczba() + ":"));
		panel.add(liczbaField);

		int result = JOptionPane.showConfirmDialog(frame1, panel, "Loteria", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			try {
				int liczbaUzytkownika = Integer.parseInt(liczbaField.getText());
				Metody.getLoteria().grajLoteria(frame1, liczbaUzytkownika, Metody.getLoginAktywnejOsoby());

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame1,
						"Błędny format liczby! Pieniądze zostały zwrócone. Spróbuj jeszcze raz.", "Błąd formatu",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void refreshSaldoKonta(JLabel lbSaldoKonta) {
		double saldoKonta = Metody.getListaKlientow()
				.get(MenuLogowanie.szukajIDLoginKlienta(Metody.getLoginAktywnejOsoby())).getSaldoKonta();
		String saldoString = String.valueOf(Math.round(saldoKonta * 100) / 100.0);
		lbSaldoKonta.setText(" Saldo konta: " + saldoString + " PLN");
	}

	private JPanel GlownyWidok() {
		JPanel znizkiPanel = new JPanel();
		znizkiPanel.setLayout(new BorderLayout());
		znizkiPanel = createSingleItemSection("Wysprzedaż", productsGaming);
		return znizkiPanel;
	}

	private JPanel createSingleItemSection(String title, ArrayList<Produkty> products) {
		JPanel section = new JPanel(new BorderLayout());

		JLabel sectionTitle = new JLabel(title, JLabel.LEFT);
		sectionTitle.setFont(new Font("Arial", Font.BOLD, 18));
		section.add(sectionTitle, BorderLayout.NORTH);

		// Panel do wyświetlania jednego elementu
		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		itemPanel.setPreferredSize(new Dimension(600, 200));

		Produkty firstProdukt = products.get(0);

		JLabel itemImageLabel = new JLabel();
		itemImageLabel.setPreferredSize(new Dimension(150, 150));
		itemImageLabel.setIcon(scaleImage(new ImageIcon(firstProdukt.getSciezkaObrazu()), 150, 150));
		itemPanel.add(itemImageLabel);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

		JLabel itemNameLabel = new JLabel(firstProdukt.getNazwaProduktu());
		itemNameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		JLabel itemPriceLabel = new JLabel("Cena: " + firstProdukt.getCenaProduktu() + " PLN");
		itemPriceLabel.setForeground(Color.RED);
		itemPriceLabel.setFont(new Font("Arial", Font.PLAIN, 18));

		JButton buyButton = new JButton("Kup");

		textPanel.add(itemNameLabel);
		textPanel.add(itemPriceLabel);
		textPanel.add(buyButton);
		itemPanel.add(textPanel);

		section.add(itemPanel, BorderLayout.CENTER);

		// Nawigacja za pomocą strzałek
		JButton leftArrow = new JButton("<");
		JButton rightArrow = new JButton(">");

		int[] currentIndex = { 0 };

		// Akcja dla lewej strzałki
		leftArrow.addActionListener(e -> {
			currentIndex[0] = (currentIndex[0] - 1 + products.size()) % products.size();
			updateProductDisplay(products.get(currentIndex[0]), itemImageLabel, itemNameLabel, itemPriceLabel);
		});
		// Akcja dla prawej strzałki
		rightArrow.addActionListener(e -> {
			currentIndex[0] = (currentIndex[0] + 1 + products.size()) % products.size();
			updateProductDisplay(products.get(currentIndex[0]), itemImageLabel, itemNameLabel, itemPriceLabel);
		});

		// Dodanie strzałek nawigacyjnych
		JPanel arrowsPanel = new JPanel(new BorderLayout());
		arrowsPanel.add(leftArrow, BorderLayout.WEST);
		arrowsPanel.add(rightArrow, BorderLayout.EAST);
		section.add(arrowsPanel, BorderLayout.SOUTH);

		return section;
	}

	private void updateProductDisplay(Produkty produkt, JLabel imageLabel, JLabel nameLabel, JLabel priceLabel) {
		nameLabel.setText(produkt.getNazwaProduktu());
		priceLabel.setText("Cena: " + produkt.getCenaProduktu() + " PLN");
		imageLabel.setIcon(scaleImage(new ImageIcon(produkt.getSciezkaObrazu()), 150, 150));
	}

	private static ImageIcon scaleImage(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Skalowanie obrazu
		return new ImageIcon(scaledImage);
	}

	// _____________________________________________________________
	// TODO przeczytać podalsze i zrobić coś z tym

	private JPanel createKategoria(String title, ArrayList<Produkty> products) {
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new BorderLayout());
		;

		JButton toggleButton = new JButton("▼ " + title);
		toggleButton.setFocusPainted(false);
		toggleButton.setContentAreaFilled(false);
		toggleButton.setBorderPainted(true);
		toggleButton.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel itemListPanel = new JPanel();
		itemListPanel.setLayout(new GridLayout(0, 3, 10, 10));
		itemListPanel.setVisible(false);
		itemListPanel.setBackground(Color.BLACK);
		for (Produkty produkt : products) {
			itemListPanel.add(createItemPanel(produkt));
		}

		scrollPane = new JScrollPane(itemListPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(380, 100));
		scrollPane.setVisible(false);

		categoryPanel.add(itemListPanel);
		categoryPanel.add(toggleButton, BorderLayout.NORTH);

		toggleButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				expanded = !expanded;
				itemListPanel.setVisible(expanded);
				if (expanded)
					toggleButton.setText("► " + title);
				else
					toggleButton.setText("▼ " + title);
				toggleButton.revalidate();
				toggleButton.repaint();
				itemListPanel.revalidate();
				itemListPanel.repaint();
			}
		});
		return categoryPanel;
	}

	private JPanel createItemPanel(Produkty produkt) {
		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		itemPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		itemPanel.setBackground(Color.DARK_GRAY);

		ImageIcon originalIcon = null;
		originalIcon = new ImageIcon(produkt.getSciezkaObrazu());
		originalIcon.getImage();
		Image scaledImage = originalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
		JLabel nameLabel = new JLabel(produkt.getNazwaProduktu());
		JButton itemButton = new JButton("Dodaj do koszyka");
		// itemButton.addActionListener(e -> koszyk.add(produkt));

		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		itemButton.setAlignmentX(Component.LEFT_ALIGNMENT);

		itemPanel.add(iconLabel);
		itemPanel.add(Box.createVerticalStrut(5));
		itemPanel.add(nameLabel);
		itemPanel.add(Box.createVerticalStrut(5));
		itemPanel.add(itemButton);
		itemPanel.add(Box.createVerticalStrut(5));
		itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		return itemPanel;
	}

	/*
	 * private void pokazKoszyk(JFrame frame1) { if (koszyk.isEmpty()) {
	 * JOptionPane.showMessageDialog(frame1, "Koszyk jest pusty.", "Koszyk",
	 * JOptionPane.INFORMATION_MESSAGE); } else { StringBuilder zawartosc = new
	 * StringBuilder("Zawartość koszyka:\n\n"); for (Produkty produkt : koszyk) {
	 * zawartosc.append(produkt.getNazwaProduktu()).append("\n"); }
	 * 
	 * JButton kupButton = new JButton("Kup");
	 * 
	 * kupButton.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { klient.kup(frame1);
	 * koszyk.clear(); } }); JOptionPane.showMessageDialog(frame1,
	 * zawartosc.toString(), "Koszyk", JOptionPane.INFORMATION_MESSAGE); } }
	 * 
	 */

	private void sortArrayList() {
		for (Produkty produkt : products) {
			if (produkt instanceof Gaming) {
				productsGaming.add(produkt);
			} else if (produkt instanceof Fotografia) {
				productsFotografia.add(produkt);
			} else if (produkt instanceof Mieszane) {
				productsMieszane.add(produkt);
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
