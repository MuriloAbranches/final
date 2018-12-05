package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.Funcionario;
import java.util.List;

public interface FuncionarioDao {

    public boolean createFuncionario(Funcionario funcionario);

    public boolean deleteFuncionario(Funcionario funcionario);

    public Funcionario readFuncionario(Funcionario funcionario);

    public boolean updateFuncionario(Funcionario funcionario);

    public List<Funcionario> listFuncionario();

    public boolean activateFuncionario(Funcionario funcionario);

    public Funcionario findFuncionarioById(Funcionario funcionario);
}
