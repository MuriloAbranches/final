package br.com.clinicamedica.service;

import br.com.clinicamedica.model.Paciente;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PacienteService {

    public boolean salvarPaciente(HttpServletRequest request, HttpServletResponse response);

    public List<Paciente> listarPaciente();

    public Paciente buscarPaciente(HttpServletRequest request, HttpServletResponse response);

    public boolean alterarPaciente(HttpServletRequest request, HttpServletResponse response);

    public boolean excluirPaciente(HttpServletRequest request, HttpServletResponse response);
}
