package br.com.clinicamedica.controller;

import br.com.clinicamedica.dao.DataHoraConsultaDao;
import br.com.clinicamedica.dao.MedicoDao;
import br.com.clinicamedica.dao.impl.DataHoraConsultaDaoImpl;
import br.com.clinicamedica.dao.impl.MedicoDaoImpl;
import br.com.clinicamedica.model.Consulta;
import br.com.clinicamedica.model.DataHoraConsulta;
import br.com.clinicamedica.model.Medico;
import br.com.clinicamedica.model.Paciente;
import br.com.clinicamedica.service.ConsultaService;
import br.com.clinicamedica.service.MedicoService;
import br.com.clinicamedica.service.PacienteService;
import br.com.clinicamedica.service.impl.ConsultaServiceImpl;
import br.com.clinicamedica.service.impl.MedicoServiceImpl;
import br.com.clinicamedica.service.impl.PacienteServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "consulta", urlPatterns = {"/consulta"})
public class ConsultaController extends HttpServlet {

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
        try {
            ConsultaService consultaService = new ConsultaServiceImpl();
            MedicoService medicoService = new MedicoServiceImpl();
            PacienteService pacienteService = new PacienteServiceImpl();
            String opcao = request.getParameter("opcao");

            if (opcao.equals("salvar")) {
                consultaService.salvarConsulta(request, response);
                response.sendRedirect("administrativo/consultas");

            } else if (opcao.equals("alterar")) {
                consultaService.alterarConsulta(request, response);

                response.sendRedirect("administrativo/consultas");

            } else if (opcao.equals("excluir")) {
                consultaService.excluirConsulta(request, response);

                response.sendRedirect("administrativo/consultas");

            } else if (opcao.equals("buscarPaciente")) {

                Paciente buscaPaciente = pacienteService.buscarPaciente(request, response);

                List<Medico> listaMedicos = medicoService.buscarMedicoPorEspecialidade(request, response);

                DataHoraConsultaDao dataHoraConsultaDao = new DataHoraConsultaDaoImpl();
                List<DataHoraConsulta> listaDataHora = dataHoraConsultaDao.readDataHoraConsultaByFlagAtivo();

                String especialidade = request.getParameter("especialidades");
                request.setAttribute("buscaPaciente", buscaPaciente);
                request.setAttribute("listaMedicos", listaMedicos);
                request.setAttribute("listaDataHora", listaDataHora);
                request.setAttribute("especialidade", especialidade);
                RequestDispatcher rd = request.getRequestDispatcher("adminpages/agendarConsulta.jsp");

                rd.forward(request, response);

            } else if (opcao.equals("buscar")) {

                Consulta buscaConsulta = consultaService.buscarConsulta(request, response);
                MedicoDao medicoDao = new MedicoDaoImpl();
                List<Medico> listaMedicos = medicoDao.listMedicoByEspecialidade(buscaConsulta.getEspecialidade());
                
                DataHoraConsultaDao dataHoraConsultaDao = new DataHoraConsultaDaoImpl();
                List<DataHoraConsulta> listaDataHora = dataHoraConsultaDao.readDataHoraById(buscaConsulta.getDataHoraConsulta());
                
                request.setAttribute("buscaConsulta", buscaConsulta);
                request.setAttribute("listaMedicos", listaMedicos);
                request.setAttribute("listaDataHora", listaDataHora);
                RequestDispatcher rd = request.getRequestDispatcher("adminpages/buscaConsulta.jsp");

                rd.forward(request, response);
            }
            
        } catch (Exception e) {
            System.out.println("ERRO(ConsultaController): " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("administrativo/consultas");
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
