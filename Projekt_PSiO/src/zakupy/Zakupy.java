package zakupy;

import java.util.ArrayList;
import produkty.Produkty;
import java.io.Serializable;

public class Zakupy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Składowe kłasy
	private ArrayList<Produkty> kupioneProdukty;
	private double cena;
	private double cenaDoZaplaty;

	// Konstruktor
	public Zakupy(ArrayList<Produkty> kupioneProdukty, double cena, double cenaDoZaplaty) {
		this.kupioneProdukty = kupioneProdukty;
		this.cena = cena;
		this.cenaDoZaplaty = cenaDoZaplaty;
	}

	// Getters and Setters
	public ArrayList<Produkty> getKupioneProdukty() {
		return kupioneProdukty;
	}

	public void setKupioneProdukty(ArrayList<Produkty> kupioneProdukty) {
		this.kupioneProdukty = kupioneProdukty;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getCenaDoZaplaty() {
		return cenaDoZaplaty;
	}

	public void setCenaDoZaplaty(double cenaDoZaplaty) {
		this.cenaDoZaplaty = cenaDoZaplaty;
	}

}
