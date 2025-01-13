package main;

import java.util.ArrayList;
import bibliotekaMetodIPol.*;
import inneGUI.*;
import osoba.*;
import produkty.*;
import zakupy.*;
import promocjaStrategia.*;
import loteria.*;

public class Main {

	public static void main(String[] args) {
		start();

	}

	private static void start() {
		WczytywanieObiektow.wczytajDane();

		// Pierwsze konto w sklepie, jeśli jeszcze nie było (admin)
		if (Metody.getListaOsobZarzadzajacych().isEmpty())
			Metody.getListaOsobZarzadzajacych().add(new Kierownik("admin@gmail.com", "admin", "admin", "Nazwisko",
					"Imie", 18, null, 0, "000000000000"));

		// testoweDane();

		@SuppressWarnings("unused")
		GUImain oknoGlowne = new GUImain();

	}

	private static void testoweDane() {
		Metody.setLoteria(new Loteria(1, 5, 50.0, 200.0));
		
		// Osoby
		Metody.getListaKlientow().add(new Klient("klient1@gmail.com", "haslo1", "klogin1", "Ivanov", "Ivan", 18, null,
				2334, new PromocjaStudenta(), new ArrayList<>(), new Zakupy()));
		Metody.getListaKlientow().add(new Klient("klient2@gmail.com", "haslo2", "klogin2", "Ivanova", "Julia", 57, null,
				4.45, new PromocjaStalegoKlienta(), new ArrayList<>(), new Zakupy()));
		Metody.getListaKlientow().add(new Klient("klient3@gmail.com", "haslo3", "klogin3", "Melnyk", "Adam", 86, null,
				100, new PromocjaPodstawowa(), new ArrayList<>(), new Zakupy()));
		Metody.getListaKlientow().add(new Klient("klient4@gmail.com", "haslo4", "klogin4", "Stachewicz", "Dagmara", 25, null,
				8766, new PromocjaStudenta(), new ArrayList<>(), new Zakupy()));
		Metody.getListaKlientow().add(new Klient("klient5@gmail.com", "haslo5", "klogin5", "Jasina", "Gabriel", 19, null,
				45, new PromocjaPodstawowa(), new ArrayList<>(), new Zakupy()));
		Metody.getListaKlientow().add(new Klient("klient6@gmail.com", "haslo6", "klogin6", "Bartecka", "Mariola", 45, null,
				999, new PromocjaStalegoKlienta(), new ArrayList<>(), new Zakupy()));
		Metody.getListaKlientow().add(new Klient("klient7@gmail.com", "haslo7", "klogin7", "Adamkiewicz", "Szymon", 23, null,
				10000, new PromocjaPodstawowa(), new ArrayList<>(), new Zakupy()));
		Metody.getListaKlientow().add(new Klient("klient8@gmail.com", "haslo8", "klogin8", "Roszkowski", "Edmund", 54, null,
				765, new PromocjaStalegoKlienta(), new ArrayList<>(), new Zakupy()));

		Metody.getListaOsobZarzadzajacych().add(new Pracownik("pracownik1@gmail.com", "1234", "plogin1", "Poręba",
				"Piotr", 25, null, 200.0, "423432324324"));
		Metody.getListaOsobZarzadzajacych().add(new Pracownik("pracownik2@gmail.com", "12345", "plogin2", "Lenkiewicz",
				"Oleksandr", 28, null, 900.0, "42376232432"));
		Metody.getListaOsobZarzadzajacych().add(new Pracownik("pracownik3@gmail.com", "123456", "plogin3", "Witecka",
				"Amelia", 22, null, 100.0, "24343232432"));
		Metody.getListaOsobZarzadzajacych().add(new Pracownik("pracownik4@gmail.com", "1234567", "plogin4", "Vova",
				"Antoni", 27, null, 700.0, "98343232432"));
		Metody.getListaOsobZarzadzajacych().add(new Pracownik("pracownik5@gmail.com", "1234567", "plogin5", "Kotok",
				"Julia", 18, null, 1100.0, "27343232432"));

		// Produkty
		Metody.getListaProduktow().add(new Gaming("PS5", 3000, 7, null));
		Metody.getListaProduktow().add(new Gaming("PS4", 2000, 4, null));
		Metody.getListaProduktow().add(new Gaming("Nintendo Switch", 1500, 3, null));
		Metody.getListaProduktow().add(new Fotografia("Lustrzanka Canon", 1700, 2, null));
		Metody.getListaProduktow().add(new Fotografia("Papier fotograficzny", 85, 22, null));
		Metody.getListaProduktow().add(new Fotografia("Karta pamięci sd 64gb", 80, 20, null));
		Metody.getListaProduktow().add(new Mieszane("Gamingowa drukarka HP", 800, 7, null));
		Metody.getListaProduktow().add(new Mieszane("Aparat w stylu Naruto", 1400, 5, null));
		Metody.getListaProduktow().add(new Mieszane("Etui do aparatu Hideokojima Edition", 500, 2, null));
		Metody.getListaProduktow().add(new Gaming("PS5", 3000, 7, null));
		Metody.getListaProduktow().add(new Gaming("PS4", 2000, 4, null));
		Metody.getListaProduktow().add(new Gaming("Nintendo Switch", 1500, 3, null));
		Metody.getListaProduktow().add(new Fotografia("Lustrzanka Canon", 1700, 2, null));
		Metody.getListaProduktow().add(new Fotografia("Papier fotograficzny", 85, 22, null));
		Metody.getListaProduktow().add(new Fotografia("Karta pamięci sd 64gb", 80, 20, null));
		Metody.getListaProduktow().add(new Mieszane("Gamingowa drukarka HP", 800, 7, null));
		Metody.getListaProduktow().add(new Mieszane("Aparat w stylu Naruto", 1400, 5, null));
		Metody.getListaProduktow().add(new Mieszane("Etui do aparatu Hideokojima Edition", 500, 2, null));
		Metody.getListaProduktow().add(new Gaming("PS5", 3000, 7, null));
		Metody.getListaProduktow().add(new Gaming("PS4", 2000, 4, null));
		Metody.getListaProduktow().add(new Gaming("Nintendo Switch", 1500, 3, null));
		Metody.getListaProduktow().add(new Fotografia("Lustrzanka Canon", 1700, 2, null));
		Metody.getListaProduktow().add(new Fotografia("Papier fotograficzny", 85, 22, null));
		Metody.getListaProduktow().add(new Fotografia("Karta pamięci sd 64gb", 80, 20, null));
		Metody.getListaProduktow().add(new Mieszane("Gamingowa drukarka HP", 800, 7, null));
		Metody.getListaProduktow().add(new Mieszane("Aparat w stylu Naruto", 1400, 5, null));
		Metody.getListaProduktow().add(new Mieszane("Etui do aparatu Hideokojima Edition", 500, 2, null));
		Metody.getListaProduktow().add(new Gaming("PS5", 3000, 7, null));
		Metody.getListaProduktow().add(new Gaming("PS4", 2000, 4, null));
		Metody.getListaProduktow().add(new Gaming("Nintendo Switch", 1500, 3, null));
		Metody.getListaProduktow().add(new Fotografia("Lustrzanka Canon", 1700, 2, null));
		Metody.getListaProduktow().add(new Fotografia("Papier fotograficzny", 85, 22, null));
		Metody.getListaProduktow().add(new Fotografia("Karta pamięci sd 64gb", 80, 20, null));
		Metody.getListaProduktow().add(new Mieszane("Gamingowa drukarka HP", 800, 7, null));
		Metody.getListaProduktow().add(new Mieszane("Aparat w stylu Naruto", 1400, 5, null));
		Metody.getListaProduktow().add(new Mieszane("Etui do aparatu Hideokojima Edition", 500, 2, null));
		Metody.getListaProduktow().add(new Gaming("PS5", 3000, 7, null));
		Metody.getListaProduktow().add(new Gaming("PS4", 2000, 4, null));
		Metody.getListaProduktow().add(new Gaming("Nintendo Switch", 1500, 3, null));
		Metody.getListaProduktow().add(new Fotografia("Lustrzanka Canon", 1700, 2, null));
		Metody.getListaProduktow().add(new Fotografia("Papier fotograficzny", 85, 22, null));
		Metody.getListaProduktow().add(new Fotografia("Karta pamięci sd 64gb", 80, 20, null));
		Metody.getListaProduktow().add(new Mieszane("Gamingowa drukarka HP", 800, 7, null));
		Metody.getListaProduktow().add(new Mieszane("Aparat w stylu Naruto", 1400, 5, null));
		Metody.getListaProduktow().add(new Mieszane("Etui do aparatu Hideokojima Edition", 500, 2, null));
		Metody.getListaProduktow().add(new Gaming("PS5", 3000, 7, null));
		Metody.getListaProduktow().add(new Gaming("PS4", 2000, 4, null));
		Metody.getListaProduktow().add(new Gaming("Nintendo Switch", 1500, 3, null));
		Metody.getListaProduktow().add(new Fotografia("Lustrzanka Canon", 1700, 2, null));
		Metody.getListaProduktow().add(new Fotografia("Papier fotograficzny", 85, 22, null));
		Metody.getListaProduktow().add(new Fotografia("Karta pamięci sd 64gb", 80, 20, null));
		Metody.getListaProduktow().add(new Mieszane("Gamingowa drukarka HP", 800, 7, null));
		Metody.getListaProduktow().add(new Mieszane("Aparat w stylu Naruto", 1400, 5, null));
		Metody.getListaProduktow().add(new Mieszane("Etui do aparatu Hideokojima Edition", 500, 2, null));

	}

}
