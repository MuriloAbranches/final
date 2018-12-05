package br.com.clinicamedica.service.impl;

import br.com.clinicamedica.dao.AgendaMedicaDao;
import br.com.clinicamedica.dao.MedicoDao;
import br.com.clinicamedica.dao.impl.AgendaMedicaDaoImpl;
import br.com.clinicamedica.dao.impl.MedicoDaoImpl;
import br.com.clinicamedica.model.AgendaMedica;
import br.com.clinicamedica.model.Funcionario;
import br.com.clinicamedica.model.Medico;
import br.com.clinicamedica.service.AgendaMedicaService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AgendaMedicaServiceImpl implements AgendaMedicaService {

    private AgendaMedicaDao agendaMedicaDao;

    @Override
    public boolean salvarAgenda(HttpServletRequest request, HttpServletResponse response) {

        try {

            Medico medico = new Medico();
            medico.setCrm(Long.parseLong(request.getParameter("buscaCrm")));
            MedicoDao medicoDao = new MedicoDaoImpl();
            medico = medicoDao.readMedico(medico);

            ///////////////////////////////////////////////
            AgendaMedica agendaMedica = new AgendaMedica();

            agendaMedica.setHorarioFim(new Date());// request.getParameter("horarioFim");
            agendaMedica.setHorarioInicio(new Date()); // request.getParameter("horarioInicio");
            agendaMedica.setTempoAtendimento(Integer.parseInt(request.getParameter("tempo")));
            agendaMedica.setEspecialidade(medico.getEspecialidade());
            agendaMedica.setMedico(medico);
            agendaMedica.setDataAgenda(new Date());
            Funcionario func = new Funcionario();//Pegar da session
            func.setCpf(147);// tirar
            agendaMedica.setCriador(func);

            agendaMedicaDao = new AgendaMedicaDaoImpl();
            agendaMedicaDao.createAgendaMedica(agendaMedica);

            request.setAttribute("agenda", "cadastrado");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

            return true;

        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }

    }

    @Override
    public List<AgendaMedica> listarAgenda(AgendaMedica agendaMedica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AgendaMedica buscarAgenda(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterarAgenda(AgendaMedica agendaMedica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirAgenda(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
