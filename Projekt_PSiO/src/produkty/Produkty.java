package produkty;

public abstract class Produkty{

	private static int id=1;

	private int idProduktu;
	private String nazwaProduktu;
	private float cenaProduktu;
	private int liczbaProduktu;
	private String opisProduktu;
	
	public Produkty(String nazwaProduktu, float cenaProduktu, int liczbaProduktu, String opisProduktu) {
		id++;
		this.nazwaProduktu=id;
		this.nazwaProduktu = nazwaProduktu;
		this.cenaProduktu = cenaProduktu;
		this.liczbaProduktu = liczbaProduktu;
		this.opisProduktu = opisProduktu;
	}

	public int getIdProduktu() {
		return idProduktu;
	}

	public void setIdProduktu(int idProduktu) {
		this.idProduktu = idProduktu;
	}

	public String getNazwaProduktu() {
		return nazwaProduktu;
	}
	public void setNazwaProduktu(String nazwaProduktu) {
		this.nazwaProduktu = nazwaProduktu;
	}
	public float getCenaProduktu() {
		return cenaProduktu;
	}
	public void setCenaProduktu(float cenaProduktu) {
		this.cenaProduktu = cenaProduktu;
	}
	public int getLiczbaProduktu() {
		return liczbaProduktu;
	}
	public void setLiczbaProduktu(int liczbaProduktu) {
		this.liczbaProduktu = liczbaProduktu;
	}
	public String getOpisProduktu() {
		return opisProduktu;
	}
	public void setOpisProduktu(String opisProduktu) {
		this.opisProduktu = opisProduktu;
	}


	public String toString() {
		return "Id: " + getIdProduktu() + "Produkty [nazwaProduktu=" + getNazwaProduktu() + ", cenaProduktu=" + getCenaProduktu() + ", liczbaProduktu="
				+ getLiczbaProduktu() + ", opisProduktu=" + getOpisProduktu();
	}
	
	

	
}
