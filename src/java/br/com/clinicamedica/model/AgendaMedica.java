package br.com.clinicamedica.model;

import java.util.Date;

public class AgendaMedica {

    private int id;
    private Date dataAgenda;
    private Date horarioInicio;
    private Date horarioFim;
    private int tempoAtendimento;
    private Medico medico;
    private Funcionario criador;
    private Especialidade especialidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(Date dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(Date horarioFim) {
        this.horarioFim = horarioFim;
    }

    public int getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(int tempoAtendimento) {
        this.tempoAtendimento = tempoAtendimento;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Funcionario getCriador() {
        return criador;
    }

    public void setCriador(Funcionario criador) {
        this.criador = criador;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public AgendaMedica() {
    }

    public AgendaMedica(int id, Date dataAgenda, Date horarioInicio, Date horarioFim, int tempoAtendimento, Medico medico, Funcionario criador, Especialidade especialidade) {
        this.id = id;
        this.dataAgenda = dataAgenda;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.tempoAtendimento = tempoAtendimento;
        this.medico = medico;
        this.criador = criador;
        this.especialidade = especialidade;
    }

}
