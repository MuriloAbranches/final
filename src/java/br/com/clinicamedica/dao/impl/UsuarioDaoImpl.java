package br.com.clinicamedica.dao.impl;

import br.com.clinicamedica.util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.clinicamedica.dao.UsuarioDao;
import br.com.clinicamedica.model.Paciente;
import br.com.clinicamedica.model.PerfilDeAcesso;
import br.com.clinicamedica.model.Usuario;


public class UsuarioDaoImpl implements UsuarioDao{
    
    private static final String INSERT = "INSERT INTO USUARIOS values (nextval('usuarios_seq'),?,?,?,?,?);";
    private static final String DELETE = "UPDATE USUARIOS SET flag_ativo = ? WHERE login = ?";
    private static final String SELECT = "SELECT * FROM USUARIOS WHERE login = ?";
    private static final String UPDATE = "UPDATE USUARIOS SET senha = ? WHERE login = ?";
    private static final String AUTENTICA_USUARIO = "SELECT * FROM USUARIOS WHERE login=? AND senha = ? ";
    private static final String SELECT_PACIENTE= "SELECT * FROM USUARIOS WHERE login = ?";
   

    private Connection conexao;
    
    @Override
    public boolean createUsuario(Usuario usuario) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, usuario.getLogin());
            pstmt.setString(2, usuario.getSenha());
            pstmt.setString(3, usuario.getPerfil().toString());
            pstmt.setInt(4, usuario.getFlagAtivo());
            pstmt.setInt(5, usuario.getPaciente().getId());
            
            pstmt.execute();
            
            return true;
        } catch (Exception e) {
            System.out.println("ERRO(LoginDaoC): " + e.getMessage());
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
    public boolean deleteUsuario(Usuario usuario) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, usuario.getFlagAtivo());
            pstmt.setString(2, usuario.getLogin());

            pstmt.execute();

            return true;
        } catch (Exception e) {
            System.out.println("ERRO(LoginDaoD): " + e.getMessage());
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
    public Usuario readUsuario(Usuario usuario) {
        try {
             conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT);

            pstmt.setString(1, usuario.getLogin());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            usuario.setLogin(rs.getString("usuario"));
            usuario.setSenha(rs.getString("senha"));
            
            return usuario;
        } catch (Exception e) {
             System.out.println("ERRO(LoginDaoD): " + e.getMessage());
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
    public boolean updateUsuario(Usuario usuario) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, usuario.getSenha());
            pstmt.setString(2, usuario.getLogin());
            
            return true;
        } catch (Exception e) {
            System.out.println("ERRO(LoginDaoD): " + e.getMessage());
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
    public Usuario autenticaUsuario(Usuario usuario) {

        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(AUTENTICA_USUARIO);

            pstmt.setString(1, usuario.getLogin());
            pstmt.setString(2, usuario.getSenha());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

          
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPerfil(PerfilDeAcesso.valueOf(rs.getString("perfil")));
            
            return usuario;
        } catch (Exception e) {
            System.out.println("ERRO(LoginDaoD): " + e.getMessage());
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
    public Paciente readUsuarioByLogin(Usuario usuario){
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_PACIENTE);

            pstmt.setString(1, usuario.getLogin());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            Paciente paciente = new Paciente();
            paciente.setId(Integer.parseInt(rs.getString("paciente")));
           
            return paciente;
            
            
        } catch (Exception e) {
            System.out.println("ERRO(LoginDaoRP): " + e.getMessage());
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
    };
}
