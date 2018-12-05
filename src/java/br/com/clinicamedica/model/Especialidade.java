package br.com.clinicamedica.model;

public class Especialidade {

    private int id;
    private String nome;
    private int flagAtivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(int flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public Especialidade() {
    }

    public Especialidade(int id, String nome, int flagAtivo) {
        this.id = id;
        this.nome = nome;
        this.flagAtivo = flagAtivo;
    }

}
