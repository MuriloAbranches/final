package br.com.clinicamedica.model;

public class Funcionario extends Pessoa {

    private TipoFuncionario cargo;

    public TipoFuncionario getCargo() {
        return cargo;
    }

    public void setCargo(TipoFuncionario cargo) {
        this.cargo = cargo;
    }

    public Funcionario() {
    }

    public Funcionario(TipoFuncionario cargo) {

        this.cargo = cargo;
    }

}
