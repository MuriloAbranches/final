package br.com.clinicamedica.dao.impl;

import br.com.clinicamedica.dao.MedicoDao;
import br.com.clinicamedica.model.Especialidade;
import br.com.clinicamedica.model.Medico;
import br.com.clinicamedica.util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDaoImpl implements MedicoDao {

    private static final String INSERT = "INSERT INTO MEDICOS values(?,?,?,?)";
    private static final String DELETE = "UPDATE MEDICOS SET flag_ativo = ? WHERE crm = ?";
    private static final String SELECT_ALL = "SELECT * FROM MEDICOS WHERE flag_ativo = 1";
    private static final String SELECT = "SELECT * FROM MEDICOS WHERE crm = ?";
    private static final String UPDATE = "UPDATE MEDICOS SET especialidade = ? WHERE crm = ?";
    private static final String ACTIVATE = "UPDATE MEDICOS SET flag_ativo = ? WHERE crm = ?";
    private static final String SELECT_ESPECIALIDADES = "SELECT func.nome, med.crm FROM MEDICOS med, FUNCIONARIOS func, ESPECIALIDADES esp "
            + "WHERE (func.id = med.funcionario) and (med.especialidade = esp.id) and (esp.id = ?) ";

    private Connection conexao;

    @Override
    public boolean createMedico(Medico medico) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setLong(1, medico.getCrm());
            pstmt.setLong(2, medico.getId());
            pstmt.setLong(3, medico.getEspecialidade().getId());
            pstmt.setInt(4, medico.getFlagAtivo());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        } finally {
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
    public boolean deleteMedico(Medico medico) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, medico.getFlagAtivo());
            pstmt.setLong(2, medico.getCrm());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        } finally {
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
    public Medico readMedico(Medico medico) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT);

            pstmt.setLong(1, medico.getCrm());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            medico.setCrm(Long.parseLong(rs.getString("crm")));
            medico.setId(Integer.parseInt(rs.getString("funcionario")));

            Especialidade especialidade = new Especialidade();
            especialidade.setId(Integer.parseInt(rs.getString("especialidade")));

            medico.setEspecialidade(especialidade);
            medico.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

            return medico;

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        } finally {
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
    public boolean updateMedico(Medico medico) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setInt(1, medico.getEspecialidade().getId());
            pstmt.setLong(2, medico.getCrm());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        } finally {
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
    public List<Medico> listMedico() {
        try {
            List<Medico> medicos = new ArrayList<>();

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();

                medico.setCrm(Long.parseLong(rs.getString("crm")));
                medico.setId(Integer.parseInt(rs.getString("funcionario")));

                Especialidade especialidade = new Especialidade();
                especialidade.setId(Integer.parseInt(rs.getString("especialidade")));

                medico.setEspecialidade(especialidade);
                medico.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

                medicos.add(medico);
            }

            return medicos;
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        } finally {
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
    public boolean activateMedico(Medico medico) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(ACTIVATE);

            pstmt.setInt(1, medico.getFlagAtivo());
            pstmt.setLong(2, medico.getCrm());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        } finally {
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
    public List<Medico> listMedicoByEspecialidade(Especialidade especialidade) {
        try {
            List<Medico> medicos = new ArrayList<>();

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ESPECIALIDADES);

            pstmt.setInt(1, especialidade.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();

                medico.setCrm(Long.parseLong(rs.getString("crm")));
                medico.setNomeCompleto(rs.getString("nome"));

                medicos.add(medico);
            }

            return medicos;
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO(MedicoDaoBE): " + e.getMessage());
            return null;
        } finally {
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
