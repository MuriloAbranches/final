package br.com.clinicamedica.service;

import br.com.clinicamedica.model.Medico;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MedicoService {

    public boolean salvarMedico(HttpServletRequest request, HttpServletResponse response);

    public List<Medico> listarMedico();

    public Medico buscarMedico(HttpServletRequest request, HttpServletResponse response);

    public boolean alterarMedico(HttpServletRequest request, HttpServletResponse response);

    public boolean excluirMedico(HttpServletRequest request, HttpServletResponse response);

    public List<Medico> buscarMedicoPorEspecialidade(HttpServletRequest request, HttpServletResponse response);
}
