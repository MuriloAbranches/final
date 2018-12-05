package br.com.clinicamedica.service;

import br.com.clinicamedica.model.AgendaMedica;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AgendaMedicaService {

    public boolean salvarAgenda(HttpServletRequest request, HttpServletResponse response);

    public List<AgendaMedica> listarAgenda(AgendaMedica agendaMedica);

    public AgendaMedica buscarAgenda(int id);

    public boolean alterarAgenda(AgendaMedica agendaMedica);

    public boolean excluirAgenda(int id);
}
