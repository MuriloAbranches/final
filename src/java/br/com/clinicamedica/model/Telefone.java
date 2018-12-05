package br.com.clinicamedica.model;

public class Telefone {

    private int id;
    private int ddd;
    private int numero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Telefone() {
    }

    public Telefone(int id, int ddd, int numero) {
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
    }

}
