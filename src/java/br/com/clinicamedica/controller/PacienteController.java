package br.com.clinicamedica.controller;

import br.com.clinicamedica.model.Paciente;
import br.com.clinicamedica.service.PacienteService;
import br.com.clinicamedica.service.impl.PacienteServiceImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "paciente", urlPatterns = {"/paciente"})
public class PacienteController extends HttpServlet {

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

            PacienteService pacienteService = new PacienteServiceImpl();
            String opcao = request.getParameter("opcao");

            if (opcao.equals("salvar")) {
                pacienteService.salvarPaciente(request, response);
                response.sendRedirect("administrativo/pacientes");

            } else if (opcao.equals("alterar")) {
                pacienteService.alterarPaciente(request, response);

                response.sendRedirect("administrativo/pacientes");

            } else if (opcao.equals("excluir")) {
                pacienteService.excluirPaciente(request, response);

                response.sendRedirect("administrativo/pacientes");

            } else if (opcao.equals("buscar")) {

                Paciente buscaPaciente = pacienteService.buscarPaciente(request, response);
                request.setAttribute("buscaPaciente", buscaPaciente);
                RequestDispatcher rd = request.getRequestDispatcher("adminpages/buscaPacienteAdm.jsp");
                rd.forward(request, response);

            }

        } catch (IOException e) {
            System.out.println("ERRO(PacienteController): " + e.getMessage());
            response.sendRedirect("administrativo/pacientes");
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
