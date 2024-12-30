package strategiaGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import produkty.*;
import bibliotekaMetodIPol.*;
import java.util.ArrayList;

public class KierownikGUI extends OsobaZarzadzajacaGUI {

    private JPanel contentPanel;
    private JPanel produktyPanel;  // Declare this as a member variable, not inside the method

    // Konstruktor
    public KierownikGUI(JFrame frame1) {
        super(frame1);
        contentPanel = new JPanel(new BorderLayout());
        frame1.getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void GUIcreate(JFrame frame1) {
        super.GUIcreate(frame1);

        // Dodanie menu "Produkty"
        JMenu produktyMenu = new JMenu("Produkty");
        JMenuItem wyswietlProdukty = new JCheckBoxMenuItem("Wyświetl produkty");
        produktyMenu.add(wyswietlProdukty);

        // Dodanie funkcji do wyświetlenia produktów
        wyswietlProdukty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wyswietlProdukty.isSelected()) {
                    wyswietlProdukty();
                } else {
                    ukryjProdukty();
                }
            }
        });

        frame1.getJMenuBar().add(produktyMenu);
    }

    private void wyswietlProdukty() {
        // If the panel is not initialized yet, initialize it
        if (produktyPanel == null) {
            produktyPanel = new JPanel(new BorderLayout());
            produktyPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

            String[] kolumny = {"Id", "Nazwa", "Cena", "Ilość", "Kategoria"};
            DefaultTableModel model = new DefaultTableModel(kolumny, 0);
            JTable table = new JTable(model);

            ArrayList <Produkty> listaProduktow = Metody.getListaProduktow();
            if (listaProduktow != null && !listaProduktow.isEmpty()) {
                for (Produkty produkt : listaProduktow) {
                    String kategoria = "";
                    if (produkt instanceof Gaming) kategoria = "Gaming";
                    else if (produkt instanceof Fotografia) kategoria = "Fotografia";
                    else if (produkt instanceof Mieszane) kategoria = "Mieszane";

                    model.addRow(new Object[]{
                            produkt.getIdProduktu(),
                            produkt.getNazwaProduktu(),
                            produkt.getCenaProduktu(),
                            produkt.getLiczbaProduktu(),
                            kategoria
                    });
                }
            }

            JScrollPane scrollPane = new JScrollPane(table);
            produktyPanel.add(scrollPane, BorderLayout.CENTER);
        }

        // Add the panel to contentPanel and refresh the view
        contentPanel.add(produktyPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void ukryjProdukty() {
        // Remove the produktyPanel if it was created
        if (produktyPanel != null) {
            contentPanel.remove(produktyPanel);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }
}

