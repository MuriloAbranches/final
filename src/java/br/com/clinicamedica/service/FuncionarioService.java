package br.com.clinicamedica.service;

import br.com.clinicamedica.model.Funcionario;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FuncionarioService {

    public boolean salvarFuncionario(HttpServletRequest request, HttpServletResponse response);

    public List<Funcionario> listarFuncionario();

    public Funcionario buscarFuncionario(HttpServletRequest request, HttpServletResponse response);

    public boolean alterarFuncionario(HttpServletRequest request, HttpServletResponse response);

    public boolean excluirFuncionario(HttpServletRequest request, HttpServletResponse response);
}
