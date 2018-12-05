package br.com.clinicamedica.service.impl;

import br.com.clinicamedica.dao.ContatoDao;
import br.com.clinicamedica.dao.EnderecoDao;
import br.com.clinicamedica.dao.PacienteDao;
import br.com.clinicamedica.dao.UsuarioDao;
import br.com.clinicamedica.dao.impl.ContatoDaoImpl;
import br.com.clinicamedica.dao.impl.EnderecoDaoImpl;
import br.com.clinicamedica.dao.impl.PacienteDaoImpl;
import br.com.clinicamedica.dao.impl.UsuarioDaoImpl;
import br.com.clinicamedica.model.Contato;
import br.com.clinicamedica.model.Email;
import br.com.clinicamedica.model.Endereco;
import br.com.clinicamedica.model.Paciente;
import br.com.clinicamedica.model.PerfilDeAcesso;
import br.com.clinicamedica.model.Telefone;
import br.com.clinicamedica.model.Usuario;
import br.com.clinicamedica.service.PacienteService;
import br.com.clinicamedica.util.EmailUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PacienteServiceImpl implements PacienteService {

    private PacienteDao pacienteDao;
    private EnderecoDao enderecoDao;
    private ContatoDao contatoDao;

    // Método responsável por Receber request e salvar no BD
    @Override
    public boolean salvarPaciente(HttpServletRequest request, HttpServletResponse response) {

        try {

            //Telefone
            Telefone telefone = new Telefone();

            telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
            telefone.setNumero(Integer.parseInt(request.getParameter("telefone")));

            //Contato
            Contato contato = new Contato();

            contato.setEmail(request.getParameter("email").toLowerCase());
            contato.setTelefone(telefone);

            //Endereco
            Endereco endereco = new Endereco();

            endereco.setCep(Integer.parseInt(request.getParameter("cep")));
            endereco.setLogradouro(request.getParameter("logradouro"));
            endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
            endereco.setBairro(request.getParameter("bairro"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setEstado(request.getParameter("estado"));

            //Paciente
            Paciente paciente = new Paciente();

            paciente.setNomeCompleto(request.getParameter("nomeCompleto").toUpperCase());
            paciente.setCpf(Long.parseLong(request.getParameter("cpf")));
            paciente.setRg(Long.parseLong(request.getParameter("rg")));

            //PacienteDao
            pacienteDao = new PacienteDaoImpl();

            if (null == pacienteDao.readPaciente(paciente)) { //Verifica se existe Paciente no BD

                //ContatoDao
                contatoDao = new ContatoDaoImpl();

                contato.setFlagAtivo(1);
                contatoDao.createContato(contato); //Cria Contato no BD

                contato = contatoDao.readContatoByEmail(contato); //Busca Contato no BD

                //EnderecoDao
                enderecoDao = new EnderecoDaoImpl();

                endereco.setFlagAtivo(1);
                enderecoDao.createEndereco(endereco); //Cria Endereco no BD

                endereco = enderecoDao.readEnderecoByCep(endereco); // Busca Endereco no BD

                //Preenche Paciente
                paciente.setEndereco(endereco);
                paciente.setContato(contato);
                paciente.setFlagAtivo(1);

                pacienteDao.createPaciente(paciente); //Cria Paciente no BD

            } else {
                paciente.setFlagAtivo(1);

                pacienteDao.activatePaciente(paciente); //Ativa Paciente no BD
            }
            

            String senha = gerarSenha();


            //Email
            Email email = new Email();
            email.setEmail(paciente.getContato().getEmail());
            email.setNome(paciente.getNomeCompleto());
            email.setSenha(senha);
            
            EmailUtil emailUtil = new EmailUtil();  
            emailUtil.enviarNotificaoCadastro(email);
            
            paciente = pacienteDao.readPaciente(paciente);
            Usuario usuario = new Usuario();
            usuario.setLogin(contato.getEmail());
            usuario.setPerfil(PerfilDeAcesso.COMUM);
            usuario.setSenha(senha);
            usuario.setFlagAtivo(1);
            usuario.setPaciente(paciente);
            UsuarioDao usuarioDao = new UsuarioDaoImpl();
            usuarioDao.createUsuario(usuario);
            
            return true;

        } catch (Exception e) {
            System.out.println("ERRO(PacienteService): " + e.getMessage());
            return false;
        }
    }

    // Método responsável por recuperar a lista de Pacientes no BD
    @Override
    public List<Paciente> listarPaciente() {
        try {
            pacienteDao = new PacienteDaoImpl();

            List<Paciente> pacientes = pacienteDao.listPaciente();

            return pacientes;

        } catch (Exception e) {
            System.out.println("ERRO(PacienteService): " + e.getMessage());
            return null;
        }
    }

    // Método responsável por recuperar um Pacientes no BD
    @Override
    public Paciente buscarPaciente(HttpServletRequest request, HttpServletResponse response) {
        try {
            Paciente paciente = new Paciente();
            paciente.setCpf(Long.parseLong(request.getParameter("cpfBusca")));

            pacienteDao = new PacienteDaoImpl();
            paciente = pacienteDao.readPaciente(paciente);

            enderecoDao = new EnderecoDaoImpl();
            paciente.setEndereco(enderecoDao.readEndereco(paciente.getEndereco()));

            contatoDao = new ContatoDaoImpl();
            paciente.setContato(contatoDao.readContato(paciente.getContato()));

            return paciente;
        } catch (Exception e) {
            System.out.println("ERRO(PacienteService): " + e.getMessage());
            return null;
        }
    }

    // Método responsável por atualizar um Pacientes no BD
    @Override
    public boolean alterarPaciente(HttpServletRequest request, HttpServletResponse response) {
        try {

            //Paciente
            Paciente paciente = new Paciente();

            paciente.setNomeCompleto(request.getParameter("nomeCompleto").toUpperCase());
            paciente.setCpf(Long.parseLong(request.getParameter("cpf")));

            pacienteDao = new PacienteDaoImpl();
            pacienteDao.updatePaciente(paciente);
            paciente = pacienteDao.readPaciente(paciente);

            //Endereco
            Endereco endereco = new Endereco();

            endereco.setCep(Integer.parseInt(request.getParameter("cep")));
            endereco.setLogradouro(request.getParameter("logradouro"));
            endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
            endereco.setBairro(request.getParameter("bairro"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setEstado(request.getParameter("estado"));

            enderecoDao = new EnderecoDaoImpl();
            endereco.setId(paciente.getEndereco().getId());
            enderecoDao.updateEndereco(endereco);

            //Contato
            Telefone telefone = new Telefone();

            telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
            telefone.setNumero(Integer.parseInt(request.getParameter("telefone")));

            Contato contato = new Contato();

            contato.setEmail(request.getParameter("email").toLowerCase());
            contato.setTelefone(telefone);

            contatoDao = new ContatoDaoImpl();
            contato.setId(paciente.getContato().getId());
            contatoDao.updateContato(contato);

            return true;
        } catch (Exception e) {
            System.out.println("ERRO(PacienteService): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluirPaciente(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Paciente
            Paciente paciente = new Paciente();
            paciente.setCpf(Long.parseLong(request.getParameter("cpf")));

            pacienteDao = new PacienteDaoImpl();
            paciente = pacienteDao.readPaciente(paciente);

            paciente.setFlagAtivo(0);

            if (pacienteDao.deletePaciente(paciente)) {

                //Contato
                Contato contato = new Contato();

                contato.setFlagAtivo(0);
                contato.setId(paciente.getContato().getId());
                contatoDao = new ContatoDaoImpl();
                contatoDao.deleteContato(contato);

                //Endereco
                Endereco endereco = new Endereco();

                endereco.setFlagAtivo(0);
                endereco.setId(paciente.getEndereco().getId());
                enderecoDao = new EnderecoDaoImpl();
                enderecoDao.deleteEndereco(endereco);

            }

            return true;
        } catch (Exception e) {
            System.out.println("ERRO(PacienteService): " + e.getMessage());
            return false;
        }

    }
    
    private String gerarSenha() {
        String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String senha = "";

        for (int x = 0; x < 10; x++) {
            int j = (int) (Math.random() * carct.length);
            senha += carct[j];
        }
        return senha;
    }
}
