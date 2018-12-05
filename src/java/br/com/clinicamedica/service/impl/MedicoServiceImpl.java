package br.com.clinicamedica.service.impl;

import br.com.clinicamedica.dao.ContatoDao;
import br.com.clinicamedica.dao.EnderecoDao;
import br.com.clinicamedica.dao.FuncionarioDao;
import br.com.clinicamedica.dao.MedicoDao;
import br.com.clinicamedica.dao.impl.ContatoDaoImpl;
import br.com.clinicamedica.dao.impl.EnderecoDaoImpl;
import br.com.clinicamedica.dao.impl.FuncionarioDaoImpl;
import br.com.clinicamedica.dao.impl.MedicoDaoImpl;
import br.com.clinicamedica.model.Contato;
import br.com.clinicamedica.model.Email;
import br.com.clinicamedica.model.Endereco;
import br.com.clinicamedica.model.Especialidade;
import br.com.clinicamedica.model.Funcionario;
import br.com.clinicamedica.model.Medico;
import br.com.clinicamedica.model.Telefone;
import br.com.clinicamedica.model.TipoFuncionario;
import br.com.clinicamedica.service.MedicoService;
import br.com.clinicamedica.util.EmailUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MedicoServiceImpl implements MedicoService {

    private FuncionarioDao funcionarioDao;
    private EnderecoDao enderecoDao;
    private ContatoDao contatoDao;
    private MedicoDao medicoDao;

    @Override
    public boolean salvarMedico(HttpServletRequest request, HttpServletResponse response) {
        try {
            Medico medico = new Medico();

            medico.setCrm(Long.parseLong(request.getParameter("crm")));

            medicoDao = new MedicoDaoImpl();

            if (null == medicoDao.readMedico(medico)) {

                Funcionario funcionario = new Funcionario();

                funcionario.setNomeCompleto(request.getParameter("nomeCompleto").toLowerCase());
                funcionario.setCpf(Long.parseLong(request.getParameter("cpf")));
                funcionario.setRg(Long.parseLong(request.getParameter("rg")));

                funcionarioDao = new FuncionarioDaoImpl();

                funcionario.setFlagAtivo(1);
                funcionario.setCargo(TipoFuncionario.MEDICO);

                Endereco endereco = new Endereco();

                endereco.setCep(Integer.parseInt(request.getParameter("cep")));
                endereco.setLogradouro(request.getParameter("logradouro"));
                endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
                endereco.setBairro(request.getParameter("bairro"));
                endereco.setCidade(request.getParameter("cidade"));
                endereco.setEstado(request.getParameter("estado"));

                enderecoDao = new EnderecoDaoImpl();

                endereco.setFlagAtivo(1);
                enderecoDao.createEndereco(endereco);

                endereco = enderecoDao.readEnderecoByCep(endereco);

                Telefone telefone = new Telefone();

                telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
                telefone.setNumero(Integer.parseInt(request.getParameter("telefone"))); //numero

                Contato contato = new Contato();

                contato.setEmail(request.getParameter("email").toLowerCase());
                contato.setTelefone(telefone);

                contatoDao = new ContatoDaoImpl();

                contato.setFlagAtivo(1);
                contatoDao.createContato(contato);

                contato = contatoDao.readContatoByEmail(contato);

                funcionario.setEndereco(endereco);
                funcionario.setContato(contato);

                funcionarioDao.createFuncionario(funcionario);

                funcionario = funcionarioDao.readFuncionario(funcionario);

                medico.setFlagAtivo(1);
                medico.setId(funcionario.getId());

                Especialidade especialidade = new Especialidade();
                especialidade.setId(Integer.parseInt(request.getParameter("especialidades")));

                medico.setEspecialidade(especialidade);

                medicoDao.createMedico(medico);
                
                Email email = new Email();
                email.setEmail(contato.getEmail());
                email.setNome(funcionario.getNomeCompleto());

                EmailUtil emailUtil = new EmailUtil();
                emailUtil.enviarNotificaoCadastro(email);

            } else {
                medico.setFlagAtivo(1);

                medicoDao.activateMedico(medico);
            }

          
            
            
            
            return true;

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Medico> listarMedico() {
        try {
            medicoDao = new MedicoDaoImpl();

            List<Medico> medicos = medicoDao.listMedico();
            List<Medico> listamedicos = new ArrayList<>();

            for (Medico medico : medicos) {

                Funcionario funcionario = new Funcionario();
                funcionario.setId(medico.getId());
                funcionarioDao = new FuncionarioDaoImpl();
                funcionario = funcionarioDao.findFuncionarioById(funcionario);

                Contato contato = new Contato();
                contato.setId(funcionario.getContato().getId());
                contatoDao = new ContatoDaoImpl();
                contato = contatoDao.readContato(contato);

                medico.setContato(contato);
                medico.setNomeCompleto(funcionario.getNomeCompleto());

                listamedicos.add(medico);

            }

            return listamedicos;

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Medico buscarMedico(HttpServletRequest request, HttpServletResponse response) {
        try {

            Medico medico = new Medico();
            medico.setCrm(Long.parseLong(request.getParameter("crmBusca")));

            medicoDao = new MedicoDaoImpl();
            medico = medicoDao.readMedico(medico);

            Funcionario funcionario = new Funcionario();
            funcionario.setId(medico.getId());

            funcionarioDao = new FuncionarioDaoImpl();
            funcionario = funcionarioDao.findFuncionarioById(funcionario);

            medico.setNomeCompleto(funcionario.getNomeCompleto());
            medico.setCpf(funcionario.getCpf());
            medico.setRg(funcionario.getRg());

            Contato contato = new Contato();
            contato.setId(funcionario.getContato().getId());
            contatoDao = new ContatoDaoImpl();
            contato = contatoDao.readContato(contato);

            Endereco endereco = new Endereco();
            endereco.setId(funcionario.getEndereco().getId());
            enderecoDao = new EnderecoDaoImpl();
            endereco = enderecoDao.readEndereco(endereco);

            medico.setEndereco(endereco);
            medico.setContato(contato);

            return medico;
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean alterarMedico(HttpServletRequest request, HttpServletResponse response) {
        try {
            Medico medico = new Medico();

            medico.setCrm(Long.parseLong(request.getParameter("crm")));

            medicoDao = new MedicoDaoImpl();

            Funcionario funcionario = new Funcionario();

            funcionario.setNomeCompleto(request.getParameter("nomeCompleto").toLowerCase());
            funcionario.setCpf(Long.parseLong(request.getParameter("cpf")));
            funcionario.setRg(Long.parseLong(request.getParameter("rg")));

            funcionarioDao = new FuncionarioDaoImpl();
            funcionario.setCargo(TipoFuncionario.MEDICO);

            funcionarioDao.updateFuncionario(funcionario);

            funcionario = funcionarioDao.readFuncionario(funcionario);

            Endereco endereco = new Endereco();

            endereco.setCep(Integer.parseInt(request.getParameter("cep")));
            endereco.setLogradouro(request.getParameter("logradouro"));
            endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
            endereco.setBairro(request.getParameter("bairro"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setEstado(request.getParameter("estado"));

            enderecoDao = new EnderecoDaoImpl();

            endereco.setId(funcionario.getEndereco().getId());

            enderecoDao.updateEndereco(endereco);

            Telefone telefone = new Telefone();

            telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
            telefone.setNumero(Integer.parseInt(request.getParameter("telefone"))); //numero

            Contato contato = new Contato();

            contato.setEmail(request.getParameter("email").toLowerCase());
            contato.setTelefone(telefone);

            contatoDao = new ContatoDaoImpl();

            contato.setId(funcionario.getContato().getId());

            contatoDao.updateContato(contato);

            medico.setId(funcionario.getId());

            Especialidade especialidade = new Especialidade();
            especialidade.setId(Integer.parseInt(request.getParameter("especialidades")));

            medico.setEspecialidade(especialidade);

            medicoDao.updateMedico(medico);

            return true;

        } catch (Exception e) {
            System.out.println("ERRO(MedicoServiceA): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluirMedico(HttpServletRequest request, HttpServletResponse response) {
        try {
            Medico medico = new Medico();
            medico.setCrm(Long.parseLong(request.getParameter("crm")));

            medicoDao = new MedicoDaoImpl();
            medico = medicoDao.readMedico(medico);

            medico.setFlagAtivo(0);

            if (medicoDao.deleteMedico(medico)) {

                Funcionario funcionario = new Funcionario();
                funcionario.setId(medico.getId());

                funcionarioDao = new FuncionarioDaoImpl();
                funcionario = funcionarioDao.findFuncionarioById(funcionario);

                funcionario.setFlagAtivo(0);

                funcionarioDao.deleteFuncionario(funcionario);

                Contato contato = new Contato();

                contato.setFlagAtivo(0);
                contato.setId(funcionario.getContato().getId());
                contatoDao = new ContatoDaoImpl();

                contatoDao.deleteContato(contato);

                Endereco endereco = new Endereco();

                endereco.setFlagAtivo(0);
                endereco.setId(funcionario.getEndereco().getId());
                enderecoDao = new EnderecoDaoImpl();

                enderecoDao.deleteEndereco(endereco);

            }

            return true;
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Medico> buscarMedicoPorEspecialidade(HttpServletRequest request, HttpServletResponse response) {
        try {
            medicoDao = new MedicoDaoImpl();

            Especialidade especialidade = new Especialidade();
            especialidade.setId(Integer.parseInt(request.getParameter("especialidades")));
            List<Medico> medicos = medicoDao.listMedicoByEspecialidade(especialidade);

            return medicos;

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }
}
