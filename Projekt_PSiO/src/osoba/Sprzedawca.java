package Osoba;

import Adres.Adres;

public class Sprzedawca extends Osoba {

    float sredniaOcena;
    String dataZalozeniaKonta;

    public Sprzedawca(String imieNazwisko, int wiek, Adres adres, String email, double saldoKonta, float sredniaOcena, String dataZalozeniaKonta) {
        super(imieNazwisko, wiek, adres, email, saldoKonta);
        this.sredniaOcena = sredniaOcena;
        this.dataZalozeniaKonta = dataZalozeniaKonta;
    }

    public float getSredniaOcena() {
        return sredniaOcena;
    }

    public void setSredniaOcena(float sredniaOcena) {
        this.sredniaOcena = sredniaOcena;
    }

    public String getDataZalozeniaKonta() {
        return dataZalozeniaKonta;
    }

    public void setDataZalozeniaKonta(String dataZalozeniaKonta) {
        this.dataZalozeniaKonta = dataZalozeniaKonta;
    }

    public String toString() {
        return "Sprzedawca" +
                "sredniaOcena=" + sredniaOcena +
                ", dataZalozeniaKonta='" + dataZalozeniaKonta + '\'' +
                '}';
    }


    public void wystaw(){}
    public void zmienCeneOferty{}
    public void zakonczOferte{}
    


}


