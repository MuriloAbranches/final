package br.com.clinicamedica.dao.impl;

import br.com.clinicamedica.dao.EnderecoDao;
import br.com.clinicamedica.model.Endereco;
import br.com.clinicamedica.util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDaoImpl implements EnderecoDao {

    private static final String INSERT = "INSERT INTO ENDERECOS values (nextval('enderecos_seq'),?,?,?,?,?,?,?)";
    private static final String DELETE = "UPDATE ENDERECOS SET flag_ativo=? WHERE id=?";
    private static final String SELECT = "SELECT * FROM ENDERECOS WHERE id = ?";
    private static final String UPDATE = "UPDATE ENDERECOS SET cep=?,logradouro=?,numero=?,bairro=?,cidade=?,estado=? WHERE id=?";
    private static final String SELECT_CEP = "SELECT * FROM ENDERECOS WHERE cep = ?";

    private Connection conexao;

    @Override
    public boolean createEndereco(Endereco endereco) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setInt(1, endereco.getCep());
            pstmt.setString(2, endereco.getLogradouro());
            pstmt.setInt(3, endereco.getNumero());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getEstado());
            pstmt.setInt(7, endereco.getFlagAtivo());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(EnderecoDao): " + e.getMessage());
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
    public boolean deleteEndereco(Endereco endereco) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, endereco.getFlagAtivo());
            pstmt.setInt(2, endereco.getId());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(EnderecoDao): " + e.getMessage());
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
    public Endereco readEndereco(Endereco endereco) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT);

            pstmt.setInt(1, endereco.getId());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            endereco.setId(Integer.parseInt(rs.getString("id")));
            endereco.setCep(Integer.parseInt(rs.getString("cep")));
            endereco.setLogradouro(rs.getString("logradouro"));
            endereco.setNumero(Integer.parseInt(rs.getString("numero")));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setEstado(rs.getString("estado"));
            endereco.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

            return endereco;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(EnderecoDao): " + e.getMessage());
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
    public boolean updateEndereco(Endereco endereco) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setInt(1, endereco.getCep());
            pstmt.setString(2, endereco.getLogradouro());
            pstmt.setInt(3, endereco.getNumero());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getEstado());
            pstmt.setInt(7, endereco.getId());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(EnderecoDao): " + e.getMessage());
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
    public Endereco readEnderecoByCep(Endereco endereco) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_CEP);

            pstmt.setInt(1, endereco.getCep());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            endereco.setId(Integer.parseInt(rs.getString("id")));
            endereco.setCep(Integer.parseInt(rs.getString("cep")));
            endereco.setLogradouro(rs.getString("logradouro"));
            endereco.setNumero(Integer.parseInt(rs.getString("numero")));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setEstado(rs.getString("estado"));
            endereco.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

            return endereco;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(EnderecoDao): " + e.getMessage());
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
