package Metody;

import osoba.*;
import Produkty.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Metody{

	public static Scanner scanner = new Scanner(System.in);
	public static boolean czyKoniecProgramu = false;
	public static Osoba aktywnaOsoba = null;
	
    public static ArrayList<Klient> listaKlientow = new ArrayList<>();
    public static ArrayList<Pracownik> listaSprzedawcow = new ArrayList<>();
    public static ArrayList<Kierownik> listaKierownikow = new ArrayList<>();
    public static ArrayList<T> listaProduktow = new ArrayList<T>();

    public static void wczytaj() {
        wczytajKlientow();
        wczytajPracownikow();
        wczytajKierownikow();
        wczytajProdukty();
    }

    public static void zapisz() {
        zapiszKlientow();
        zapiszPracownikow();
        zapiszKierownikow();
        zapiszProdukty();
    }

    public static void wczytajKlientow() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("BazaDanych/klienci.txt"))) {
            Object obj1 = is.readObject();
            kl = (Klient) obj1;
            listaKlientow.add(kl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void wczytajPracownikow() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("BazaDanych/pracownicy.txt"))) {
            Object obj1 = is.readObject();
            pr = (Pracownik) obj1;
            listaPracownikow.add(pr);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajKierownikow() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("BazaDanych/kierownicy.txt"))) {
            Object obj1 = is.readObject();
            kier = (Kierownik) obj1;
            listaKierownikow.add(kier);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajProdukty() {
        File bazaProdukty = new File("BazaDanych/produkty.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(bazaProdukty))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(",");

                if dane[0] == "Kategoria: gaming"
                {
                    Gaming produkt = new Gaming(dane[1], Double.parseDouble(dane[2]), dane[3], Integer.parseInt(dane[4]));
                    listaProduktow.add(produkt);
                }

                else if dane[0] == "Kategoria: fotografia"
                {
                    Fotografia produkt = new Fotografia(dane[1], Double.parseDouble(dane[2]), dane[3], Integer.parseInt(dane[4]));
                    listaProduktow.add(produkt);
                }

                else
                {
                    Mieszane produkt = new Mieszane(dane[1], Double.parseDouble(dane[2]), dane[3], Integer.parseInt(dane[4]));
                    listaProduktow.add(produkt);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void zapiszKlientow() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BazaDanych/klienci.txt")))
        {
           for (Klient klient : listaKlientow)
           {
                writer.write(klient.getLogin() + "," + klient.getEmail() + "," + klient.getHaslo() + "," + klient.getSaldo());
                writer.newLine();
            }
        }
        catch (IOException e)
        {
             e.printStackTrace();
       }
    }

    public static void zapiszPracownikow() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BazaDanych/pracownicy.txt"))) {
            for (Pracownik pracownik : listaPracowikow) {
                writer.write(pracownik.getLogin() + "," + pracownik.getEmail() + "," + pracownik.getHaslo());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void zapiszKierownikow() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BazaDanych/kierownicy.txt"))) {
            for (Kierownik kierownik : listaKierownikow) {
                writer.write(kierownik.getLogin() + "," + kierownik.getEmail() + "," + kierownik.getHaslo());
                writer.newLine();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zapiszProdukty() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BazaDanych/produkty.txt"))) {
            for (Produkt produkt : listaProduktow) {
                writer.write("Kategoria: " + produkt.getInstance() + "," + produkt.nazwaProduktu + "," + produkt.cena + "," + produkt.opis + "," + produkt.liczbaProduktu);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void dodajProdukt() {
    }

    public static void usunProdukt() {
    }

    public static void wystwietlProdukty() {
        for (Produkt produkt : listaProduktow) {
            System.out.println(produkt);
        }
    }

    public static void usunKlienta() {
    }

    public static void wystwietlKlientow() {
        for (Klient klient : listaKlientow) {
            System.out.println(klient);
        }
    }
}







}