package Adres;

public class Adres {
	private String ulica, numerBudynku,miasto,region,panstwo;
	int kodPocztowy;
	
	
	public Adres(String ulica,String numerBudynku,String miasto,String region,String panstwo,int kodPocztowy) {
		this.ulica=ulica;
		this.numerBudynku=numerBudynku;
		this.miasto=miasto;
		this.region=region;
		this.panstwo=panstwo;
		
	}
	
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getNumerBudynku() {
		return numerBudynku;
	}
	public void setNumerBudynku(String numerBudynku) {
		this.numerBudynku = numerBudynku;
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
	public int getKodPocztowy() {
		return kodPocztowy;
	}
	public void setKodPocztowy(int kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	@Override
	public String toString() {
		return "Adres [ulica=" + ulica + ", numerBudynku=" + numerBudynku + ", miasto=" + miasto + ", region=" + region
				+ ", panstwo=" + panstwo + ", kodPocztowy=" + kodPocztowy + "]";
	}
	


}
