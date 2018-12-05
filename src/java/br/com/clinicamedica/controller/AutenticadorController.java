package br.com.clinicamedica.controller;

import br.com.clinicamedica.dao.ConsultaDao;
import br.com.clinicamedica.dao.DataHoraConsultaDao;
import br.com.clinicamedica.dao.EspecialidadeDao;
import br.com.clinicamedica.dao.FuncionarioDao;
import br.com.clinicamedica.dao.MedicoDao;
import br.com.clinicamedica.dao.PacienteDao;
import br.com.clinicamedica.dao.UsuarioDao;
import br.com.clinicamedica.dao.impl.ConsultaDaoImpl;
import br.com.clinicamedica.dao.impl.DataHoraConsultaDaoImpl;
import br.com.clinicamedica.dao.impl.EspecialidadeDaoImpl;
import br.com.clinicamedica.dao.impl.FuncionarioDaoImpl;
import br.com.clinicamedica.dao.impl.MedicoDaoImpl;
import br.com.clinicamedica.dao.impl.PacienteDaoImpl;
import br.com.clinicamedica.dao.impl.UsuarioDaoImpl;
import br.com.clinicamedica.model.Consulta;
import br.com.clinicamedica.model.DataHoraConsulta;
import br.com.clinicamedica.model.Especialidade;
import br.com.clinicamedica.model.Funcionario;
import br.com.clinicamedica.model.Medico;
import br.com.clinicamedica.model.Paciente;
import br.com.clinicamedica.model.PerfilDeAcesso;
import br.com.clinicamedica.model.Usuario;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "autenticador", urlPatterns = {"/autenticador"})
public class AutenticadorController extends HttpServlet {

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
            HttpSession sessao = request.getSession(false);

            if (sessao == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            sessao.removeAttribute("Login");

            Usuario usuario = new Usuario();
            usuario.setLogin(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));

            UsuarioDao usuarioDao = new UsuarioDaoImpl();
            usuario = usuarioDao.autenticaUsuario(usuario);

            if (usuario != null) {
                if (usuario.getPerfil().equals(PerfilDeAcesso.ADMINISTRADOR)) {
                    sessao.setAttribute("Login", usuario.getLogin());

                    response.sendRedirect("adminpages/index.jsp");
                } else {
                    
                    UsuarioDaoImpl usuarioPaciente = new UsuarioDaoImpl();
                    Paciente paciente = usuarioPaciente.readUsuarioByLogin(usuario);
                    
                    ConsultaDao consultaDao = new ConsultaDaoImpl();
                    List<Consulta> consultas = consultaDao.listConsultaPaciente(paciente);
                    List<Consulta> listaConsulta = new ArrayList<>();
                    
                    
                    for (Consulta consulta : consultas) {

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

                        listaConsulta.add(consulta);
                    }
                    
                    sessao.setAttribute("lista", listaConsulta);
                    response.sendRedirect("paciente.jsp");
                }

            } else {
                sessao.setAttribute("mensagem", "Login ou senha inválidos");
                response.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            response.sendRedirect("index.jsp");
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
