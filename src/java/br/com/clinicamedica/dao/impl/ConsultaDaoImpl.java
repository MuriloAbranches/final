package br.com.clinicamedica.dao.impl;

import br.com.clinicamedica.dao.ConsultaDao;
import br.com.clinicamedica.model.Consulta;
import br.com.clinicamedica.model.DataHoraConsulta;
import br.com.clinicamedica.model.Especialidade;
import br.com.clinicamedica.model.Medico;
import br.com.clinicamedica.model.Paciente;
import br.com.clinicamedica.util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDaoImpl implements ConsultaDao {

    private static final String INSERT = "INSERT INTO CONSULTAS values (nextval('consultas_seq'),?,?,?,?,?);";
    private static final String DELETE = "UPDATE CONSULTAS SET flag_ativo = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM CONSULTAS where flag_ativo = 1";
    private static final String SELECT = "SELECT * FROM CONSULTAS WHERE id = ?";
    private static final String UPDATE = "UPDATE CONSULTAS SET medico = ?, data_hora_consulta = ?, flag_ativo = ? WHERE id = ?";
    private static final String SELECT_PACIENTE = "SELECT * FROM CONSULTAS WHERE paciente = ? and flag_ativo =1 ";
  

    private Connection conexao;

    @Override
    public boolean createConsulta(Consulta consulta) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setLong(1, consulta.getPaciente().getId());
            pstmt.setInt(2, consulta.getEspecialidade().getId());
            pstmt.setLong(3, consulta.getMedico().getCrm());
            pstmt.setInt(4, consulta.getDataHoraConsulta().getId());
            pstmt.setInt(5, consulta.getFlagAtivo());
            

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(PacienteDaoC): " + e.getMessage());
            return false;
        }finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public boolean deleteConsulta(Consulta consulta) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, consulta.getFlagAtivo());
            pstmt.setLong(2, consulta.getId());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(ConsultaDaoD): " + e.getMessage());
            return false;
        }finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public Consulta readConsulta(Consulta consulta) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT);

            pstmt.setLong(1, consulta.getId());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            consulta.setId(Integer.parseInt(rs.getString("id")));

            Medico medico = new Medico();
            medico.setCrm(Integer.parseInt(rs.getString("medico")));

            Especialidade especialidade = new Especialidade();
            especialidade.setId(Integer.parseInt(rs.getString("especialidade")));

            Paciente paciente = new Paciente();
            paciente.setId(Integer.parseInt(rs.getString("paciente")));
            
            DataHoraConsulta dataHoraConsulta = new DataHoraConsulta();
            dataHoraConsulta.setId(Integer.parseInt(rs.getString("data_hora_consulta")));
       
            consulta.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));
            consulta.setDataHoraConsulta(dataHoraConsulta);
            consulta.setEspecialidade(especialidade);
            consulta.setMedico(medico);
            consulta.setPaciente(paciente);

            return consulta;

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO(ConsultaDaoR): " + e.getMessage());
            return null;
        }finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public boolean updateConsulta(Consulta consulta) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setLong(1, consulta.getMedico().getCrm());
            pstmt.setInt(2, consulta.getDataHoraConsulta().getId());
            pstmt.setInt(3, consulta.getFlagAtivo());
            pstmt.setInt(4, consulta.getId());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(ConsultaDaoU): " + e.getMessage());
            return false;
        }finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public List<Consulta> listConsulta() {
        try {
            List<Consulta> consultas = new ArrayList<>();

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Consulta consulta = new Consulta();

                consulta.setId(Integer.parseInt(rs.getString("id")));

                Paciente paciente = new Paciente();
                paciente.setId(Integer.parseInt(rs.getString("paciente")));

                Especialidade especialidade = new Especialidade();
                especialidade.setId(Integer.parseInt(rs.getString("especialidade")));

                Medico medico = new Medico();
                medico.setCrm(Integer.parseInt(rs.getString("medico")));
                
                DataHoraConsulta dataHoraConsulta = new DataHoraConsulta();
                dataHoraConsulta.setId(Integer.parseInt(rs.getString("data_hora_consulta")));

                consulta.setDataHoraConsulta(dataHoraConsulta);
                consulta.setEspecialidade(especialidade);
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);

                consultas.add(consulta);
            }

            return consultas;
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO(ConsultaDaoL): " + e.getMessage());
            return null;
        }finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public boolean activateConsulta(Consulta consulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consulta> listConsultaPaciente(Paciente paciente) {
        try {
            List<Consulta> consultas = new ArrayList<>();

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_PACIENTE);
            
            pstmt.setInt(1, paciente.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Consulta consulta = new Consulta();

                consulta.setId(Integer.parseInt(rs.getString("id")));


                Especialidade especialidade = new Especialidade();
                especialidade.setId(Integer.parseInt(rs.getString("especialidade")));

                Medico medico = new Medico();
                medico.setCrm(Integer.parseInt(rs.getString("medico")));
                
                DataHoraConsulta dataHoraConsulta = new DataHoraConsulta();
                dataHoraConsulta.setId(Integer.parseInt(rs.getString("data_hora_consulta")));

                consulta.setDataHoraConsulta(dataHoraConsulta);
                consulta.setEspecialidade(especialidade);
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);

                consultas.add(consulta);
            }

            return consultas;
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO(ConsultaDaoL): " + e.getMessage());
            return null;
        }finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    

}
