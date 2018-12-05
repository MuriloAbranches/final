package br.com.clinicamedica.model;

public class Consulta {

    private int id;
    private Paciente paciente;
    private Medico medico;
    private Especialidade especialidade;
    private DataHoraConsulta dataHoraConsulta;
    private int flagAtivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public DataHoraConsulta getDataHoraConsulta() {
        return dataHoraConsulta;
    }

    public void setDataHoraConsulta(DataHoraConsulta dataHoraConsulta) {
        this.dataHoraConsulta = dataHoraConsulta;
    }

    public int getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(int flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public Consulta() {
    }

    public Consulta(int id, Paciente paciente, Medico medico, Especialidade especialidade, DataHoraConsulta dataHoraConsulta, int flagAtivo) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.especialidade = especialidade;
        this.dataHoraConsulta = dataHoraConsulta;
        this.flagAtivo = flagAtivo;
    }

}
