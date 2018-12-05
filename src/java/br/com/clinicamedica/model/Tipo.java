package br.com.clinicamedica.model;

public abstract class Tipo {
    private String cargo;

    public Tipo() {
    }

    public Tipo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
