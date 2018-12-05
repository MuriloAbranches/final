package br.com.clinicamedica.model;

import java.util.Date;

public class Paciente extends Pessoa {

    private Date dataNascimento;

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Paciente() {
    }

    public Paciente(Date dataNascimento, int id, String nomeCompleto, long cpf, long rg, Contato contato, Endereco endereco, int flagAtivo) {
        super(id, nomeCompleto, cpf, rg, contato, endereco, flagAtivo);
        this.dataNascimento = dataNascimento;
    }

}
