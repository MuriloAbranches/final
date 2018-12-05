package br.com.clinicamedica.service;

import br.com.clinicamedica.model.Consulta;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ConsultaService {

    public boolean salvarConsulta(HttpServletRequest request, HttpServletResponse response);

    public List<Consulta> listarConsulta();

    public Consulta buscarConsulta(HttpServletRequest request, HttpServletResponse response);

    public boolean alterarConsulta(HttpServletRequest request, HttpServletResponse response);

    public boolean excluirConsulta(HttpServletRequest request, HttpServletResponse response);
}
