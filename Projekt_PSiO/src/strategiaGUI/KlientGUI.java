package strategiaGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import bibliotekaMetodIPol.*;
import produkty.*;
import java.util.ArrayList;
import logowanie.*;
import osoba.Klient;

public class KlientGUI extends WspolneGUI {

	private JLabel lbSaldoKonta;
	private boolean expanded = false;
	private JPanel contentPanel;
	private JPanel homePanel;
	private JScrollPane scrollPane;
	private ArrayList<Produkty> products = Metody.getListaProduktow();
	private ArrayList<Produkty> productsGaming = new ArrayList<Produkty>();
	private ArrayList<Produkty> productsFotografia = new ArrayList<Produkty>();
	private ArrayList<Produkty> productsMieszane = new ArrayList<Produkty>();
	private ArrayList<Produkty> koszyk = new ArrayList<Produkty>();

	// Konstruktor
	public KlientGUI(JFrame frame1) {
		super(frame1);

		showProducts(frame1);
	}

	private void showProducts(JFrame frame1) {
		
		frame1.getContentPane().removeAll();
		
		JTabbedPane tabPanel = new JTabbedPane();
		homePanel = new JPanel();
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		sortArrayList();

		contentPanel.add(createKategoria("Gaming", productsGaming));
		contentPanel.add(createKategoria("Fotografia", productsFotografia));
		contentPanel.add(createKategoria("Mieszane", productsMieszane));

		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		tabPanel.addTab("Widok Główny", homePanel);
		tabPanel.addTab("Produkty", scrollPane);
		frame1.add(tabPanel, BorderLayout.CENTER);
		
		frame1.revalidate();
		frame1.repaint();
	}
	
	@Override
	public void GUIcreate(JFrame frame1) {
		super.GUIcreate(frame1);
		createManagementMenu(frame1);

	}

	private void pokazKoszyk(JFrame frame1) {
		if (koszyk.isEmpty()) {
			JOptionPane.showMessageDialog(frame1, "Koszyk jest pusty.", "Koszyk", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		JFrame koszykFrame = null;
		for (Window window : Window.getWindows()) {
			if (window instanceof JFrame && ((JFrame) window).getTitle().equals("Koszyk")) {
				koszykFrame = (JFrame) window;
				break;
			}
		}

		if (koszykFrame == null) {
			koszykFrame = new JFrame("Koszyk");
			koszykFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			koszykFrame.setSize(700, 700);
		}

		try {
			koszykFrame.setIconImage(ImageIO.read(new File("Grafika/koszyk.jpg")));
		} catch (Exception e) {
			System.err.println("Błąd podczas wczytywania ikony: " + e.getMessage());
		}

		JPanel koszykPanel = new JPanel();
		koszykPanel.setLayout(new BoxLayout(koszykPanel, BoxLayout.Y_AXIS));
		koszykPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		final JFrame finalKoszykFrame = koszykFrame;

		for (Produkty produkt : koszyk) {
			JPanel produktPanel = new JPanel();
			produktPanel.setLayout(new BoxLayout(produktPanel, BoxLayout.X_AXIS));
			produktPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			produktPanel.setPreferredSize(new Dimension(600, 150));
			produktPanel.setMaximumSize(new Dimension(600, 150));

			try {
				File file = new File(produkt.getSciezkaObrazu());
				ImageIcon originalIcon = new ImageIcon(ImageIO.read(file));

				Image image = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel obrazekLabel = new JLabel(new ImageIcon(image));
				obrazekLabel.setHorizontalAlignment(SwingConstants.LEFT);
				obrazekLabel.setPreferredSize(new Dimension(100, 100));
				produktPanel.add(obrazekLabel);
			} catch (IOException e) {
				e.printStackTrace();
				JLabel obrazekLabel = new JLabel(new ImageIcon("domyslny_obrazek.jpg"));
				obrazekLabel.setHorizontalAlignment(SwingConstants.LEFT);
				obrazekLabel.setPreferredSize(new Dimension(100, 100));
				produktPanel.add(obrazekLabel);
			}

			JPanel tekstPanel = new JPanel();
			tekstPanel.setLayout(new BoxLayout(tekstPanel, BoxLayout.Y_AXIS));
			tekstPanel.setPreferredSize(new Dimension(300, 100));

			JLabel nazwaLabel = new JLabel("Nazwa: " + produkt.getNazwaProduktu());
			nazwaLabel.setFont(new Font("Arial", Font.BOLD, 14));
			nazwaLabel.setPreferredSize(new Dimension(300, 20));
			nazwaLabel.setMaximumSize(new Dimension(300, 20));
			tekstPanel.add(nazwaLabel);

			JLabel cenaLabel = new JLabel("Cena: " + produkt.getCenaProduktu() + " PLN");
			cenaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			tekstPanel.add(cenaLabel);

			produktPanel.add(tekstPanel);

			JTextArea opisText = new JTextArea(3, 30);
			opisText.setText(produkt.getOpisProduktu());
			opisText.setWrapStyleWord(true);
			opisText.setLineWrap(true);
			opisText.setCaretPosition(0);
			opisText.setEditable(false);
			opisText.setFont(new Font("Arial", Font.ITALIC, 12));

			opisText.setPreferredSize(new Dimension(350, 60));
			opisText.setMaximumSize(new Dimension(350, 100));
			opisText.setMinimumSize(new Dimension(350, 60));

			JScrollPane opisScroll = new JScrollPane(opisText);
			opisScroll.setPreferredSize(new Dimension(350, 100));
			opisScroll.setMaximumSize(new Dimension(350, 100));

			produktPanel.add(opisScroll);

			JButton usunButton = new JButton(new ImageIcon("Grafika/x.jpg"));
			usunButton.setPreferredSize(new Dimension(40, 40));
			usunButton.setFont(new Font("Arial", Font.BOLD, 16));
			usunButton.addActionListener(e -> {
				koszyk.remove(produkt);
				if (koszyk.isEmpty()) {
					finalKoszykFrame.dispose();
					JOptionPane.showMessageDialog(frame1, "Koszyk jest pusty.", "Koszyk",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					koszykPanel.removeAll();
					pokazKoszyk(frame1);
				}
			});

			JPanel usunPanel = new JPanel();
			usunPanel.setLayout(new BoxLayout(usunPanel, BoxLayout.Y_AXIS));
			usunPanel.add(usunButton);
			produktPanel.add(usunPanel);

			koszykPanel.add(produktPanel);
		}

		JButton kupButton = new JButton("Kup");
		kupButton.setPreferredSize(new Dimension(120, 40));
		kupButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(frame1, "Zakup został dokonany.", "Zakup", JOptionPane.INFORMATION_MESSAGE);
			koszyk.clear();
			finalKoszykFrame.dispose();
		});

		JButton kontynuujButton = new JButton("Kontynuuj zakupy");
		kontynuujButton.setPreferredSize(new Dimension(180, 40));
		kontynuujButton.addActionListener(e -> {
			finalKoszykFrame.setVisible(false);
			frame1.setVisible(true);
			try {
				frame1.setIconImage(ImageIO.read(new File("./Grafika/dolarZielony.png")));
			} catch (Exception e1) {
				System.err.println("Błąd podczas wczytywania ikony: " + e1.getMessage());
			}
		});

		JPanel przyciskiPanel = new JPanel();
		przyciskiPanel.add(kupButton);
		przyciskiPanel.add(kontynuujButton);

		koszykPanel.add(przyciskiPanel);

		koszykFrame.getContentPane().removeAll();
		koszykFrame.add(new JScrollPane(koszykPanel));
		koszykFrame.revalidate();
		koszykFrame.repaint();
		koszykFrame.setVisible(true);
	}

	private void createManagementMenu(JFrame frame1) {
		JMenuBar menuBar = frame1.getJMenuBar();

		JMenu mnProdukty = new JMenu("Produkty");
		menuBar.add(mnProdukty);

		JMenuItem mntmWszystkieProdukty = new JMenuItem("Wszystkie produkty");
		mnProdukty.add(mntmWszystkieProdukty);
		mntmWszystkieProdukty.addActionListener(e -> showProducts(frame1));

		JMenu mnKoszyk = new JMenu("Koszyk");
		menuBar.add(mnKoszyk);

		JMenuItem mntmPokazKoszyk = new JMenuItem("Pokaż koszyk");
		mnKoszyk.add(mntmPokazKoszyk);
		mntmPokazKoszyk.addActionListener(e -> pokazKoszyk(frame1));

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
				Klient klient = Metody.getListaKlientow()
						.get(MenuLogowanie.szukajIDLoginKlienta(Metody.getLoginAktywnejOsoby()));
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
			Klient klient = Metody.getListaKlientow()
					.get(MenuLogowanie.szukajIDLoginKlienta(Metody.getLoginAktywnejOsoby()));
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
		// itemListPanel.setBackground(Color.BLACK);
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
		// itemPanel.setBackground(Color.DARK_GRAY);

		ImageIcon originalIcon = null;
		try {
			originalIcon = new ImageIcon(ImageIO.read(new File(produkt.getSciezkaObrazu())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		originalIcon.getImage();
		Image scaledImage = originalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
		JLabel nameLabel = new JLabel(produkt.getNazwaProduktu());
		JButton itemButton = new JButton("Dodaj do koszyka");
		itemButton.addActionListener(e -> koszyk.add(produkt));

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
