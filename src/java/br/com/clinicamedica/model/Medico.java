package br.com.clinicamedica.model;

public class Medico extends Funcionario {

    private long crm;
    private Especialidade especialidade;

    public long getCrm() {
        return crm;
    }

    public void setCrm(long crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Medico() {
    }

    public Medico(long crm, Especialidade especialidade,TipoFuncionario cargo) {
        super(cargo);
        this.crm = crm;
        this.especialidade = especialidade;
    }

}
