package br.com.clinicamedica.model;

public class Email {

    private String nome;
    private String email;
    private String dataHoraMarcada;
    private String medico;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataHoraMarcada() {
        return dataHoraMarcada;
    }

    public void setDataHoraMarcada(String dataHoraMarcada) {
        this.dataHoraMarcada = dataHoraMarcada;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Email() {
    }

    public Email(String nome, String email, String dataHoraMarcada, String medico, String senha) {
        this.nome = nome;
        this.email = email;
        this.dataHoraMarcada = dataHoraMarcada;
        this.medico = medico;
        this.senha = senha;
    }
}
