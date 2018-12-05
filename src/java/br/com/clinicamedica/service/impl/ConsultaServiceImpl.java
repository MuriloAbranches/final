package br.com.clinicamedica.service.impl;

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
import br.com.clinicamedica.model.Email;
import br.com.clinicamedica.model.Especialidade;
import br.com.clinicamedica.model.Funcionario;
import br.com.clinicamedica.model.Medico;
import br.com.clinicamedica.model.Paciente;
import br.com.clinicamedica.service.ConsultaService;
import br.com.clinicamedica.util.EmailUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultaServiceImpl implements ConsultaService {

    private EspecialidadeDao especialidadeDao;
    private ConsultaDao consultaDao;

    @Override
    public boolean salvarConsulta(HttpServletRequest request, HttpServletResponse response) {
        try {
     
            Paciente paciente = new Paciente();
            paciente.setCpf(Long.parseLong(request.getParameter("cpf")));
            PacienteDao pacienteDao = new PacienteDaoImpl();
            paciente = pacienteDao.readPaciente(paciente);
            
            ContatoDao contatoDao = new ContatoDaoImpl();
            Contato contato = contatoDao.readContato(paciente.getContato());
            
            
            Medico medico = new Medico();
            medico.setCrm(Long.parseLong(request.getParameter("medico")));
            MedicoDao medicoDao = new MedicoDaoImpl();
            medico = medicoDao.readMedico(medico);
            
            Funcionario funcionario = new Funcionario();
            funcionario.setId(medico.getId());
            FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
            funcionario = funcionarioDao.findFuncionarioById(funcionario);
            
            Especialidade especialidade = new Especialidade();
            especialidade.setId(Integer.parseInt(request.getParameter("especialidade")));
            especialidadeDao = new EspecialidadeDaoImpl();
            especialidade = especialidadeDao.readEspecialidade(especialidade);

            DataHoraConsulta dataHoraConsulta = new DataHoraConsulta();
            dataHoraConsulta.setId(Integer.parseInt(request.getParameter("dataHora")));
                      
            Consulta consulta = new Consulta();
            consulta.setPaciente(paciente);
            consulta.setMedico(medico);
            consulta.setEspecialidade(especialidade);
            consulta.setDataHoraConsulta(dataHoraConsulta);
            consulta.setFlagAtivo(1);
            
            
            DataHoraConsultaDao dataHoraConsultaDao = new DataHoraConsultaDaoImpl();
            dataHoraConsultaDao.updateDataHoraConsulta(dataHoraConsulta);
            dataHoraConsulta = dataHoraConsultaDao.readDataHoraConsulta(dataHoraConsulta);

            consultaDao = new ConsultaDaoImpl();
            consultaDao.createConsulta(consulta);
            
           

            Email emailPaciente = new Email();
            emailPaciente.setEmail(contato.getEmail());
            emailPaciente.setNome(paciente.getNomeCompleto());
            emailPaciente.setMedico(funcionario.getNomeCompleto());
            emailPaciente.setDataHoraMarcada("o dia " + dataHoraConsulta.getDataConsulta()+", ás "+ dataHoraConsulta.getHoraConsulta()+"hrs");
            
            EmailUtil emailUtil = new EmailUtil();  
            emailUtil.enviarNotificacaoAgendamento(emailPaciente);
            
            return true;

        } catch (Exception e) {
            System.out.println("ERRO(ConsultaServiceS): " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Consulta> listarConsulta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consulta buscarConsulta(HttpServletRequest request, HttpServletResponse response) {
        try {
            Consulta consulta = new Consulta();
            int id = (Integer.parseInt(request.getParameter("buscaConsulta")));
            consulta.setId(id);
            consultaDao = new ConsultaDaoImpl();
            consulta  = consultaDao.readConsulta(consulta);
            
            Paciente paciente = new Paciente();
            PacienteDao pacienteDao = new PacienteDaoImpl();
            paciente = pacienteDao.readPacienteById(consulta.getPaciente());

            Medico medico = new Medico();
            MedicoDao medicoDao = new MedicoDaoImpl();
            medico = medicoDao.readMedico(consulta.getMedico());

            Especialidade especialidade = new Especialidade();
            especialidadeDao = new EspecialidadeDaoImpl();
            especialidade = especialidadeDao.readEspecialidade(consulta.getEspecialidade());

            DataHoraConsulta dataHoraConsulta = new DataHoraConsulta();
            DataHoraConsultaDao dataHoraConsultaDao = new DataHoraConsultaDaoImpl();
            dataHoraConsulta = dataHoraConsultaDao.readDataHoraConsulta(consulta.getDataHoraConsulta());

            consulta.setPaciente(paciente);
            consulta.setMedico(medico);
            consulta.setEspecialidade(especialidade);
            consulta.setDataHoraConsulta(dataHoraConsulta);

            return consulta;

        } catch (Exception e) {
            System.out.println("ERRO(ConsultaServiceB): " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean alterarConsulta(HttpServletRequest request, HttpServletResponse response) {
        try {

            Medico medico = new Medico();
            medico.setCrm(Long.parseLong(request.getParameter("medico")));

            DataHoraConsulta dataHoraConsulta = new DataHoraConsulta();
            dataHoraConsulta.setId(Integer.parseInt(request.getParameter("dataHora")));
            dataHoraConsulta.setFlagAtivo(0);

            Consulta consulta = new Consulta();
            
            consulta.setId(Integer.parseInt(request.getParameter("idConsulta")));
            consulta.setMedico(medico);
            consulta.setDataHoraConsulta(dataHoraConsulta);
            consulta.setFlagAtivo(1);

            DataHoraConsultaDao dataHoraConsultaDao = new DataHoraConsultaDaoImpl();
            dataHoraConsultaDao.updateDataHoraConsulta(dataHoraConsulta);
            
            DataHoraConsulta dataAntiga = new DataHoraConsulta();
            dataAntiga.setId(Integer.parseInt(request.getParameter("idDataAntigo")));
            dataAntiga.setFlagAtivo(1);
            DataHoraConsultaDao dataDao = new DataHoraConsultaDaoImpl();
            dataDao.updateDataHoraConsulta(dataAntiga);

            consultaDao = new ConsultaDaoImpl();
            consultaDao.updateConsulta(consulta);
            
            return true;

        } catch (Exception e) {
            System.out.println("ERRO(ConsultaServiceA): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluirConsulta(HttpServletRequest request, HttpServletResponse response) {
          try {

            DataHoraConsulta dataHoraConsulta = new DataHoraConsulta();
            dataHoraConsulta.setId(Integer.parseInt(request.getParameter("idDataAntigo")));
            dataHoraConsulta.setFlagAtivo(1);

            Consulta consulta = new Consulta();
            
            consulta.setId(Integer.parseInt(request.getParameter("idConsulta")));
            consulta.setFlagAtivo(0);

            DataHoraConsultaDao dataHoraConsultaDao = new DataHoraConsultaDaoImpl();
            dataHoraConsultaDao.updateDataHoraConsulta(dataHoraConsulta);

            consultaDao = new ConsultaDaoImpl();
            consultaDao.deleteConsulta(consulta);

            return true;

        } catch (Exception e) {
            System.out.println("ERRO(ConsultaServiceE): " + e.getMessage());
            return false;
        }
    }

}
