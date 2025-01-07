package strategiaGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import bibliotekaMetodIPol.*;
import osoba.*;
import adres.Adres;

public class KierownikGUI extends OsobaZarzadzajacaGUI {

    private ArrayList<Klient> listaKlientow = Metody.getListaKlientow();
    private ArrayList<OsobaZarzadzajaca> listaPracownikow = Metody.getListaOsobZarzadzajacych();
    private DefaultTableModel klientTableModel;
    private DefaultTableModel pracownikTableModel;
    
    

    public KierownikGUI(JFrame frame1) {
        super(frame1);
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
        manageClients.addActionListener(e -> showClientManagement());
        JMenuItem searchClients = new JMenuItem("Wyszukaj klientów");
        searchClients.addActionListener(e -> showClientSearch());
        clientsSubmenu.add(manageClients);
        clientsSubmenu.add(searchClients);

        // Opcje dla pracowników
        JMenuItem manageEmployees = new JMenuItem("Zarządzaj pracownikami");
        manageEmployees.addActionListener(e -> showEmployeeManagement());
        JMenuItem searchEmployees = new JMenuItem("Wyszukaj pracowników");
        searchEmployees.addActionListener(e -> showEmployeeSearch());
        employeesSubmenu.add(manageEmployees);
        employeesSubmenu.add(searchEmployees);

        toolsMenu.add(clientsSubmenu);
        toolsMenu.add(employeesSubmenu);
        menuBar.add(toolsMenu);
    }

    private void showClientManagement() {
        JFrame clientFrame = new JFrame("Zarządzanie Klientami");
        clientFrame.setSize(800, 600);
        clientFrame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Imię", "Nazwisko", "Email", "Ranga"};    
        
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
        removeButton.addActionListener(e -> removeClient(klientTable));
        editButton.addActionListener(e -> editClient(klientTable));

        refreshClientTable();

        clientFrame.add(new JScrollPane(klientTable), BorderLayout.CENTER);
        clientFrame.add(buttonPanel, BorderLayout.SOUTH);
        clientFrame.setVisible(true);
    }

    private void addClient() {
        JTextField imieField = new JTextField(10);
        JTextField nazwiskoField = new JTextField(10);
        JTextField emailField = new JTextField(10);
        JTextField rangaField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Imię:"));
        panel.add(imieField);
        panel.add(new JLabel("Nazwisko:"));
        panel.add(nazwiskoField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Ranga:"));
        panel.add(rangaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Dodaj Klienta", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Klient nowyKlient = new Klient(emailField.getText(), "", "", nazwiskoField.getText(),
                    imieField.getText(), 0, new Adres("", "", "", null, null, null), 0, "", new ArrayList<>());
            listaKlientow.add(nowyKlient);
            refreshClientTable();
        }
    }

    private void removeClient(JTable klientTable) {
        int selectedRow = klientTable.getSelectedRow();
        if (selectedRow != -1) {
            listaKlientow.remove(selectedRow);
            refreshClientTable();
        }
    }

    private void editClient(JTable klientTable) {
        int selectedRow = klientTable.getSelectedRow();
        if (selectedRow != -1) {
            Klient klient = listaKlientow.get(selectedRow);

            JTextField imieField = new JTextField(klient.getImie(), 10);
            JTextField nazwiskoField = new JTextField(klient.getNazwisko(), 10);
            JTextField emailField = new JTextField(klient.getEmail(), 10);

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
                refreshClientTable();
            }
        }
    }

    private void showClientSearch() {
        JFrame searchFrame = new JFrame("Wyszukiwanie Klientów");
        searchFrame.setSize(800, 600);

        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Szukaj");

        searchPanel.add(new JLabel("Wprowadź kryteria:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        String[] columnNames = {"ID", "Imię", "Nazwisko", "Email", "Ranga"};
        
        DefaultTableModel searchTableModel = new DefaultTableModel(columnNames, 0){
        	private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable searchTable = new JTable(searchTableModel);

        searchButton.addActionListener(e -> searchClients(searchField.getText(), searchTableModel));

        searchFrame.add(searchPanel, BorderLayout.NORTH);
        searchFrame.add(new JScrollPane(searchTable), BorderLayout.CENTER);
        searchFrame.setVisible(true);
    }

    private void searchClients(String criteria, DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (Klient klient : listaKlientow) {
            if (klient.getEmail().contains(criteria) || klient.getNazwisko().contains(criteria)) {
                tableModel.addRow(new Object[]{klient.hashCode(), klient.getImie(), klient.getNazwisko(), klient.getEmail()});
            }
        }
    }

    private void showEmployeeManagement() {
        JFrame employeeFrame = new JFrame("Zarządzanie Pracownikami");
        employeeFrame.setSize(800, 600);
        employeeFrame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Imię", "Nazwisko", "Email", "Saldo Konta"};
        pracownikTableModel = new DefaultTableModel(columnNames, 0);
        JTable pracownikTable = new JTable(pracownikTableModel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Dodaj");
        JButton removeButton = new JButton("Usuń");
        JButton editButton = new JButton("Edytuj");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);

        addButton.addActionListener(e -> addEmployee());
        removeButton.addActionListener(e -> removeEmployee(pracownikTable));
        editButton.addActionListener(e -> editEmployee(pracownikTable));

        refreshEmployeeTable();

        employeeFrame.add(new JScrollPane(pracownikTable), BorderLayout.CENTER);
        employeeFrame.add(buttonPanel, BorderLayout.SOUTH);
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
            refreshEmployeeTable();
        }
    }

    private void removeEmployee(JTable pracownikTable) {
        int selectedRow = pracownikTable.getSelectedRow();
        if (selectedRow != -1) {
            listaPracownikow.remove(selectedRow);
            refreshEmployeeTable();
        }
    }

    private void editEmployee(JTable pracownikTable) {
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
                refreshEmployeeTable();
            }
        }
    }

    private void showEmployeeSearch() {
        JFrame searchFrame = new JFrame("Wyszukiwanie Pracowników");
        searchFrame.setSize(800, 600);

        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Szukaj");

        searchPanel.add(new JLabel("Wprowadź kryteria:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        String[] columnNames = {"ID", "Imię", "Nazwisko", "Email", "Saldo Konta"};
        DefaultTableModel searchTableModel = new DefaultTableModel(columnNames, 0);
        JTable searchTable = new JTable(searchTableModel);

        searchButton.addActionListener(e -> searchEmployees(searchField.getText(), searchTableModel));

        searchFrame.add(searchPanel, BorderLayout.NORTH);
        searchFrame.add(new JScrollPane(searchTable), BorderLayout.CENTER);
        searchFrame.setVisible(true);
    }

    private void searchEmployees(String criteria, DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (OsobaZarzadzajaca pracownik : listaPracownikow) {
            if (pracownik.getEmail().contains(criteria) || pracownik.getNazwisko().contains(criteria)) {
                tableModel.addRow(new Object[]{pracownik.hashCode(), pracownik.getImie(), pracownik.getNazwisko(), pracownik.getEmail(), pracownik.getSaldoKonta()});
            }
        }
    }

    private void refreshClientTable() {
        klientTableModel.setRowCount(0);
        for (Klient klient : listaKlientow) {
            klientTableModel.addRow(new Object[]{klient.hashCode(), klient.getImie(), klient.getNazwisko(), klient.getEmail()});
        }
    }

    private void refreshEmployeeTable() {
        pracownikTableModel.setRowCount(0);
        for (OsobaZarzadzajaca osoba : listaPracownikow) {
        	 if (osoba instanceof Pracownik) {
        		 Pracownik pracownik = (Pracownik) osoba;
            pracownikTableModel.addRow(new Object[]{pracownik.hashCode(), pracownik.getImie(), pracownik.getNazwisko(), pracownik.getEmail(), pracownik.getSaldoKonta()});
        }
        }
    }
}
