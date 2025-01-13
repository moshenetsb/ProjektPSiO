package strategiaGUI;

import javax.swing.*;
import adres.Adres;
import java.awt.*;
import java.awt.event.*;
import bibliotekaMetodIPol.*;
import produkty.*;
import promocjaStrategia.PromocjaPodstawowa;
import zakupy.Zakupy;

import java.util.ArrayList;
import logowanie.*;
import osoba.Klient;

public class KlientGUI extends WspolneGUI {

	// TODO zrobić klienta (dodawanie produktów do koszyka, wyświetlenie koszyka i
	// kupowanie ze strone koszyka funkcja "KUP" w klasie Klient)

	private JLabel lbSaldoKonta;
	private boolean expanded = false;
	private JPanel contentPanel;
	private JButton toggleButton;
	private String title;
	private JScrollPane scrollPane;
	private ArrayList<Produkty> products = Metody.getListaProduktow();
	private ArrayList<Produkty> productsGaming = new ArrayList<Produkty>();
	private ArrayList<Produkty> productsFotografia = new ArrayList<Produkty>();
	private ArrayList<Produkty> productsMieszane = new ArrayList<Produkty>();

	// Konstruktor
	public KlientGUI(JFrame frame1) {
		super(frame1);
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		sortArrayList();
		contentPanel.add(createKategoria("Gaming", productsGaming));
		System.out.println("Added Category 1");
		contentPanel.add(createKategoria("Fotografia", productsFotografia));
		System.out.println("Added Category 2");
		contentPanel.add(createKategoria("Mieszane", productsMieszane));
		System.out.println("Added Category 3");
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame1.getContentPane().add(contentPanel, BorderLayout.CENTER);
		frame1.add(new JScrollPane(contentPanel));

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
				Metody.updateSaldoKonta(Double.parseDouble(kwotaField.getText()), Metody.getLoginAktywnejOsoby());

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
			if (!Metody.czyWystarczyPieniedzy(Metody.getLoginAktywnejOsoby(), Metody.getLoteria().getWartosc())) {
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

	// _____________________________________________________________
	// TODO przeczytać podalsze i zrobić coś z tym

	private JPanel createKategoria(String title, ArrayList<Produkty> products) {

		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new BorderLayout());
		categoryPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		toggleButton = new JButton(title);
		toggleButton.setFocusPainted(false);
		toggleButton.setContentAreaFilled(false);
		toggleButton.setBorderPainted(false);
		toggleButton.setHorizontalAlignment(SwingConstants.LEFT);

		// Panel zawierający elementy kategorii
		JPanel itemListPanel = new JPanel();
		itemListPanel.setLayout(new BoxLayout(itemListPanel, BoxLayout.Y_AXIS));
		itemListPanel.setVisible(false);
		for (Produkty produkt : products) {
			itemListPanel.add(createItemPanel(produkt.getNazwaProduktu()));

		}

		scrollPane = new JScrollPane(itemListPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(380, 100)); // Fixed height for scrollable area
		scrollPane.setVisible(false); // Initially hidden

		categoryPanel.add(itemListPanel);
		categoryPanel.add(toggleButton, BorderLayout.NORTH);
		// categoryPanel.add(scrollPane, BorderLayout.CENTER);

		toggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				expanded = !expanded;
				itemListPanel.setVisible(expanded);
				// scrollPane.setVisible(expanded);
				toggleButton.setText((expanded ? "▼ " : "► ") + title);
				categoryPanel.revalidate();
				categoryPanel.repaint();
			}
		});
		return categoryPanel;
	}

	private JPanel createItemPanel(String name) {
		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		// TUTAJ SĄ OBRAZY,TRZEBA DODAĆ ICH W BAZĘ DANYCH
		// ImageIcon originalIcon = new
		// ImageIcon(getClass().getResource("/images/images.jpg"));
		// Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100,
		// Image.SCALE_SMOOTH);
		// JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
		JLabel nameLabel = new JLabel(name);
		JButton itemButton = new JButton("Dodaj do koszyka");
		itemButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		itemPanel.add(Box.createVerticalStrut(5));
		// itemPanel.add(iconLabel);
		itemPanel.add(Box.createVerticalStrut(5));
		itemPanel.add(nameLabel);
		itemPanel.add(Box.createVerticalStrut(5));
		itemPanel.add(itemButton);
		itemPanel.add(Box.createVerticalStrut(5));
		itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		return itemPanel;
	}

	private void toggleContent() {
		expanded = !expanded;
		scrollPane.setVisible(expanded);
		toggleButton.setText(getButtonText(title));
		contentPanel.revalidate();
		contentPanel.repaint();

	}

	// Get button text with the correct arrow symbol
	private String getButtonText(String title) {
		this.title = title;
		return (expanded ? "▼ " : "► ") + title;
	}

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

}
