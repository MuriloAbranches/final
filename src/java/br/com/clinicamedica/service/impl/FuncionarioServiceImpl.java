package br.com.clinicamedica.service.impl;

import br.com.clinicamedica.dao.ContatoDao;
import br.com.clinicamedica.dao.EnderecoDao;
import br.com.clinicamedica.dao.FuncionarioDao;
import br.com.clinicamedica.dao.impl.ContatoDaoImpl;
import br.com.clinicamedica.dao.impl.EnderecoDaoImpl;
import br.com.clinicamedica.dao.impl.FuncionarioDaoImpl;
import br.com.clinicamedica.model.Contato;
import br.com.clinicamedica.model.Endereco;
import br.com.clinicamedica.model.Funcionario;
import br.com.clinicamedica.model.Telefone;
import br.com.clinicamedica.model.TipoFuncionario;
import br.com.clinicamedica.service.FuncionarioService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FuncionarioServiceImpl implements FuncionarioService {

    private FuncionarioDao funcionarioDao;
    private EnderecoDao enderecoDao;
    private ContatoDao contatoDao;

    @Override
    public boolean salvarFuncionario(HttpServletRequest request, HttpServletResponse response) {
        try {
            Funcionario funcionario = castRequestToFuncionario(request);

            funcionarioDao = new FuncionarioDaoImpl();

            if (null == funcionarioDao.readFuncionario(funcionario)) {

                funcionario.setEndereco(saveEndereco(castRequestToEndereco(request)));
                funcionario.setContato(saveContato(castRequestToContato(request)));
                funcionario.setFlagAtivo(1);

                funcionarioDao.createFuncionario(funcionario);

            } else {
                funcionario.setFlagAtivo(1);

                funcionarioDao.activateFuncionario(funcionario);
            }

            request.setAttribute("funcionario", "cadastrado");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

            return true;

        } catch (IOException | ServletException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Funcionario> listarFuncionario() {
        try {
            funcionarioDao = new FuncionarioDaoImpl();

            List<Funcionario> funcionarios = funcionarioDao.listFuncionario();

            return funcionarios;

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Funcionario buscarFuncionario(HttpServletRequest request, HttpServletResponse response) {
        try {

            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(Long.parseLong(request.getParameter("buscaCpf")));

            funcionarioDao = new FuncionarioDaoImpl();
            funcionario = funcionarioDao.readFuncionario(funcionario);

            request.setAttribute("funcionario", funcionario);
            RequestDispatcher rd = request.getRequestDispatcher("buscar.jsp");
            rd.forward(request, response);

            return funcionario;
        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean alterarFuncionario(HttpServletRequest request, HttpServletResponse response) {
        try {

            Funcionario funcionario = castRequestToFuncionario(request);
            funcionarioDao = new FuncionarioDaoImpl();

            updateEndereco(castRequestToEndereco(request), funcionario);
            updateContato(castRequestToContato(request), funcionario);

            funcionarioDao.updateFuncionario(funcionario);

            request.setAttribute("funcionario", "alterado");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

            return true;

        } catch (IOException | ServletException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluirFuncionario(HttpServletRequest request, HttpServletResponse response) {
        try {
            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(Long.parseLong(request.getParameter("cpf")));

            funcionarioDao = new FuncionarioDaoImpl();
            funcionario = funcionarioDao.readFuncionario(funcionario);

            funcionario.setFlagAtivo(0);

            if (funcionarioDao.deleteFuncionario(funcionario)) {

                deleteContato(funcionario);
                deleteEndereco(funcionario);
            }

            request.setAttribute("funcionario", "excluido");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

            return true;
        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

    private Endereco castRequestToEndereco(HttpServletRequest request) {

        Endereco endereco = new Endereco();

        endereco.setCep(Integer.parseInt(request.getParameter("cep")));
        endereco.setLogradouro(request.getParameter("logradouro"));
        endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setEstado(request.getParameter("estado"));

        return endereco;
    }

    private Contato castRequestToContato(HttpServletRequest request) {

        Telefone telefone = new Telefone();

        telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
        telefone.setNumero(Integer.parseInt(request.getParameter("telefone"))); //numero

        Contato contato = new Contato();

        contato.setEmail(request.getParameter("email").toLowerCase());
        contato.setTelefone(telefone);

        return contato;
    }

    private Funcionario castRequestToFuncionario(HttpServletRequest request) {

        Funcionario funcionario = new Funcionario();

        funcionario.setNomeCompleto(request.getParameter("nomeCompleto").toLowerCase());
        funcionario.setCpf(Long.parseLong(request.getParameter("cpf")));
        funcionario.setRg(Long.parseLong(request.getParameter("rg")));
        funcionario.setCargo(TipoFuncionario.valueOf(request.getParameter("cargo")));

        return funcionario;
    }

    private Contato saveContato(Contato contato) {

        contatoDao = new ContatoDaoImpl();

        contato.setFlagAtivo(1);
        contatoDao.createContato(contato);

        contato = contatoDao.readContato(contato);

        return contato;
    }

    private Endereco saveEndereco(Endereco endereco) {

        enderecoDao = new EnderecoDaoImpl();

        endereco.setFlagAtivo(1);
        enderecoDao.createEndereco(endereco);

        endereco = enderecoDao.readEndereco(endereco);

        return endereco;
    }

    private void updateContato(Contato contato, Funcionario funcionario) {

        funcionario = funcionarioDao.readFuncionario(funcionario);
        contatoDao = new ContatoDaoImpl();

        contato.setId(funcionario.getContato().getId());

        contatoDao.updateContato(contato);

    }

    private void updateEndereco(Endereco endereco, Funcionario funcionario) {

        funcionario = funcionarioDao.readFuncionario(funcionario);
        enderecoDao = new EnderecoDaoImpl();

        endereco.setId(funcionario.getEndereco().getId());

        enderecoDao.updateEndereco(endereco);
    }

    private void deleteContato(Funcionario funcionario) {
        Contato contato = new Contato();

        contato.setFlagAtivo(0);
        contato.setId(funcionario.getContato().getId());
        contatoDao = new ContatoDaoImpl();

        contatoDao.deleteContato(contato);
    }

    private void deleteEndereco(Funcionario funcionario) {
        Endereco endereco = new Endereco();

        endereco.setFlagAtivo(0);
        endereco.setId(funcionario.getEndereco().getId());
        enderecoDao = new EnderecoDaoImpl();

        enderecoDao.deleteEndereco(endereco);
    }
}
