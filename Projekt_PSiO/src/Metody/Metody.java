package Metody;

import osoba.*;
import Produkty.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;

public class Metody{

	public static Scanner scanner = new Scanner(System.in);
	public static boolean czyKoniecProgramu = false;
	public static Osoba aktywnaOsoba = null;
	
    public static ArrayList<Klient> listaKlientow = new ArrayList<>();
    public static ArrayList<Pracownik> listaPracownikow = new ArrayList<>();
    public static ArrayList<Kierownik> listaKierownikow = new ArrayList<>();
    public static ArrayList<Produkty> listaProduktow = new ArrayList<>();


    public static void wczytaj() throws ClassNotFoundException {
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
            Klient klient = (Klient) obj1;
            listaKlientow.add(klient);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }


    public static void wczytajPracownikow() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("BazaDanych/pracownicy.txt"))) {
            Object obj1 = is.readObject();
            Pracownik pracownik = (Pracownik) obj1;
            listaPracownikow.add(pracownik);
        
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

   public static void wczytajKierownikow() throws ClassNotFoundException 
   {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("BazaDanych/kierownicy.txt"))) 
        {
            Object obj1 = is.readObject();
            Kierownik kier = (Kierownik) obj1;
            listaKierownikow.add(kier);
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    
	}
   public static void wczytajProdukty() {
	    File bazaProdukty = new File("BazaDanych/produkty.txt");
	    try (BufferedReader reader = new BufferedReader(new FileReader(bazaProdukty))) {
	        String linia;
	        while ((linia = reader.readLine()) != null) {
	            String[] dane = linia.split(",");
	            String kategoria = dane[0].replace("Kategoria: ", "").trim();

	            // Ignorujemy dane[1] (ID) ponieważ jest generowane automatycznie
	            if (kategoria.equals("Gaming")) {
	                Gaming produkt = new Gaming(
	                    dane[2],                   
	                    Float.parseFloat(dane[3]),  
	                    Integer.parseInt(dane[4]),  
	                    dane[5]                     
	                );
	                listaProduktow.add(produkt);
	            }
	            else if (kategoria.equals("Fotografia")) {
	                Fotografia produkt = new Fotografia(
	                    dane[2],                    
	                    Float.parseFloat(dane[3]),  
	                    Integer.parseInt(dane[4]),  
	                    dane[5]                     
	                );
	                listaProduktow.add(produkt);
	            }
	            else if (kategoria.equals("Mieszane")) {
	                Mieszane produkt = new Mieszane(
	                    dane[2],                    
	                    Float.parseFloat(dane[3]),  
	                    Integer.parseInt(dane[4]),  
	                    dane[5]                     
	                );
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
                writer.write(klient.getLogin() + "," + klient.getEmail() + "," + klient.getHaslo() + "," + klient.getSaldoKonta());
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
            for (Pracownik pracownik : listaPracownikow) {
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
            for (Produkty produkt : listaProduktow) {
                writer.write("Kategoria: " + produkt.getClass().getSimpleName() + "," + produkt.getNazwaProduktu() + "," + produkt.getCenaProduktu() + "," + produkt.getOpisProduktu() + "," + produkt.getLiczbaProduktu());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 


    public static void dodajProdukt() {
    	
    	//WORK IN PROGRESS - w planach zamiast arraylisty produktów zrobić hashmapę, która zapobiegałaby dodawania tych samych produktow sprawdzajac po nazwie
    	
    	System.out.println("Nazwa produktu:");
    	String nazwa = scanner.next();
    	
    	System.out.println("Cena produktu:");
    	float cena = scanner.nextFloat();
    	
    	System.out.println("Liczba produktu:");
    	int liczba = scanner.nextInt();
    	
    	System.out.println("Opis produktu:");
    	String opis = scanner.next();
    	
    	int wybor=0;
    	while (wybor!=1 || wybor!=2 || wybor!=3)
    	{
    		System.out.println("Jaki typ produktu?\n1. Gaming\n2. Fotografia\n3. Mieszane\n(Jezeli widzisz ta wiadomosc ponownie oznacza to, ze dokonales zlego wyboru)");
        	wybor = scanner.nextInt();
    	}
    	
    	switch (wybor)
    	{
    	case 1 -> listaProduktow.add(new Gaming(nazwa, cena, liczba, opis));
    	case 2 -> listaProduktow.add(new Fotografia(nazwa, cena, liczba, opis));
    	case 3 -> listaProduktow.add(new Mieszane(nazwa, cena, liczba, opis));
    	}
    }

    
    public static void usunProdukt() {
    	wystwietlProdukty();
    	System.out.println("Wybierz produkt [id] do usuniecia");
    
    	 int wybor = scanner.nextInt();
         
         Iterator<Produkty> iterator = listaProduktow.iterator();
         
         while (iterator.hasNext()) {
             Produkty produkt = iterator.next();
             if (produkt.getIdProduktu()==wybor) {
                 iterator.remove(); 
                 System.out.println("Produkt o ID " + wybor + " został usunięty.");
                 break; // Jeśli produkt został usunięty, nie ma potrzeby kontynuowania
             }
         }
         
         //a tu będzie metoda, która wywołuje menu, bądż sama metoda menu bedzie zapetlona
    }

    
    public static void wystwietlProdukty() 
    {
        for (Produkty produkt : listaProduktow) {
            System.out.println(produkt);
        }
    } 

    public static void usunKlienta() {
    	wystwietlKlientow();
    	System.out.println("Wybierz klienta [mail] do usuniecia");
    
    	String wybor = scanner.next();
         
         Iterator<Klient> iterator = listaKlientow.iterator();
         
         while (iterator.hasNext()) {
             Klient klient = iterator.next();
             if (klient.getEmail()==wybor) {
                 iterator.remove(); 
                 System.out.println("Osoba o mailu " + wybor + " został(a) usunięty.");
                 break;
             }
         }
         
         //a tu będzie metoda, która wywołuje menu, bądż sama metoda menu bedzie zapetlona
    }



	public static void wystwietlKlientow() {
        for (Klient klient : listaKlientow) {
            System.out.println(klient);
        }
    }
}






