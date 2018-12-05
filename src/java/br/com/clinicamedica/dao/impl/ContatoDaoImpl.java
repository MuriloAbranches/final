package br.com.clinicamedica.dao.impl;

import br.com.clinicamedica.dao.ContatoDao;
import br.com.clinicamedica.model.Contato;
import br.com.clinicamedica.model.Telefone;
import br.com.clinicamedica.util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContatoDaoImpl implements ContatoDao {

    private static final String INSERT = "INSERT INTO CONTATOS values (nextval('contatos_seq'),?,?,?,?) ";
    private static final String DELETE = "UPDATE CONTATOS SET flag_ativo=? WHERE id = ?";
    private static final String SELECT = "SELECT * FROM CONTATOS WHERE id = ?";
    private static final String UPDATE = "UPDATE CONTATOS SET email=?,ddd=?,telefone=? WHERE id = ?";
    private static final String SELECT_EMAIL = "SELECT * FROM CONTATOS WHERE email = ?";

    private Connection conexao;

    @Override
    public boolean createContato(Contato contato) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, contato.getEmail());
            pstmt.setInt(2, contato.getTelefone().getDdd());
            pstmt.setInt(3, contato.getTelefone().getNumero());
            pstmt.setInt(4, contato.getFlagAtivo());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(ContatoDao): " + e.getMessage());
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
    public boolean deleteContato(Contato contato) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, contato.getFlagAtivo());
            pstmt.setInt(2, contato.getId());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(ContatoDao): " + e.getMessage());
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
    public Contato readContato(Contato contato) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT);

            pstmt.setInt(1, contato.getId());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            contato.setId(Integer.parseInt(rs.getString("id")));
            contato.setEmail(rs.getString("email"));

            Telefone telefone = new Telefone();
            telefone.setDdd(Integer.parseInt(rs.getString("ddd")));
            telefone.setNumero(Integer.parseInt(rs.getString("telefone")));

            contato.setTelefone(telefone);
            contato.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

            return contato;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(ContatoDao): " + e.getMessage());
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
    public boolean updateContato(Contato contato) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, contato.getEmail());
            pstmt.setInt(2, contato.getTelefone().getDdd());
            pstmt.setInt(3, contato.getTelefone().getNumero());
            pstmt.setInt(4, contato.getId());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(ContatoDao): " + e.getMessage());
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
    public Contato readContatoByEmail(Contato contato) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_EMAIL);

            pstmt.setString(1, contato.getEmail());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            contato.setId(Integer.parseInt(rs.getString("id")));
            contato.setEmail(rs.getString("email"));

            Telefone telefone = new Telefone();
            telefone.setDdd(Integer.parseInt(rs.getString("ddd")));
            telefone.setNumero(Integer.parseInt(rs.getString("telefone")));

            contato.setTelefone(telefone);
            contato.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

            return contato;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(ContatoDao): " + e.getMessage());
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
