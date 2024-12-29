package zakupy;

import java.util.ArrayList;
import produkty.Produkty;
import java.io.Serializable;

public class Zakupy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Składowe kłasy
	private ArrayList<Produkty> kupioneProdukty;
	private double cena;
	private double cenaDoZapłaty;

	// Konstruktor
	public Zakupy(ArrayList<Produkty> kupioneProdukty, double cena, double cenaDoZapłaty) {
		this.kupioneProdukty = kupioneProdukty;
		this.cena = cena;
		this.cenaDoZapłaty = cenaDoZapłaty;
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

	public double getCenaDoZapłaty() {
		return cenaDoZapłaty;
	}

	public void setCenaDoZapłaty(double cenaDoZapłaty) {
		this.cenaDoZapłaty = cenaDoZapłaty;
	}

}
