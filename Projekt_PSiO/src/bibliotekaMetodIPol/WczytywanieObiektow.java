package bibliotekaMetodIPol;

import java.io.*;
import logowanie.*;
import osoba.*;
import produkty.*;

public class WczytywanieObiektow {

	public static void wczytajDane() {
		wczytajKlientow();
		wczytajPracownikow();
		wczytajKierownikow();
		wczytajProduktyFotografia();
		wczytajProduktyGaming();
		wczytajProduktyMieszane();
		wczytajIDProduktu();
	}

	public static void wczytajSposobLogowania() {
		try (BufferedReader is = new BufferedReader(new FileReader("./BazaDanych/SposobLogowania.txt"))) {
			String sposob = is.readLine();
			is.close();
			if (sposob.equals("email"))
				MenuLogowanie.setPreferowaneLogowanie(new Logowanie_EmailHaslo());
			else
				MenuLogowanie.setPreferowaneLogowanie(new Logowanie_LoginHaslo());

			System.out.println("Sposób logowania został wczytany pomyślnie.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void wczytajIDProduktu() {
		try (BufferedReader is = new BufferedReader(new FileReader("./BazaDanych/IDProduktu.txt"))) {
			try {
				int id = Integer.parseInt(is.readLine());
				Produkty.id = id;
			} catch (NumberFormatException e) {
				System.out.println("Błędna informacja w bazie danych o ID Produktu.");
			}

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void wczytajKlientow() {

		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("./BazaDanych/Klienci.ser"))) {
			while (true) {
				try {
					Object obj1 = is.readObject();
					Metody.getListaKlientow().add((Klient) obj1);

				} catch (EOFException e) { // Pokazuje koniec pliku
					System.out.println("Lista klientów wczytana pomyślnie.");
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
					Metody.getListaOsobZarzadzajacych().add((Pracownik) obj1);

				} catch (EOFException e) { // Pokazuje koniec pliku
					System.out.println("Lista pracowników wczytana pomyślnie.");
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
					Metody.getListaOsobZarzadzajacych().add((Kierownik) obj1);

				} catch (EOFException e) { // Pokazuje koniec pliku
					System.out.println("Lista kierowników wczytana pomyślnie.");
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void wczytajProduktyFotografia() {
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("./BazaDanych/ProduktyFotografia.ser"))) {
			while (true) {
				try {
					Object obj1 = is.readObject();
					Metody.getListaProduktow().add((Fotografia) obj1);

				} catch (EOFException e) { // Pokazuje koniec pliku
					System.out.println("Lista produktów kłasy fotografia wczytana pomyślnie.");
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void wczytajProduktyGaming() {
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("./BazaDanych/ProduktyGaming.ser"))) {
			while (true) {
				try {
					Object obj1 = is.readObject();
					Metody.getListaProduktow().add((Gaming) obj1);

				} catch (EOFException e) { // Pokazuje koniec pliku
					System.out.println("Lista produktów kłasy gaming wczytana pomyślnie.");
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void wczytajProduktyMieszane() {
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("./BazaDanych/ProduktyMieszane.ser"))) {
			while (true) {
				try {
					Object obj1 = is.readObject();
					Metody.getListaProduktow().add((Mieszane) obj1);

				} catch (EOFException e) { // Pokazuje koniec pliku
					System.out.println("Lista produktów kłasy mieszane wczytana pomyślnie.");
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
