package br.com.clinicamedica.model;

public class Contato {

    private int id;
    private String email;
    private Telefone telefone;
    private Pessoa pessoa;
    private int flagAtivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public int getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(int flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public Contato() {
    }

    public Contato(int id, String email, Telefone telefone) {
        this.id = id;
        this.email = email;
        this.telefone = telefone;
    }

}
