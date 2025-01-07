package strategiaGUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.table.*;
import produkty.*;
import bibliotekaMetodIPol.*;
import java.util.ArrayList;

public abstract class OsobaZarzadzajacaGUI extends WspolneGUI {

	// Składowe kłasy
	private JFrame frame1;
	private JPanel contentPanel;
	private JPanel mainProductPanel;
	private JPanel searchPanel;
	private DefaultTableModel tableModel;
	private JTable productsTable;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField quantityField;
	private JTextField descriptionField;
	private JComboBox<String> productTypeComboBox;
	private JTextField productIdField;

	// Konstruktor
	public OsobaZarzadzajacaGUI(JFrame frame1) {
		super(frame1);
		this.frame1 = frame1;

	}

	@Override
	public void GUIcreate(JFrame frame1) {
		super.GUIcreate(frame1);
		createToolsMenu(frame1);
	}

	private void initializeComponents() {
		contentPanel = new JPanel(new BorderLayout());
		frame1.getContentPane().add(contentPanel, BorderLayout.CENTER);

	}

	private void createToolsMenu(JFrame frame1) {
		JMenu toolsMenu = new JMenu("Produkty");

		JMenuItem manageProducts = new JMenuItem("Zarządzaj produktami");
		JMenuItem searchProducts = new JMenuItem("Wyszukaj produkty");

		manageProducts.addActionListener(e -> showProductManagement());
		searchProducts.addActionListener(e -> showProductSearch());

		toolsMenu.add(manageProducts);
		toolsMenu.add(searchProducts);
		frame1.getJMenuBar().add(toolsMenu);

	}

	private JPanel createMainProductPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel managementPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		addProductManagementControls(managementPanel, gbc);
		createProductsTable();

		panel.add(managementPanel, BorderLayout.NORTH);
		panel.add(new JScrollPane(productsTable), BorderLayout.CENTER);

		return panel;
	}

	private void addProductManagementControls(JPanel panel, GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(new JLabel("Typ produktu:"), gbc);
		String[] productTypes = { "Gaming", "Fotografia", "Mieszane" };
		productTypeComboBox = new JComboBox<>(productTypes);
		gbc.gridx = 1;
		panel.add(productTypeComboBox, gbc);

		gbc.gridx = 2;
		panel.add(new JLabel("ID produktu:"), gbc);
		productIdField = new JTextField(10);
		gbc.gridx = 3;
		panel.add(productIdField, gbc);

		addProductDetailsFields(panel, gbc);
		addActionButtons(panel, gbc);
	}

	private void addProductDetailsFields(JPanel panel, GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Nazwa:"), gbc);
		nameField = new JTextField(20);
		gbc.gridx = 1;
		panel.add(nameField, gbc);

		gbc.gridx = 2;
		panel.add(new JLabel("Cena:"), gbc);
		priceField = new JTextField(10);
		gbc.gridx = 3;
		panel.add(priceField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel("Ilość:"), gbc);
		quantityField = new JTextField(10);
		gbc.gridx = 1;
		panel.add(quantityField, gbc);

		gbc.gridx = 2;
		panel.add(new JLabel("Opis:"), gbc);
		descriptionField = new JTextField(20);
		gbc.gridx = 3;
		panel.add(descriptionField, gbc);
	}

	private void addActionButtons(JPanel panel, GridBagConstraints gbc) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JButton addButton = new JButton("Dodaj");
		JButton modifyButton = new JButton("Modyfikuj");
		JButton deleteButton = new JButton("Usuń");
		JButton searchButton = new JButton("Szukaj");
		JButton clearButton = new JButton("Wyczyść");

		buttonPanel.add(addButton);
		buttonPanel.add(modifyButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(clearButton);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		panel.add(buttonPanel, gbc);

		addButton.addActionListener(e -> handleAddProduct());
		modifyButton.addActionListener(e -> handleModifyProduct());
		deleteButton.addActionListener(e -> handleDeleteProduct());
		searchButton.addActionListener(e -> showProductSearch());
		clearButton.addActionListener(e -> clearFields());
	}

	private void createProductsTable() {
		String[] columns = { "Id", "Nazwa", "Cena", "Ilość", "Kategoria" };
		tableModel = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		productsTable = new JTable(tableModel);
		productsTable.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = productsTable.getSelectedRow();
				if (selectedRow != -1) {
					loadProductToFields(selectedRow);
				}
			}
		});
		refreshProductsTable();
	}

	private JPanel createSearchPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel searchControls = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx = 0;
		gbc.gridy = 0;
		searchControls.add(new JLabel("Szukaj:"), gbc);
		JTextField searchField = new JTextField(20);
		gbc.gridx = 1;
		searchControls.add(searchField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		searchControls.add(new JLabel("Kategoria:"), gbc);
		String[] categories = { "Wszystkie", "Gaming", "Fotografia", "Mieszane" };
		JComboBox<String> categoryFilter = new JComboBox<>(categories);
		gbc.gridx = 1;
		searchControls.add(categoryFilter, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pricePanel.add(new JLabel("Cena od:"));
		JTextField minPrice = new JTextField(8);
		pricePanel.add(minPrice);
		pricePanel.add(new JLabel("do:"));
		JTextField maxPrice = new JTextField(8);
		pricePanel.add(maxPrice);
		gbc.gridwidth = 2;
		searchControls.add(pricePanel, gbc);

		gbc.gridy = 3;
		JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		quantityPanel.add(new JLabel("Ilość od:"));
		JTextField minQuantity = new JTextField(8);
		quantityPanel.add(minQuantity);
		quantityPanel.add(new JLabel("do:"));
		JTextField maxQuantity = new JTextField(8);
		quantityPanel.add(maxQuantity);
		searchControls.add(quantityPanel, gbc);

		gbc.gridy = 4;
		JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sortPanel.add(new JLabel("Sortuj po:"));
		String[] sortOptions = { "Domyślnie", "Nazwa", "Cena", "Ilość", "Kategoria" };
		JComboBox<String> sortCriteria = new JComboBox<>(sortOptions);
		sortPanel.add(sortCriteria);

		sortPanel.add(new JLabel("Kolejność:"));
		String[] orderOptions = { "Rosnąco", "Malejąco" };
		JComboBox<String> sortOrder = new JComboBox<>(orderOptions);
		sortPanel.add(sortOrder);
		searchControls.add(sortPanel, gbc);

		String[] columns = { "Id", "Nazwa", "Cena", "Ilość", "Kategoria" };
		DefaultTableModel searchTableModel = new DefaultTableModel(columns, 0) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable searchResultsTable = new JTable(searchTableModel);
		JScrollPane scrollPane = new JScrollPane(searchResultsTable);

		performSearch("", "Wszystkie", "", "", "", "", "Domyślnie", "Rosnąco", searchTableModel);

		DocumentListener searchListener = new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateSearch();
			}

			public void removeUpdate(DocumentEvent e) {
				updateSearch();
			}

			public void insertUpdate(DocumentEvent e) {
				updateSearch();
			}

			private void updateSearch() {
				performSearch(searchField.getText(), categoryFilter.getSelectedItem().toString(), minPrice.getText(),
						maxPrice.getText(), minQuantity.getText(), maxQuantity.getText(),
						sortCriteria.getSelectedItem().toString(), sortOrder.getSelectedItem().toString(),
						searchTableModel);
			}
		};

		searchField.getDocument().addDocumentListener(searchListener);
		categoryFilter.addActionListener(e -> searchListener.changedUpdate(null));
		sortCriteria.addActionListener(e -> searchListener.changedUpdate(null));
		sortOrder.addActionListener(e -> searchListener.changedUpdate(null));

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton backProductManagementButton = new JButton("Zarządzanie produktami");
		backProductManagementButton.addActionListener(e -> showProductManagement());
		buttonPanel.add(backProductManagementButton);

		panel.add(searchControls, BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		return panel;
	}

	private void performSearch(String searchText, String category, String minPriceText, String maxPriceText,
			String minQuantityText, String maxQuantityText, String sortCriteria, String sortOrder,
			DefaultTableModel searchTableModel) {
		try {
			double minPrice = minPriceText.isEmpty() ? Double.MIN_VALUE : Double.parseDouble(minPriceText);
			double maxPrice = maxPriceText.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(maxPriceText);
			int minQuantity = minQuantityText.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(minQuantityText);
			int maxQuantity = maxQuantityText.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(maxQuantityText);

			ArrayList<Produkty> products = Metody.getListaProduktow();
			ArrayList<Produkty> filteredProducts = new ArrayList<>();

			for (Produkty product : products) {
				if ((searchText.isEmpty()
						|| product.getNazwaProduktu().toLowerCase().contains(searchText.toLowerCase()))
						&& (category.equals("Wszystkie") || getProductCategory(product).equals(category))
						&& (product.getCenaProduktu() >= minPrice && product.getCenaProduktu() <= maxPrice)
						&& (product.getLiczbaProduktu() >= minQuantity && product.getLiczbaProduktu() <= maxQuantity)) {
					filteredProducts.add(product);
				}
			}

			filteredProducts.sort((p1, p2) -> {
				int result = 0;
				switch (sortCriteria) {
				case "Nazwa":
					result = p1.getNazwaProduktu().compareTo(p2.getNazwaProduktu());
					break;
				case "Cena":
					result = Float.compare(p1.getCenaProduktu(), p2.getCenaProduktu());
					break;
				case "Ilość":
					result = Integer.compare(p1.getLiczbaProduktu(), p2.getLiczbaProduktu());
					break;
				case "Kategoria":
					result = getProductCategory(p1).compareTo(getProductCategory(p2));
					break;
				}
				return sortOrder.equals("Malejąco") ? -result : result;
			});

			searchTableModel.setRowCount(0);
			for (Produkty product : filteredProducts) {
				searchTableModel.addRow(new Object[] { product.getIdProduktu(), product.getNazwaProduktu(),
						product.getCenaProduktu(), product.getLiczbaProduktu(), getProductCategory(product) });
			}
		} catch (NumberFormatException e) {
		}
	}

	private void loadProductToFields(int selectedRow) {
		productIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
		nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
		priceField.setText(tableModel.getValueAt(selectedRow, 2).toString());
		quantityField.setText(tableModel.getValueAt(selectedRow, 3).toString());
		productTypeComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 4));
	}

	private void clearFields() {
		productIdField.setText("");
		nameField.setText("");
		priceField.setText("");
		quantityField.setText("");
		descriptionField.setText("");
		productTypeComboBox.setSelectedIndex(0);
		productsTable.clearSelection();
	}

	private void refreshProductsTable() {
		tableModel.setRowCount(0);
		ArrayList<Produkty> products = Metody.getListaProduktow();
		if (products != null) {
			for (Produkty product : products) {
				tableModel.addRow(new Object[] { product.getIdProduktu(), product.getNazwaProduktu(),
						product.getCenaProduktu(), product.getLiczbaProduktu(), getProductCategory(product) });
			}
		}
	}

	private String getProductCategory(Produkty product) {
		if (product instanceof Gaming)
			return "Gaming";
		if (product instanceof Fotografia)
			return "Fotografia";
		if (product instanceof Mieszane)
			return "Mieszane";
		return "";
	}

	private void handleAddProduct() {
		try {
			if (validateFields()) {
				String productType = (String) productTypeComboBox.getSelectedItem();
				String name = nameField.getText();
				float price = Float.parseFloat(priceField.getText());
				int quantity = Integer.parseInt(quantityField.getText());
				String description = descriptionField.getText();

				Produkty newProduct;
				switch (productType) {
				case "Gaming":
					newProduct = new Gaming(name, price, quantity, description);
					break;
				case "Fotografia":
					newProduct = new Fotografia(name, price, quantity, description);
					break;
				case "Mieszane":
					newProduct = new Mieszane(name, price, quantity, description);
					break;
				default:
					throw new IllegalArgumentException("Nieprawidłowy typ produktu");
				}

				Metody.getListaProduktow().add(newProduct);
				refreshProductsTable();
				clearFields();
				JOptionPane.showMessageDialog(contentPanel, "Produkt został dodany pomyślnie.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPanel, "Nieprawidłowy format danych numerycznych.", "Błąd",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Wystąpił błąd: " + e.getMessage(), "Błąd",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void handleModifyProduct() {
		try {
			if (productIdField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(contentPanel, "Wybierz produkt do modyfikacji.", "Informacja",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			if (validateFields()) {
				int productId = Integer.parseInt(productIdField.getText());
				String productType = (String) productTypeComboBox.getSelectedItem();
				String name = nameField.getText();
				float price = Float.parseFloat(priceField.getText());
				int quantity = Integer.parseInt(quantityField.getText());
				String description = descriptionField.getText();

				ArrayList<Produkty> products = Metody.getListaProduktow();
				boolean found = false;

				for (int i = 0; i < products.size(); i++) {
					if (products.get(i).getIdProduktu() == productId) {
						Produkty modifiedProduct;
						switch (productType) {
						case "Gaming":
							modifiedProduct = new Gaming(name, price, quantity, description);
							break;
						case "Fotografia":
							modifiedProduct = new Fotografia(name, price, quantity, description);
							break;
						case "Mieszane":
							modifiedProduct = new Mieszane(name, price, quantity, description);
							break;
						default:
							throw new IllegalArgumentException("Nieprawidłowy typ produktu");
						}
						modifiedProduct.setIdProduktu(productId);
						products.set(i, modifiedProduct);
						found = true;
						break;
					}
				}

				if (found) {
					refreshProductsTable();
					clearFields();
					JOptionPane.showMessageDialog(contentPanel, "Produkt został zmodyfikowany pomyślnie.");
				} else {
					JOptionPane.showMessageDialog(contentPanel, "Nie znaleziono produktu o podanym ID.", "Błąd",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPanel, "Nieprawidłowy format danych numerycznych.", "Błąd",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Wystąpił błąd: " + e.getMessage(), "Błąd",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void handleDeleteProduct() {
		try {
			if (productIdField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(contentPanel, "Wybierz produkt do usunięcia.", "Informacja",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			int productId = Integer.parseInt(productIdField.getText());
			ArrayList<Produkty> products = Metody.getListaProduktow();
			boolean found = false;

			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getIdProduktu() == productId) {
					int confirm = JOptionPane.showConfirmDialog(contentPanel, "Czy na pewno chcesz usunąć ten produkt?",
							"Potwierdzenie usunięcia", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						products.remove(i);
						found = true;
						refreshProductsTable();
						clearFields();
						JOptionPane.showMessageDialog(contentPanel, "Produkt został usunięty pomyślnie.");
					}
					break;
				}
			}

			if (!found) {
				JOptionPane.showMessageDialog(contentPanel, "Nie znaleziono produktu o podanym ID.", "Błąd",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPanel, "Nieprawidłowy format ID produktu.", "Błąd",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean validateFields() {
		if (nameField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "Nazwa produktu nie może być pusta.", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			float price = Float.parseFloat(priceField.getText());
			if (price <= 0) {
				JOptionPane.showMessageDialog(contentPanel, "Cena musi być większa od zera.", "Błąd",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPanel, "Nieprawidłowy format ceny.", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			int quantity = Integer.parseInt(quantityField.getText());
			if (quantity < 0) {
				JOptionPane.showMessageDialog(contentPanel, "Ilość nie może być ujemna.", "Błąd",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPanel, "Nieprawidłowy format ilości.", "Błąd",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void showProductManagement() {
		frame1.getContentPane().removeAll();

		initializeComponents();

		mainProductPanel = createMainProductPanel();
		JScrollPane mainScrollPane = new JScrollPane(mainProductPanel);
		contentPanel.add(mainScrollPane, BorderLayout.CENTER);

		frame1.revalidate();
		frame1.repaint();
	}

	private void showProductSearch() {
		frame1.getContentPane().removeAll();

		initializeComponents();

		searchPanel = createSearchPanel();
		JScrollPane searchScrollPane = new JScrollPane(searchPanel);
		contentPanel.add(searchScrollPane, BorderLayout.CENTER);

		frame1.revalidate();
		frame1.repaint();

	}
}