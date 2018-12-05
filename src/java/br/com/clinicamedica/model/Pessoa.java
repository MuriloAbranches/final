package br.com.clinicamedica.model;

public abstract class Pessoa {

    private int id;
    private String nomeCompleto;
    private long cpf;
    private long rg;
    private Contato contato;
    private Endereco endereco;
    private int flagAtivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getRg() {
        return rg;
    }

    public void setRg(long rg) {
        this.rg = rg;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(int flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public Pessoa() {
    }

    public Pessoa(int id, String nomeCompleto, long cpf, long rg, Contato contato, Endereco endereco, int flagAtivo) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.rg = rg;
        this.contato = contato;
        this.endereco = endereco;
        this.flagAtivo = flagAtivo;
    }

}
