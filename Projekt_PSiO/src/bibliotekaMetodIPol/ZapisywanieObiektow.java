package bibliotekaMetodIPol;

import java.io.*;
import javax.swing.*;
import logowanie.*;
import osoba.*;
import produkty.*;

public class ZapisywanieObiektow {

	public static void zapiszDane(JFrame frame1) {
		zapiszKlientow();
		zapiszPracownikow();
		zapiszKierownikow();
		zapiszProduktyFotografia();
		zapiszProduktyGaming();
		zapiszProduktyMieszane();
		zapiszIDProoduktu();
		JOptionPane.showMessageDialog(frame1, "Zmiany zostały zapisane", "Informacja zapisywania danych",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void zapiszSposobLogowania() {
		try (BufferedWriter write = new BufferedWriter(new FileWriter("./BazaDanych/SposobLogowania.txt"))) {
			if (MenuLogowanie.getPreferowaneLogowanie() instanceof Logowanie_LoginHaslo) {
				write.write("login");
			} else {
				write.write("email");
			}
			System.out.println("Sposób logowania został zapisany pomyślnie.");
			write.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void zapiszIDProoduktu() {
		try (BufferedWriter write = new BufferedWriter(new FileWriter("./BazaDanych/IDProduktu.txt"))) {
			write.write(Produkty.id + "");
			write.close();
			System.out.println("ID produktu zapisano pomyślnie.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void zapiszKlientow() {

		try (ObjectOutputStream writeob = new ObjectOutputStream(new FileOutputStream("./BazaDanych/Klienci.ser"))) {
			for (Klient klient : Metody.getListaKlientow()) {
				writeob.writeObject(klient);
			}
			System.out.println("Lista klientów zapisana pomyślnie.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void zapiszPracownikow() {

		try (ObjectOutputStream writeob = new ObjectOutputStream(new FileOutputStream("./BazaDanych/Pracownicy.ser"))) {
			for (OsobaZarzadzajaca osobaZarzadzajaca : Metody.getListaOsobZarzadzajacych()) {
				if (osobaZarzadzajaca instanceof Pracownik)
					writeob.writeObject(osobaZarzadzajaca);
			}
			System.out.println("Lista pracowników zapisana pomyślnie.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void zapiszKierownikow() {

		try (ObjectOutputStream writeob = new ObjectOutputStream(new FileOutputStream("./BazaDanych/Kierownicy.ser"))) {
			for (OsobaZarzadzajaca osobaZarzadzajaca : Metody.getListaOsobZarzadzajacych()) {
				if (osobaZarzadzajaca instanceof Kierownik)
					writeob.writeObject(osobaZarzadzajaca);
			}
			System.out.println("Lista kierowników zapisana pomyślnie.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void zapiszProduktyFotografia() {
		try (ObjectOutputStream writeob = new ObjectOutputStream(
				new FileOutputStream("./BazaDanych/ProduktyFotografia.ser"))) {
			for (Produkty produkt : Metody.getListaProduktow()) {
				if (produkt instanceof Fotografia)
					writeob.writeObject(produkt);
			}
			System.out.println("Lista produktów kłasy fotografia zapisana pomyślnie.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void zapiszProduktyGaming() {
		try (ObjectOutputStream writeob = new ObjectOutputStream(
				new FileOutputStream("./BazaDanych/ProduktyGaming.ser"))) {
			for (Produkty produkt : Metody.getListaProduktow()) {
				if (produkt instanceof Gaming)
					writeob.writeObject(produkt);
			}
			System.out.println("Lista produktów kłasy gaming zapisana pomyślnie.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void zapiszProduktyMieszane() {
		try (ObjectOutputStream writeob = new ObjectOutputStream(
				new FileOutputStream("./BazaDanych/ProduktyMieszane.ser"))) {
			for (Produkty produkt : Metody.getListaProduktow()) {
				if (produkt instanceof Mieszane)
					writeob.writeObject(produkt);
			}
			System.out.println("Lista produktów kłasy mieszane zapisana pomyślnie.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
