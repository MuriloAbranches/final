package br.com.clinicamedica.controller;

import br.com.clinicamedica.dao.ConsultaDao;
import br.com.clinicamedica.dao.ContatoDao;
import br.com.clinicamedica.dao.DataHoraConsultaDao;
import br.com.clinicamedica.dao.EspecialidadeDao;
import br.com.clinicamedica.dao.FuncionarioDao;
import br.com.clinicamedica.dao.MedicoDao;
import br.com.clinicamedica.dao.PacienteDao;
import br.com.clinicamedica.dao.impl.ConsultaDaoImpl;
import br.com.clinicamedica.dao.impl.ContatoDaoImpl;
import br.com.clinicamedica.dao.impl.DataHoraConsultaDaoImpl;
import br.com.clinicamedica.dao.impl.EspecialidadeDaoImpl;
import br.com.clinicamedica.dao.impl.FuncionarioDaoImpl;
import br.com.clinicamedica.dao.impl.MedicoDaoImpl;
import br.com.clinicamedica.dao.impl.PacienteDaoImpl;
import br.com.clinicamedica.model.Consulta;
import br.com.clinicamedica.model.Contato;
import br.com.clinicamedica.model.DataHoraConsulta;
import br.com.clinicamedica.model.Especialidade;
import br.com.clinicamedica.model.Funcionario;
import br.com.clinicamedica.model.Medico;
import br.com.clinicamedica.model.Paciente;
import br.com.clinicamedica.model.Telefone;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "administrativo", urlPatterns = {"/administrativo",
    "/administrativo/medicos", "/administrativo/pacientes", "/administrativo/funcionarios",
    "/administrativo/agendas", "/administrativo/consultas"})
public class AdministrativoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String uri = request.getRequestURI();

        if (uri.contains("/pacientes")) {

            PacienteDao pacienteDao = new PacienteDaoImpl();
            List<Paciente> lista = pacienteDao.listPaciente();
            List<Paciente> pacientes = new ArrayList<>();

            if (null != lista) {

                for (Paciente paciente : lista) {

                    ContatoDao contatoDao = new ContatoDaoImpl();
                    Contato contato = contatoDao.readContato(paciente.getContato());
                    paciente.setContato(contato);

                    pacientes.add(paciente);
                }
            } else {

                Paciente paciente = new Paciente();
                paciente.setNomeCompleto("Não Cadastrado");
                paciente.setCpf(new Long(999999));
                paciente.setRg(new Long(999999));

                Telefone telefone = new Telefone();
                telefone.setDdd(99);
                telefone.setNumero(99999999);

                Contato contato = new Contato();
                contato.setEmail("Não Cadastrado");
                contato.setTelefone(telefone);

                paciente.setContato(contato);

                pacientes.add(paciente);
            }

            request.setAttribute("listaPacientes", pacientes);

            RequestDispatcher rd = request.getRequestDispatcher("/adminpages/pacienteAdm.jsp");
            rd.forward(request, response);

        } else if (uri.contains("/medicos")) {

            EspecialidadeDao especialidadeDao = new EspecialidadeDaoImpl();
            List<Especialidade> especialidades = new ArrayList<>();

            MedicoDao medicoDao = new MedicoDaoImpl();
            List<Medico> medicos = new ArrayList<>();
            List<Medico> listaMedicos = new ArrayList<>();

            if (null != especialidadeDao.listEspecialidade()) {

                especialidades = especialidadeDao.listEspecialidade();

            } else {

                Especialidade esp = new Especialidade();
                esp.setId(0);
                esp.setNome("Não Cadastrado");

                especialidades.add(esp);
            }

            if (null != medicoDao.listMedico()) {

                medicos = medicoDao.listMedico();

                for (Medico medico : medicos) {

                    Especialidade especialidade = especialidadeDao.readEspecialidade(medico.getEspecialidade());
                    medico.setEspecialidade(especialidade);

                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(medico.getId());
                    FuncionarioDaoImpl funcionarioDao = new FuncionarioDaoImpl();
                    funcionario = funcionarioDao.findFuncionarioById(funcionario);

                    Contato contato = new Contato();
                    contato.setId(funcionario.getContato().getId());
                    ContatoDaoImpl contatoDao = new ContatoDaoImpl();
                    contato = contatoDao.readContato(contato);

                    medico.setContato(contato);
                    medico.setNomeCompleto(funcionario.getNomeCompleto());

                    listaMedicos.add(medico);
                }
            } else {

                Medico medico = new Medico();
                medico.setNomeCompleto("Não cadastrado");
                medico.setCrm(new Long(999999));
            }

            request.setAttribute("listaMedicos", listaMedicos);
            request.setAttribute("listaEspecialidades", especialidades);
            RequestDispatcher rd = request.getRequestDispatcher("/adminpages/medicoAdm.jsp");
            rd.forward(request, response);

        } else if (uri.contains("/consultas")) {

            ConsultaDao consultaDao = new ConsultaDaoImpl();
            List<Consulta> consultas = consultaDao.listConsulta();
            List<Consulta> listaConsultas = new ArrayList<>();

            if (null != consultas ) {

                consultas = consultaDao.listConsulta();
                
                for(Consulta consulta: consultas){
                    
                    PacienteDao pacienteDao = new PacienteDaoImpl();
                    Paciente paciente = pacienteDao.readPacienteById(consulta.getPaciente());
                    
                    EspecialidadeDao especialidadeDao = new EspecialidadeDaoImpl();
                    Especialidade especialidade = especialidadeDao.readEspecialidade(consulta.getEspecialidade());
                    
                    MedicoDao medicoDao = new MedicoDaoImpl();
                    Medico medico = medicoDao.readMedico(consulta.getMedico());
                    
                    FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(medico.getId());
                    funcionario = funcionarioDao.findFuncionarioById(funcionario);
                    
                    medico.setNomeCompleto(funcionario.getNomeCompleto());
                    
                    DataHoraConsultaDao dataHoraConsultaDao = new DataHoraConsultaDaoImpl();
                    DataHoraConsulta dataHoraConsulta = dataHoraConsultaDao.readDataHoraConsulta(consulta.getDataHoraConsulta());
                    
                    consulta.setDataHoraConsulta(dataHoraConsulta);
                    consulta.setEspecialidade(especialidade);
                    consulta.setMedico(medico);
                    consulta.setPaciente(paciente);
                    
                    listaConsultas.add(consulta);
                }

            } else {

                Consulta consulta = new Consulta();
                consulta.setId(0);

                Paciente paciente = new Paciente();
                paciente.setNomeCompleto("Não Encontrado");

                Especialidade especialidade = new Especialidade();
                especialidade.setNome("Não Encontrado");

                Medico medico = new Medico();
                medico.setNomeCompleto("Não Encontrado");

                consulta.setPaciente(paciente);
                consulta.setEspecialidade(especialidade);
                consulta.setMedico(medico);

                consultas.add(consulta);
            }

            EspecialidadeDao especialidadeDao = new EspecialidadeDaoImpl();
            List<Especialidade> especialidades = new ArrayList<>();

            if (null != especialidadeDao.listEspecialidade()) {

                especialidades = especialidadeDao.listEspecialidade();

            } else {

                Especialidade esp = new Especialidade();
                esp.setId(0);
                esp.setNome("Não Cadastrado");

                especialidades.add(esp);
            }

            request.setAttribute("listaEspecialidades", especialidades);
            request.setAttribute("listaConsultas", consultas);
            RequestDispatcher rd = request.getRequestDispatcher("/adminpages/agendamentoAdm.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
