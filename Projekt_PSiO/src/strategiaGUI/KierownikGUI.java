package strategiaGUI;

import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import bibliotekaMetodIPol.*;
import logowanie.MenuLogowanie;
import osoba.*;
import adres.Adres;
import promocjaStrategia.*;
import zakupy.*;
import promocjaStrategia.*;

public class KierownikGUI extends PracownikGUI {

	private JFrame frame1;
	private ArrayList<Klient> listaKlientow;
	private ArrayList<Pracownik> listaPracownikow;
	private DefaultTableModel klientTableModel;
	private DefaultTableModel pracownikTableModel;

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
			searchClients.setEnabled(false);
			showClientSearch(searchClients);
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
			searchEmployees.setEnabled(false);
			showEmployeeSearch(searchEmployees);
		});

		employeesSubmenu.add(manageEmployees);
		employeesSubmenu.add(searchEmployees);

		toolsMenu.add(clientsSubmenu);
		toolsMenu.add(employeesSubmenu);
		menuBar.add(toolsMenu);
	}

	private void showClientManagement(JMenuItem manageClients) {
		JFrame clientFrame = new JFrame("Zarządzanie Klientami");
		clientFrame.setSize(1000, 600);
		clientFrame.setLayout(new BorderLayout());

		toolIcon(clientFrame);

		String[] columnNames = { "Imię", "Nazwisko", "Login", "Email", "Hasło", "Saldo Konta", "Wiek", "Typ promocji" };

		klientTableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable klientTable = new JTable(klientTableModel);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Dodaj");
		JButton removeButton = new JButton("Usuń");
		JButton editButton = new JButton("Edytuj");
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(editButton);

		addButton.addActionListener(e -> addClient());
		removeButton.addActionListener(e -> removeClient(klientTable, clientFrame));
		editButton.addActionListener(e -> editClient(klientTable, clientFrame));

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

	private void addClient() {
		JTextField imieField = new JTextField(20);
		JTextField nazwiskoField = new JTextField(20);
		JTextField emailField = new JTextField(20);

		JPanel panel = new JPanel(new GridLayout(4, 2));
		panel.add(new JLabel("Imię:"));
		panel.add(imieField);
		panel.add(new JLabel("Nazwisko:"));
		panel.add(nazwiskoField);
		panel.add(new JLabel("Email:"));
		panel.add(emailField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Dodaj Klienta", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Klient nowyKlient = new Klient(emailField.getText(), "", "", nazwiskoField.getText(), imieField.getText(),
					0, new Adres("", "", "", null, null, null), 0, new PromocjaPodstawowa(), new ArrayList<>(),
					new Zakupy());
			listaKlientow.add(nowyKlient);
			refreshTable(klientTableModel, listaKlientow);
		}
	}

	private void removeClient(JTable klientTable, JFrame clientFrame) {
		int selectedRow = klientTable.getSelectedRow();
		if (selectedRow != -1) {
			int confirm = JOptionPane.showConfirmDialog(clientFrame, "Czy na pewno chcesz usunąć konto tego klienta?",
					"Potwierdzenie usunięcia", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				listaKlientow.remove(selectedRow);
				refreshTable(klientTableModel, listaKlientow);
			}

		} else {
			JOptionPane.showMessageDialog(clientFrame, "Nie wybrano konto do usunięcia!", "Informacja usunięcia",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void editClient(JTable klientTable, JFrame clientFrame) {
		int selectedRow = klientTable.getSelectedRow();
		if (selectedRow != -1) {
			Klient klient = listaKlientow.get(selectedRow);

			JTextField imieField = new JTextField(klient.getImie(), 20);
			JTextField nazwiskoField = new JTextField(klient.getNazwisko(), 20);
			JTextField emailField = new JTextField(klient.getEmail(), 20);

			JPanel panel = new JPanel(new GridLayout(4, 2));
			panel.add(new JLabel("Imię:"));
			panel.add(imieField);
			panel.add(new JLabel("Nazwisko:"));
			panel.add(nazwiskoField);
			panel.add(new JLabel("Email:"));
			panel.add(emailField);

			int result = JOptionPane.showConfirmDialog(null, panel, "Edytuj Klienta", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				klient.setImie(imieField.getText());
				klient.setNazwisko(nazwiskoField.getText());
				klient.setEmail(emailField.getText());
				refreshTable(klientTableModel, listaKlientow);
			}
		} else {
			JOptionPane.showMessageDialog(clientFrame, "Nie wybrano konto dla edytowania!", "Informacja edytowania",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void showClientSearch(JMenuItem searchClients) {
		JFrame searchFrame = new JFrame("Wyszukiwanie Klientów");
		searchFrame.setSize(1000, 600);

		toolIcon(searchFrame);

		JPanel searchPanel = new JPanel(new FlowLayout());
		JTextField searchField = new JTextField(20);
		JButton searchButton = new JButton("Szukaj");

		searchPanel.add(new JLabel("Wprowadź kryteria (email, login, nazwisko):"));
		searchPanel.add(searchField);
		searchPanel.add(searchButton);

		String[] columnNames = { "Imię", "Nazwisko", "Login", "Email", "Hasło", "Saldo Konta", "Wiek", "Typ promocji" };

		DefaultTableModel searchTableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable searchTable = new JTable(searchTableModel);

		searchButton.addActionListener(e -> search(searchField.getText(), searchTableModel, listaKlientow));

		searchFrame.add(searchPanel, BorderLayout.NORTH);

		searchFrame.add(new JScrollPane(searchTable), BorderLayout.CENTER);

		searchFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				searchClients.setEnabled(true);
			}
		});

		searchFrame.setVisible(true);
	}

	private void showEmployeeManagement(JMenuItem showEmployee) {
		JFrame employeeFrame = new JFrame("Zarządzanie Pracownikami");
		employeeFrame.setSize(1000, 600);
		employeeFrame.setLayout(new BorderLayout());

		toolIcon(employeeFrame);

		String[] columnNames = { "Imię", "Nazwisko", "Login", "Email", "Hasło", "Saldo Konta", "PESEL" };
		pracownikTableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable pracownikTable = new JTable(pracownikTableModel);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Dodaj");
		JButton removeButton = new JButton("Usuń");
		JButton editButton = new JButton("Edytuj");
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(editButton);

		addButton.addActionListener(e -> addEmployee());
		removeButton.addActionListener(e -> removeEmployee(pracownikTable, employeeFrame));
		editButton.addActionListener(e -> editEmployee(pracownikTable, employeeFrame));

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

	private void addEmployee() {
		JTextField imieField = new JTextField(10);
		JTextField nazwiskoField = new JTextField(10);
		JTextField emailField = new JTextField(10);
		JTextField saldoField = new JTextField(10);

		JPanel panel = new JPanel(new GridLayout(4, 2));
		panel.add(new JLabel("Imię:"));
		panel.add(imieField);
		panel.add(new JLabel("Nazwisko:"));
		panel.add(nazwiskoField);
		panel.add(new JLabel("Email:"));
		panel.add(emailField);
		panel.add(new JLabel("Saldo:"));
		panel.add(saldoField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Dodaj Pracownika", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			double saldo = Double.parseDouble(saldoField.getText());
			Pracownik nowyPracownik = new Pracownik(emailField.getText(), "", "", nazwiskoField.getText(),
					imieField.getText(), 0, new Adres("", "", "", null, null, null), saldo, "");
			listaPracownikow.add(nowyPracownik);
			refreshTable(pracownikTableModel, listaPracownikow);
		}
	}

	private void removeEmployee(JTable pracownikTable, JFrame employeeFrame) {
		int selectedRow = pracownikTable.getSelectedRow();
		if (selectedRow != -1) {
			int confirm = JOptionPane.showConfirmDialog(employeeFrame,
					"Czy na pewno chcesz usunąć konto tego pracownika?", "Potwierdzenie usunięcia",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {

				Pracownik wybranyPracownik = (Pracownik) listaPracownikow.get(selectedRow);
				Pracownik aktywnyPracownik = Metody.getListaOsobZarzadzajacych()
						.get(MenuLogowanie.szukajIDLoginZarzadzajacych(Metody.getLoginAktywnejOsoby()));

				if (wybranyPracownik.getLogin().equals(aktywnyPracownik.getLogin()))
					JOptionPane.showMessageDialog(employeeFrame, "Nie można usunąć swojego konta!", "Błąd usunięcia",
							JOptionPane.ERROR_MESSAGE);
				else {
					listaPracownikow.remove(selectedRow);
					refreshTable(pracownikTableModel, listaPracownikow);
				}

			}

		} else {
			JOptionPane.showMessageDialog(employeeFrame, "Nie wybrano konto do usunięcia!", "Informacja usunięcia",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void editEmployee(JTable pracownikTable, JFrame employeeFrame) {
		int selectedRow = pracownikTable.getSelectedRow();
		if (selectedRow != -1) {
			Pracownik pracownik = (Pracownik) listaPracownikow.get(selectedRow);

			JTextField imieField = new JTextField(pracownik.getImie(), 10);
			JTextField nazwiskoField = new JTextField(pracownik.getNazwisko(), 10);
			JTextField emailField = new JTextField(pracownik.getEmail(), 10);
			JTextField saldoField = new JTextField(String.valueOf(pracownik.getSaldoKonta()), 10);

			JPanel panel = new JPanel(new GridLayout(4, 2));
			panel.add(new JLabel("Imię:"));
			panel.add(imieField);
			panel.add(new JLabel("Nazwisko:"));
			panel.add(nazwiskoField);
			panel.add(new JLabel("Email:"));
			panel.add(emailField);
			panel.add(new JLabel("Saldo:"));
			panel.add(saldoField);

			int result = JOptionPane.showConfirmDialog(null, panel, "Edytuj Pracownika", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				pracownik.setImie(imieField.getText());
				pracownik.setNazwisko(nazwiskoField.getText());
				pracownik.setEmail(emailField.getText());
				pracownik.setSaldoKonta(Double.parseDouble(saldoField.getText()));
				refreshTable(pracownikTableModel, listaPracownikow);
			}
		} else {
			JOptionPane.showMessageDialog(employeeFrame, "Nie wybrano konto dla edytowania!", "Informacja edytowania",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void showEmployeeSearch(JMenuItem searchEmployees) {
		JFrame searchFrame = new JFrame("Wyszukiwanie Pracowników");
		searchFrame.setSize(1000, 600);

		toolIcon(searchFrame);

		JPanel searchPanel = new JPanel(new FlowLayout());
		JTextField searchField = new JTextField(20);
		JButton searchButton = new JButton("Szukaj");

		searchPanel.add(new JLabel("Wprowadź kryteria (email, login, nazwisko):"));
		searchPanel.add(searchField);
		searchPanel.add(searchButton);

		String[] columnNames = { "Imię", "Nazwisko", "Login", "Email", "Hasło", "Saldo Konta", "PESEL" };
		DefaultTableModel searchTableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable searchTable = new JTable(searchTableModel);

		searchButton.addActionListener(e -> search(searchField.getText(), searchTableModel, listaPracownikow));

		searchFrame.add(searchPanel, BorderLayout.NORTH);
		searchFrame.add(new JScrollPane(searchTable), BorderLayout.CENTER);

		searchFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				searchEmployees.setEnabled(true);
			}
		});

		searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		searchFrame.setVisible(true);
	}

	// Wyszukiwanie osób
	private <T> void search(String criteria, DefaultTableModel tableModel, ArrayList<T> lista) {
		tableModel.setRowCount(0);

		for (T osoba : lista) {
			if (czySpelniaKryteria(criteria, (Osoba) osoba)) {

				if (osoba instanceof Pracownik) {
					Pracownik pracownik = (Pracownik) osoba;
					tableModel.addRow(new Object[] { pracownik.getImie(), pracownik.getNazwisko(), pracownik.getLogin(),
							pracownik.getEmail(), pracownik.getHaslo(), pracownik.getSaldoKonta(),
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
						pracownik.getEmail(), pracownik.getHaslo(), pracownik.getSaldoKonta(), pracownik.getPesel() });
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
