package br.com.clinicamedica.controller;

import br.com.clinicamedica.dao.EspecialidadeDao;
import br.com.clinicamedica.dao.impl.EspecialidadeDaoImpl;
import br.com.clinicamedica.model.Especialidade;
import br.com.clinicamedica.model.Medico;
import br.com.clinicamedica.service.MedicoService;
import br.com.clinicamedica.service.impl.MedicoServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "medico", urlPatterns = {"/medico"})
public class MedicoController extends HttpServlet {

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
            MedicoService medicoService = new MedicoServiceImpl();
            String opcao = request.getParameter("opcao");

            if (opcao.equals("salvar")) {
                medicoService.salvarMedico(request, response);

                response.sendRedirect("administrativo/medicos");

            } else if (opcao.equals("alterar")) {
                medicoService.alterarMedico(request, response);

                response.sendRedirect("administrativo/medicos");

            } else if (opcao.equals("excluir")) {
                medicoService.excluirMedico(request, response);

                response.sendRedirect("administrativo/medicos");

            } else if (opcao.equals("buscar")) {

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

                Medico buscaMedico = medicoService.buscarMedico(request, response);
                request.setAttribute("buscaMedico", buscaMedico);
                request.setAttribute("listaEspecialidades", especialidades);
                RequestDispatcher rd = request.getRequestDispatcher("adminpages/buscaMedicoAdm.jsp");
                rd.forward(request, response);

            }

        } catch (IOException e) {
            System.out.println("ERRO(MedicoController): " + e.getMessage());
            response.sendRedirect("administrativo/medicos");
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
