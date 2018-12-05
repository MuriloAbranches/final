package br.com.clinicamedica.model;

public class Usuario {
    
    private String login;
    private String senha;
    private PerfilDeAcesso perfil;
    private int flagAtivo;
    private Paciente paciente;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilDeAcesso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDeAcesso perfil) {
        this.perfil = perfil;
    }

     public int getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(int flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Usuario() {
    }

    public Usuario(String login, String senha, PerfilDeAcesso perfil, int flagAtivo) {
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.flagAtivo = flagAtivo;
    }
    
}
