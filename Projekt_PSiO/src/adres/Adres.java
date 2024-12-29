package adres;

import java.io.Serializable;

public class Adres implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Składowe kłasy
	private String panstwo;
	private String kodPocztowy;
	private String region;
	private String miasto;
	private String ulica;
	private String numerBudynku;

	// Konstruktor
	public Adres(String panstwo, String kodPocztowy, String region, String miasto, String ulica, String numerBudynku) {
		this.panstwo = panstwo;
		this.kodPocztowy = kodPocztowy;
		this.region = region;
		this.miasto = miasto;
		this.ulica = ulica;
		this.numerBudynku = numerBudynku;
	}

	// Getters and Setters
	public String getNumerBudynku() {
		return numerBudynku;
	}

	public void setNumerBudynku(String numerBudynku) {
		this.numerBudynku = numerBudynku;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getMiasto() {
		return miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPanstwo() {
		return panstwo;
	}

	public void setPanstwo(String panstwo) {
		this.panstwo = panstwo;
	}

	public String getKodPocztowy() {
		return kodPocztowy;
	}

	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	@Override
	public String toString() {
		return "Adres [Państwo: " + panstwo + ", Kod pocztowy: " + kodPocztowy + ", Region: " + region + ", Miasto: "
				+ miasto + ", Ulica: " + ulica + ", Numer budynku=" + numerBudynku + "]";
	}

}
