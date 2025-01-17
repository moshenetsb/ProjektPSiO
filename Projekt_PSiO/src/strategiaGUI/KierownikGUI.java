package strategiaGUI;

import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import bibliotekaMetodIPol.*;
import logowanie.MenuLogowanie;
import osoba.*;
import promocjaStrategia.*;
import zakupy.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import inneGUI.GUImain;

public class KierownikGUI extends PracownikGUI {

	private JFrame frame1;
	private ArrayList<Klient> listaKlientow;
	private ArrayList<Pracownik> listaPracownikow;
	private DefaultTableModel klientTableModel;
	private DefaultTableModel pracownikTableModel;
	private Osoba osoba;

	public KierownikGUI(JFrame frame1) {
		super(frame1);
		this.frame1 = frame1;

		listaKlientow = Metody.getListaKlientow();
		listaPracownikow = Metody.getListaOsobZarzadzajacych();

		kierownikIcon();

	}

	@Override
	public void GUIcreate(JFrame frame1) {
		super.GUIcreate(frame1);

		createManagementMenu(frame1);
	}

	private void createManagementMenu(JFrame frame1) {
		JMenuBar menuBar = frame1.getJMenuBar();
		if (menuBar == null) {
			menuBar = new JMenuBar();
			frame1.setJMenuBar(menuBar);
		}

		JMenu toolsMenu = new JMenu("Zarządzanie kontami");
		JMenu clientsSubmenu = new JMenu("Klienci");
		JMenu employeesSubmenu = new JMenu("Pracownicy");

		// Opcje dla klientów
		JMenuItem manageClients = new JMenuItem("Zarządzaj klientami");
		manageClients.addActionListener(e -> {
			manageClients.setEnabled(false);
			showClientManagement(manageClients);
		});

		JMenuItem searchClients = new JMenuItem("Wyszukaj klientów");
		searchClients.addActionListener(e -> {
			if (listaKlientow.isEmpty())
				JOptionPane.showMessageDialog(frame1, "Lista klientów jest pusta", "Informacja wyszukiwania",
						JOptionPane.INFORMATION_MESSAGE);
			else {
				this.initializeKlientTableModel();
				showSearch(klientTableModel, searchClients, listaKlientow);
			}

		});

		clientsSubmenu.add(manageClients);
		clientsSubmenu.add(searchClients);

		// Opcje dla pracowników
		JMenuItem manageEmployees = new JMenuItem("Zarządzaj pracownikami");
		manageEmployees.addActionListener(e -> {
			manageEmployees.setEnabled(false);
			showEmployeeManagement(manageEmployees);
		});

		JMenuItem searchEmployees = new JMenuItem("Wyszukaj pracowników");
		searchEmployees.addActionListener(e -> {
			if (listaPracownikow.isEmpty())
				JOptionPane.showMessageDialog(frame1, "Lista pracowników jest pusta", "Informacja wyszukiwania",
						JOptionPane.INFORMATION_MESSAGE);
			else {
				this.initializePracownikTableModel();
				showSearch(pracownikTableModel, searchEmployees, listaPracownikow);
			}

		});

		employeesSubmenu.add(manageEmployees);
		employeesSubmenu.add(searchEmployees);

		toolsMenu.add(clientsSubmenu);
		toolsMenu.add(employeesSubmenu);
		menuBar.add(toolsMenu);
	}

	private void initializeKlientTableModel() {
		String[] clientColumnNames = { "Imię", "Nazwisko", "Login", "Email", "Hasło", "Saldo Konta", "Wiek",
				"Typ promocji" };
		klientTableModel = new DefaultTableModel(clientColumnNames, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

	}

	private void initializePracownikTableModel() {

		String[] employeeColumnNames = { "Imię", "Nazwisko", "Login", "Email", "Hasło", "Saldo Konta", "Wiek",
				"PESEL" };
		pracownikTableModel = new DefaultTableModel(employeeColumnNames, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

	}

	private void showClientManagement(JMenuItem manageClients) {
		JFrame clientFrame = new JFrame("Zarządzanie Klientami");
		clientFrame.setSize(1100, 600);
		clientFrame.setLayout(new BorderLayout());

		toolIcon(clientFrame);

		this.initializeKlientTableModel();

		JTable klientTable = new JTable(klientTableModel);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Dodaj");
		JButton removeButton = new JButton("Usuń");
		JButton editButton = new JButton("Edytuj");
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(editButton);

		addButton.addActionListener(e -> addOrEditAccount(Klient.class, clientFrame, false, klientTable));
		removeButton.addActionListener(e -> deleteAccount(klientTable, clientFrame));
		editButton.addActionListener(e -> addOrEditAccount(Klient.class, clientFrame, true, klientTable));

		refreshTable(klientTableModel, listaKlientow);

		clientFrame.add(new JScrollPane(klientTable), BorderLayout.CENTER);
		clientFrame.add(buttonPanel, BorderLayout.SOUTH);

		clientFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				manageClients.setEnabled(true);
			}
		});

		clientFrame.setVisible(true);
	}

	private void addOrEditAccount(Class<?> typ, JFrame frame, boolean czyEdytowanie, JTable table) {

		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1 && czyEdytowanie) {
			JOptionPane.showMessageDialog(frame, "Nie wybrano konto dla edytowania!", "Informacja edytowania",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (typ.equals(Pracownik.class)) {
			if (listaPracownikow.get(selectedRow) instanceof Kierownik) {
				JOptionPane.showMessageDialog(frame, "Nie można edtować informacji o kierowniku w ten sposób!",
						"Informacja edytowania", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}

		JDialog accountDialog = new JDialog(frame, "", true);

		JTextField imieField = new JTextField(10);
		JTextField nazwiskoField = new JTextField(10);
		JTextField loginField = new JTextField(10);
		JTextField emailField = new JTextField(10);
		JPasswordField hasloField = new JPasswordField(10);
		hasloField.setEchoChar('*');
		GUImain.ustawWyswietlelieHasla(hasloField);
		JTextField saldoField = new JTextField(10);

		JTextField wiekField = new JTextField(10);

		JTextField peselField = new JTextField(10);
		JComboBox<String> promocjaComboBox = new JComboBox<>(
				new String[] { "Podstawowa", "Stały klient", "Studencka" });

		JPanel panel = new JPanel(new GridLayout(16, 1));

		panel.add(new JLabel("Imię:"));
		panel.add(imieField);
		panel.add(new JLabel("Nazwisko:"));
		panel.add(nazwiskoField);
		panel.add(new JLabel("Login:"));
		panel.add(loginField);
		panel.add(new JLabel("Email:"));
		panel.add(emailField);
		panel.add(new JLabel("Hasło:"));
		panel.add(hasloField);
		panel.add(new JLabel("Saldo konta:"));
		panel.add(saldoField);
		panel.add(new JLabel("Wiek:"));
		panel.add(wiekField);

		JPanel btnPanel = new JPanel(new FlowLayout());
		JButton btnAdd = new JButton();

		btnPanel.add(btnAdd);

		if (typ.equals(Pracownik.class)) {
			if (czyEdytowanie) {
				osoba = listaPracownikow.get(selectedRow);
				accountDialog.setTitle("Edytuj Pracownika");
				peselField.setText(((Pracownik) osoba).getPesel());
			} else
				accountDialog.setTitle("Dodaj Pracownika");
			panel.add(new JLabel("PESEL:"));
			panel.add(peselField);

		} else if (typ.equals(Klient.class)) {
			if (czyEdytowanie) {
				osoba = listaKlientow.get(selectedRow);

				Promocja promocja = ((Klient) osoba).getPromocjaKlienta();
				if (promocja instanceof PromocjaPodstawowa)
					promocjaComboBox.setSelectedItem("Podstawowa");

				else if (promocja instanceof PromocjaStalegoKlienta)
					promocjaComboBox.setSelectedItem("Stały klient");
				else
					promocjaComboBox.setSelectedItem("Studencka");

				accountDialog.setTitle("Edytuj Klienta");

			} else
				accountDialog.setTitle("Dodaj Klienta");

			panel.add(new JLabel("Typ promocji:"));
			panel.add(promocjaComboBox);
		}

		if (czyEdytowanie) {

			wypelnienieWspolneInfo(imieField, nazwiskoField, loginField, emailField, hasloField, saldoField, wiekField,
					osoba);
			btnAdd.setText("Edytuj");
		} else {
			btnAdd.setText("Dodaj");
			wiekField.setText("0");
			saldoField.setText("0");
		}
		panel.setBorder(new EmptyBorder(0, 10, 0, 10));
		accountDialog.add(BorderLayout.CENTER, panel);
		accountDialog.add(BorderLayout.SOUTH, btnPanel);

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText().trim();
				String haslo = new String(hasloField.getPassword()).trim();
				String login = loginField.getText().trim();
				String nazwisko = nazwiskoField.getText().trim();
				String imie = imieField.getText().trim();
				String wiek = wiekField.getText().trim();
				String saldoKonta = saldoField.getText().trim();

				if (isValidDataSaldoKonta(frame, saldoKonta)
						&& Metody.isValidData(frame, email, haslo, login, nazwisko, imie, wiek, true, osoba)) {

					if (typ.equals(Pracownik.class)) {
						String pesel = peselField.getText().trim();

						if (isValidDataPracownik(frame, pesel)) {
							Pracownik pracownik;

							if (czyEdytowanie) {
								pracownik = new Pracownik(email, haslo, login, nazwisko, imie, Integer.parseInt(wiek),
										osoba.getAdres(), Double.parseDouble(saldoKonta), pesel);
								listaPracownikow.set(selectedRow, pracownik);

							} else {
								pracownik = new Pracownik(email, haslo, login, nazwisko, imie, Integer.parseInt(wiek),
										null, Double.parseDouble(saldoKonta), pesel);
								listaPracownikow.add(pracownik);
							}
							accountDialog.dispose();
							refreshTable(pracownikTableModel, listaPracownikow);
						}

					} else if (typ.equals(Klient.class)) {

						Promocja promocja;
						String selectedPromocja = (String) promocjaComboBox.getSelectedItem();
						if (selectedPromocja.equals("Podstawowa")) {
							promocja = new PromocjaPodstawowa();
						} else if (selectedPromocja.equals("Stały klient")) {
							promocja = new PromocjaStalegoKlienta();
						} else {
							promocja = new PromocjaStudenta();
						}

						Klient klient;

						if (czyEdytowanie) {
							klient = new Klient(email, haslo, login, nazwisko, imie, Integer.parseInt(wiek),
									osoba.getAdres(), Double.parseDouble(saldoKonta), promocja);
							listaKlientow.set(selectedRow, klient);
						} else {
							klient = new Klient(email, haslo, login, nazwisko, imie, Integer.parseInt(wiek), null,
									Double.parseDouble(saldoKonta), promocja, new ArrayList<>(), new Zakupy());
							listaKlientow.add(klient);
						}

						accountDialog.dispose();
						refreshTable(klientTableModel, listaKlientow);
					}
				}
			}
		});

		accountDialog.setSize(300, 500);
		accountDialog.setVisible(true);
	}

	private void wypelnienieWspolneInfo(JTextField imieField, JTextField nazwiskoField, JTextField loginField,
			JTextField emailField, JPasswordField hasloField, JTextField saldoField, JTextField wiekField,
			Osoba osoba) {
		imieField.setText(osoba.getImie());
		nazwiskoField.setText(osoba.getNazwisko());
		loginField.setText(osoba.getLogin());
		emailField.setText(osoba.getEmail());
		hasloField.setText(osoba.getHaslo());
		saldoField.setText(String.valueOf(Math.round(osoba.getSaldoKonta() * 100) / 100.0));
		wiekField.setText(String.valueOf(osoba.getWiek()));
	}

	private boolean isValidDataPracownik(JFrame frame, String pesel) {
		if (pesel.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Wszystkie pola muszą być wypełnione!", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (pesel.matches("\\d{11}")) {
			JOptionPane.showMessageDialog(frame, "PESEL musi zawierać 11 cyfr", "Błąd", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private boolean isValidDataSaldoKonta(JFrame frame, String saldo) {
		if (saldo.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Pole saldo konta musi być wypełnione!", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Double.parseDouble(saldo);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Saldo konta musi być liczbą!", "Błąd", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void deleteAccount(JTable table, JFrame frame) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			int confirm = JOptionPane.showConfirmDialog(frame, "Czy na pewno chcesz usunąć konto tej osoby?",
					"Potwierdzenie usunięcia", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				if (frame.getTitle().equals("Zarządzanie Klientami")) {
					listaKlientow.remove(selectedRow);
					refreshTable(klientTableModel, listaKlientow);
				} else {
					Pracownik wybranyPracownik = listaPracownikow.get(selectedRow);
					Pracownik aktywnyPracownik = listaPracownikow
							.get(MenuLogowanie.szukajIDLoginZarzadzajacych(Metody.getLoginAktywnejOsoby()));

					if (wybranyPracownik.getLogin().equals(aktywnyPracownik.getLogin()))
						JOptionPane.showMessageDialog(frame, "Nie można usunąć swojego konta!", "Błąd usunięcia",
								JOptionPane.ERROR_MESSAGE);
					else {
						listaPracownikow.remove(selectedRow);
						refreshTable(pracownikTableModel, listaPracownikow);
					}
				}

			}

		} else {
			JOptionPane.showMessageDialog(frame, "Nie wybrano konto do usunięcia!", "Informacja usunięcia",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void showEmployeeManagement(JMenuItem showEmployee) {
		JFrame employeeFrame = new JFrame("Zarządzanie Pracownikami");
		employeeFrame.setSize(1100, 600);
		employeeFrame.setLayout(new BorderLayout());

		toolIcon(employeeFrame);

		this.initializePracownikTableModel();

		JTable pracownikTable = new JTable(pracownikTableModel);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Dodaj");
		JButton removeButton = new JButton("Usuń");
		JButton editButton = new JButton("Edytuj");
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(editButton);

		addButton.addActionListener(e -> addOrEditAccount(Pracownik.class, employeeFrame, false, pracownikTable));
		removeButton.addActionListener(e -> deleteAccount(pracownikTable, employeeFrame));
		editButton.addActionListener(e -> addOrEditAccount(Pracownik.class, employeeFrame, true, pracownikTable));

		refreshTable(pracownikTableModel, listaPracownikow);

		employeeFrame.add(new JScrollPane(pracownikTable), BorderLayout.CENTER);
		employeeFrame.add(buttonPanel, BorderLayout.SOUTH);

		employeeFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				showEmployee.setEnabled(true);
			}
		});

		employeeFrame.setVisible(true);
	}

	// Wyszukiwanie osób
	private <T> void showSearch(DefaultTableModel searchTableModel, JMenuItem mntmSearch, ArrayList<T> lista) {

		mntmSearch.setEnabled(false);

		JFrame searchFrame = new JFrame();

		T firstElement = lista.get(0);
		if (firstElement instanceof Pracownik) {
			searchFrame.setTitle("Wyszukiwanie Pracowników");
		} else {
			searchFrame.setTitle("Wyszukiwanie Klientów");
		}

		searchFrame.setSize(1100, 600);

		toolIcon(searchFrame);

		JPanel searchPanel = new JPanel(new FlowLayout());
		JTextField searchField = new JTextField(30);

		searchField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				onTextChanged();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				onTextChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				onTextChanged();
			}

			// Metoda wywoływana po każdej zmianie tekstu
			private void onTextChanged() {
				search(searchField.getText(), searchTableModel, lista);
			}
		});

		searchPanel.add(new JLabel("Wprowadź kryteria (email, login, nazwisko):"));
		searchPanel.add(searchField);
		searchPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

		JTable searchTable = new JTable(searchTableModel);

		searchFrame.add(searchPanel, BorderLayout.NORTH);
		searchFrame.add(new JScrollPane(searchTable), BorderLayout.CENTER);

		searchFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				mntmSearch.setEnabled(true);
			}
		});

		searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		searchFrame.setVisible(true);
	}

	private <T> void search(String criteria, DefaultTableModel tableModel, ArrayList<T> lista) {
		tableModel.setRowCount(0);

		for (T osoba : lista) {
			if (czySpelniaKryteria(criteria, (Osoba) osoba)) {

				if (osoba instanceof Pracownik) {
					Pracownik pracownik = (Pracownik) osoba;
					tableModel.addRow(new Object[] { pracownik.getImie(), pracownik.getNazwisko(), pracownik.getLogin(),
							pracownik.getEmail(), pracownik.getHaslo(), pracownik.getSaldoKonta(), pracownik.getWiek(),
							pracownik.getPesel() });
				} else if (osoba instanceof Klient) {
					Klient klient = (Klient) osoba;
					String promocja = typPromocji(klient.getPromocjaKlienta());

					tableModel.addRow(new Object[] { klient.getImie(), klient.getNazwisko(), klient.getLogin(),
							klient.getEmail(), klient.getHaslo(), klient.getSaldoKonta(), klient.getWiek(), promocja });
				}
			}

		}

	}

	private boolean czySpelniaKryteria(String criteria, Osoba osoba) {

		if (osoba.getEmail().contains(criteria) || osoba.getNazwisko().contains(criteria)
				|| osoba.getLogin().contains(criteria))
			return true;

		return false;

	}

	// Odświeżanie tabeli
	private <T> void refreshTable(DefaultTableModel tableModel, ArrayList<T> lista) {
		tableModel.setRowCount(0);
		for (T osoba : lista) {
			if (osoba instanceof Pracownik) {
				Pracownik pracownik = (Pracownik) osoba;
				tableModel.addRow(new Object[] { pracownik.getImie(), pracownik.getNazwisko(), pracownik.getLogin(),
						pracownik.getEmail(), pracownik.getHaslo(), pracownik.getSaldoKonta(), pracownik.getWiek(),
						pracownik.getPesel() });
			} else if (osoba instanceof Klient) {
				Klient klient = (Klient) osoba;
				String promocja = typPromocji(klient.getPromocjaKlienta());

				tableModel.addRow(new Object[] { klient.getImie(), klient.getNazwisko(), klient.getLogin(),
						klient.getEmail(), klient.getHaslo(), klient.getSaldoKonta(), klient.getWiek(), promocja });
			}

		}
	}

	private String typPromocji(Promocja promocja) {
		if (promocja instanceof PromocjaPodstawowa)
			return "Podstawowa";

		if (promocja instanceof PromocjaStalegoKlienta)
			return "Stały klient";

		return "Studencka";
	}

	// Ikony aplikacji
	private void toolIcon(JFrame Frame) {
		try {
			Frame.setIconImage(ImageIO.read(new File("Grafika/toolIcon.png")));
		} catch (Exception e) {
			System.err.println("Błąd podczas wczytywania ikony: " + e.getMessage());
		}
	}

	private void kierownikIcon() {
		try {
			frame1.setIconImage(ImageIO.read(new File("Grafika/dolarCzerwony.png")));
		} catch (Exception e) {
			System.err.println("Błąd podczas wczytywania ikony: " + e.getMessage());
		}
	}

}
