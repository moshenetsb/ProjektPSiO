package bibliotekaMetodIPol;

import java.io.*;
import logowanie.*;
import osoba.*;
import produkty.*;

public class ZapisywanieObiektow {

	public static void zapiszDane() {
		zapiszKlientow();
		zapiszPracownikow();
		zapiszKierownikow();
		zapiszProduktyFotografia();
		zapiszProduktyGaming();
		zapiszProduktyMieszane();
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
			for (Klient klient : Metody.getListaKlientow()) {
				writeob.writeObject(klient);
			}

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

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
