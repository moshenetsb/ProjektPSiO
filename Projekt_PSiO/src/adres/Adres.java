package adres;

public class Adres {

	// Składowe kłasy
	private int numerBudynku;
	private String ulica;
	private String miasto;
	private String region;
	private String kodPocztowy;
	private String panstwo;

	// Konstruktor
	public Adres(int numerBudynku, String ulica, String miasto, String region, String kodPocztowy, String panstwo) {
		this.numerBudynku = numerBudynku;
		this.ulica = ulica;
		this.miasto = miasto;
		this.region = region;
		this.kodPocztowy = kodPocztowy;
		this.panstwo = panstwo;
	}

	// Getters and Setters
	public int getNumerBudynku() {
		return numerBudynku;
	}

	public void setNumerBudynku(int numerBudynku) {
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

	public String getKodPocztowy() {
		return kodPocztowy;
	}

	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	public String getPanstwo() {
		return panstwo;
	}

	public void setPanstwo(String panstwo) {
		this.panstwo = panstwo;
	}

	// toString
	@Override
	public String toString() {
		return "Adres [ul. " + ulica + ", nr. bud. " + numerBudynku + ", m. " + miasto + ", region " + region
				+ ", państwo " + panstwo + ", kod pocztowy " + kodPocztowy + "]";
	}