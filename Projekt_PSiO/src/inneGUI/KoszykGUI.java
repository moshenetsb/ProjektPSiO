package inneGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bibliotekaMetodIPol.Metody;
import logowanie.MenuLogowanie;
import osoba.Klient;
import produkty.Produkty;

import java.util.ArrayList;
import java.util.List;

public class KoszykGUI {

    private JFrame frame1;
    private JPanel panel1;
    private JLabel lbTytul;
    private JTextArea taKoszyk;
    private JButton btnKup;
    private JScrollPane scrollPane;

    private List<JSpinner> spinners = new ArrayList<>();
    private List<JButton> deleteButtons = new ArrayList<>();
    private ArrayList<Produkty> produktyKoszyka = new ArrayList<>();
    private Klient klient = (Metody.getListaKlientow()).get(MenuLogowanie.szukajIDLoginKlienta(Metody.getLoginAktywnejOsoby()));

    public KoszykGUI(JFrame frame1) {
        this.frame1 = frame1;
        wypelnijGUI(frame1);
    }

    private void wypelnijGUI(JFrame frame1) {
        panel1 = new JPanel();
        panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame1.getContentPane().add(BorderLayout.CENTER, panel1);
        panel1.setLayout(new BorderLayout(10, 10));

        Font customFont = new Font("Arial", Font.BOLD, 16);

        lbTytul = new JLabel("   KOSZYK   ", SwingConstants.CENTER);
        lbTytul.setFont(new Font("Arial", Font.BOLD, 24));
        lbTytul.setForeground(Color.GREEN);
        panel1.add(lbTytul, BorderLayout.NORTH);

        taKoszyk = new JTextArea(10, 30);
        taKoszyk.setEditable(false); 
        taKoszyk.setFont(new Font("Arial", Font.PLAIN, 14));
        taKoszyk.setBackground(new Color(245, 245, 245)); 
        taKoszyk.setForeground(Color.BLACK);
        taKoszyk.setText("Koszyk jest pusty.");

        scrollPane = new JScrollPane(taKoszyk);
        panel1.add(scrollPane, BorderLayout.CENTER);

        btnKup = new JButton("Kup");
        btnKup.setFont(customFont);
        btnKup.setBackground(new Color(0, 153, 0));
        btnKup.setForeground(Color.WHITE);
        panel1.add(btnKup, BorderLayout.SOUTH);

        btnKup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                klient.kup(frame1);
                produktyKoszyka.clear();
                spinners.clear();
                deleteButtons.clear();
                wyswietlKoszyk("Koszyk jest pusty.");
            }
        });
    }

    public void wyswietlKoszyk(String zawartoscKoszyka) {
        taKoszyk.setText(zawartoscKoszyka);
        panel1.revalidate();
        panel1.repaint();
    }

    public void dodajProdukt(Produkty produkt) {
        produktyKoszyka.add(produkt);
        String zawartoscKoszyka = taKoszyk.getText() + produkt.getNazwaProduktu() + "\n";

        JPanel productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        productPanel.setPreferredSize(new Dimension(300, 40));

        JLabel productLabel = new JLabel(produkt.getNazwaProduktu());
        productLabel.setPreferredSize(new Dimension(200, 30));
        productPanel.add(productLabel);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinners.add(spinner);
        productPanel.add(spinner);

        JButton btnDelete = new JButton("Usuń");
        btnDelete.setPreferredSize(new Dimension(70, 30));
        deleteButtons.add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                produktyKoszyka.remove(produkt);
                spinners.remove(spinner);
                deleteButtons.remove(btnDelete);
                panel1.remove(productPanel);
                wyswietlKoszyk(zawartoscKoszyka);
            }
        });

        productPanel.add(btnDelete);

        panel1.add(productPanel, BorderLayout.CENTER);
        wyswietlKoszyk(zawartoscKoszyka);
    }
}