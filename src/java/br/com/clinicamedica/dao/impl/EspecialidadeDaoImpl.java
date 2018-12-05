package br.com.clinicamedica.dao.impl;

import br.com.clinicamedica.dao.EspecialidadeDao;
import br.com.clinicamedica.model.Especialidade;
import br.com.clinicamedica.util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDaoImpl implements EspecialidadeDao {

    private static final String INSERT = "INSERT INTO ESPECIALIDADES values(nextval('especialidades_seq'),?)";
    private static final String DELETE = "UPDATE ESPECIALIDADES SET flag_ativo = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM ESPECIALIDADES WHERE flag_ativo = 1";
    private static final String SELECT = "SELECT * FROM ESPECIALIDADES WHERE id = ? ";
    private static final String UPDATE = "UPDATE ESPECIALIDADES SET nome = ? WHERE id = ?";
    private static final String SELECT_NOME = "SELECT * FROM ESPECIALIDADES WHERE nome = ? ";

    private Connection conexao;

    @Override
    public boolean createEspecialidade(Especialidade especialidade) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, especialidade.getNome());
            pstmt.setInt(2, especialidade.getFlagAtivo());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
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
    public boolean deleteEspecialidade(Especialidade especialidade) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, especialidade.getFlagAtivo());
            pstmt.setInt(2, especialidade.getId());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
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
    public Especialidade readEspecialidade(Especialidade especialidade) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT);

            pstmt.setInt(1, especialidade.getId());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            especialidade.setId(Integer.parseInt(rs.getString("id")));
            especialidade.setNome(rs.getString("nome"));
            especialidade.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

            return especialidade;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(EspDaoR: " + e.getMessage());
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
    public boolean updateEspecialidade(Especialidade especialidade) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, especialidade.getNome());
            pstmt.setInt(2, especialidade.getId());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
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
    public List<Especialidade> listEspecialidade() {
        try {
            List<Especialidade> especialidades = new ArrayList<>();

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Especialidade especialidade = new Especialidade();

                especialidade.setId(Integer.parseInt(rs.getString("id")));
                especialidade.setNome(rs.getString("nome"));

                especialidades.add(especialidade);
            }

            return especialidades;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
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
    public Especialidade readEspecialidadeByNome(Especialidade especialidade) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_NOME);

            pstmt.setString(1, especialidade.getNome());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            especialidade.setId(Integer.parseInt(rs.getString("id")));
            especialidade.setNome(rs.getString("nome"));

            return especialidade;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
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
