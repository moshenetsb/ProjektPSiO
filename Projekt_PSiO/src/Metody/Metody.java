package Metody;

import osoba.*;
import Produkty.*;
import Logowanie.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Metody {

	// Pola_statyczne______________________________________________________________________________
	public static String aktywnaOsobaDostep;
	public static int idAktywnejOsoby;

	public static ArrayList<OsobaZarzadzajaca> listaOsobZarzadzajacych = new ArrayList<>();
	public static ArrayList<Klient> listaKlientow = new ArrayList<>();
	public static ArrayList<Produkty> listaProduktow = new ArrayList<>();

	// _Wczytywanie_obiektów_______________________________________________________________________

	public static void wczytajDane() {
		wczytajKlientow();
		wczytajPracownikow();
		wczytajKierownikow();
		// wczytajProdukty();
	}

	public static void wczytajSposobLogowania() {
		try (BufferedReader is = new BufferedReader(new FileReader("./BazaDanych/SposobLogowania.txt"))) {
			String sposob = is.readLine();
			is.close();
			if (sposob.equals("email"))
				MenuLogowanie.setPreferowaneLogowanie(new Logowanie_EmailHaslo());
			else
				MenuLogowanie.setPreferowaneLogowanie(new Logowanie_LoginHaslo());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void wczytajKlientow() {

		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("./BazaDanych/Klienci.ser"))) {
			while (true) {
				try {
					Object obj1 = is.readObject();
					listaKlientow.add((Klient) obj1);

				} catch (EOFException e) { // Pokazuje koniec pliku
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void wczytajPracownikow() {

		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("./BazaDanych/Pracownicy.ser"))) {
			while (true) {
				try {
					Object obj1 = is.readObject();
					listaOsobZarzadzajacych.add((Pracownik) obj1);

				} catch (EOFException e) { // Pokazuje koniec pliku
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void wczytajKierownikow() {

		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("./BazaDanych/Kierownicy.ser"))) {
			while (true) {
				try {
					Object obj1 = is.readObject();
					listaOsobZarzadzajacych.add((Kierownik) obj1);

				} catch (EOFException e) { // Pokazuje koniec pliku
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void wczytajProdukty() { File bazaProdukty = new
	 * File("BazaDanych/produkty.txt"); try (BufferedReader reader = new
	 * BufferedReader(new FileReader(bazaProdukty))) { String linia; while ((linia =
	 * reader.readLine()) != null) { String[] dane = linia.split(",");
	 * 
	 * if dane[0] == "Kategoria: gaming" { Gaming produkt = new Gaming(dane[1],
	 * Double.parseDouble(dane[2]), dane[3], Integer.parseInt(dane[4]));
	 * listaProduktow.add(produkt); }
	 * 
	 * else if dane[0] == "Kategoria: fotografia" { Fotografia produkt = new
	 * Fotografia(dane[1], Double.parseDouble(dane[2]), dane[3],
	 * Integer.parseInt(dane[4])); listaProduktow.add(produkt); }
	 * 
	 * else { Mieszane produkt = new Mieszane(dane[1], Double.parseDouble(dane[2]),
	 * dane[3], Integer.parseInt(dane[4])); listaProduktow.add(produkt); } } } catch
	 * (IOException e) { e.printStackTrace(); } }
	 */

	// _Zapisywanie_obiektów________________________________________________________________________

	public static void zapiszDane() {
		zapiszKlientow();
		zapiszPracownikow();
		zapiszKierownikow();
		// zapiszProdukty();
	}

	public static void zapiszSposobLogowania() {
		try (BufferedWriter write = new BufferedWriter(new FileWriter("./BazaDanych/SposobLogowania.txt"))) {
			if (MenuLogowanie.getPreferowaneLogowanie() instanceof Logowanie_LoginHaslo) {
				write.write("login");
			} else {
				write.write("email");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void zapiszKlientow() {

		try (ObjectOutputStream writeob = new ObjectOutputStream(new FileOutputStream("./BazaDanych/Klienci.ser"))) {
			for (Klient klient : listaKlientow) {
				writeob.writeObject(klient);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void zapiszPracownikow() {

		try (ObjectOutputStream writeob = new ObjectOutputStream(new FileOutputStream("./BazaDanych/Pracownicy.ser"))) {
			for (OsobaZarzadzajaca osobaZarzadzajaca : listaOsobZarzadzajacych) {
				if (osobaZarzadzajaca instanceof Pracownik)
					writeob.writeObject(osobaZarzadzajaca);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void zapiszKierownikow() {

		try (ObjectOutputStream writeob = new ObjectOutputStream(new FileOutputStream("./BazaDanych/Kierownicy.ser"))) {
			for (OsobaZarzadzajaca osobaZarzadzajaca : listaOsobZarzadzajacych) {
				if (osobaZarzadzajaca instanceof Kierownik)
					writeob.writeObject(osobaZarzadzajaca);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * public static void zapiszProdukty() { try (BufferedWriter writer = new
	 * BufferedWriter(new FileWriter("BazaDanych/produkty.txt"))) { for (Produkt
	 * produkt : listaProduktow) { writer.write("Kategoria: " +
	 * produkt.getInstance() + "," + produkt.nazwaProduktu + "," + produkt.cena +
	 * "," + produkt.opis + "," + produkt.liczbaProduktu); writer.newLine(); } }
	 * catch (IOException e) { e.printStackTrace(); } }
	 */

	/*
	 * 
	 * public static void wystwietlProdukty() { for (Produkt produkt :
	 * listaProduktow) { System.out.println(produkt); } }
	 */

	public static void wystwietlKlientow() {
		for (Klient klient : listaKlientow) {
			System.out.println(klient);
		}
	}
}
