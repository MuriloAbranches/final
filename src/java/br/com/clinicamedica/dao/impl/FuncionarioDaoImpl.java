package br.com.clinicamedica.dao.impl;

import br.com.clinicamedica.dao.FuncionarioDao;
import br.com.clinicamedica.model.Contato;
import br.com.clinicamedica.model.Endereco;
import br.com.clinicamedica.model.Funcionario;
import br.com.clinicamedica.model.TipoFuncionario;
import br.com.clinicamedica.util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDaoImpl implements FuncionarioDao {

    private static final String INSERT = "INSERT INTO FUNCIONARIOS values (nextval('funcionarios_seq'),?,?,?,?,?,?,?)";
    private static final String DELETE = "UPDATE FUNCIONARIOS SET flag_ativo = ? WHERE cpf = ?";
    private static final String SELECT_ALL = "SELECT * FROM FUNCIONARIOS WHERE flag_ativo = 1";
    private static final String SELECT = "SELECT * FROM FUNCIONARIOS WHERE cpf = ?";
    private static final String UPDATE = "UPDATE FUNCIONARIOS SET nome = ?, tipo = ? WHERE cpf = ?";
    private static final String ACTIVATE = "UPDATE FUNCIONARIOS SET flag_ativo = ? WHERE cpf = ?";
    private static final String SELECT_ID = "SELECT * FROM FUNCIONARIOS WHERE id = ?";

    private Connection conexao;

    @Override
    public boolean createFuncionario(Funcionario funcionario) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, funcionario.getNomeCompleto());
            pstmt.setLong(2, funcionario.getCpf());
            pstmt.setLong(3, funcionario.getRg());
            pstmt.setString(4, funcionario.getCargo().name());
            pstmt.setInt(5, funcionario.getFlagAtivo());
            pstmt.setInt(6, funcionario.getContato().getId());
            pstmt.setInt(7, funcionario.getEndereco().getId());

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
    public boolean deleteFuncionario(Funcionario funcionario) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, funcionario.getFlagAtivo());
            pstmt.setLong(2, funcionario.getCpf());

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
    public Funcionario readFuncionario(Funcionario funcionario) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT);

            pstmt.setLong(1, funcionario.getCpf());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            funcionario.setId(Integer.parseInt(rs.getString("id")));
            funcionario.setNomeCompleto(rs.getString("nome"));
            funcionario.setCpf(Long.parseLong(rs.getString("cpf")));
            funcionario.setRg(Long.parseLong(rs.getString("rg")));
            funcionario.setCargo(TipoFuncionario.valueOf(rs.getString("tipo")));
            funcionario.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

            Contato contato = new Contato();
            contato.setId(Integer.parseInt(rs.getString("contato")));

            Endereco endereco = new Endereco();
            endereco.setId(Integer.parseInt(rs.getString("endereco")));

            funcionario.setContato(contato);
            funcionario.setEndereco(endereco);

            return funcionario;

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO(FuncDaoR): " + e.getMessage());
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
    public boolean updateFuncionario(Funcionario funcionario) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, funcionario.getNomeCompleto());
            pstmt.setString(2, funcionario.getCargo().name());

            pstmt.setLong(3, funcionario.getCpf());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(FuncDaoA): " + e.getMessage());
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
    public List<Funcionario> listFuncionario() {
        try {
            List<Funcionario> funcionarios = new ArrayList<>();

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(Integer.parseInt(rs.getString("id")));
                funcionario.setNomeCompleto(rs.getString("nome"));
                funcionario.setCpf(Long.parseLong(rs.getString("cpf")));
                funcionario.setRg(Long.parseLong(rs.getString("rg")));
                funcionario.setCargo(TipoFuncionario.valueOf(rs.getString("tipo")));
                funcionario.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

                Contato contato = new Contato();
                contato.setId(Integer.parseInt(rs.getString("contato")));

                Endereco endereco = new Endereco();
                endereco.setId(Integer.parseInt(rs.getString("endereco")));

                funcionario.setContato(contato);
                funcionario.setEndereco(endereco);

                funcionarios.add(funcionario);
            }

            return funcionarios;
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
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
    public boolean activateFuncionario(Funcionario funcionario) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(ACTIVATE);

            pstmt.setInt(1, funcionario.getFlagAtivo());
            pstmt.setLong(2, funcionario.getCpf());

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
    public Funcionario findFuncionarioById(Funcionario funcionario) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ID);

            pstmt.setLong(1, funcionario.getId());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            funcionario.setId(Integer.parseInt(rs.getString("id")));
            funcionario.setNomeCompleto(rs.getString("nome"));
            funcionario.setCpf(Long.parseLong(rs.getString("cpf")));
            funcionario.setRg(Long.parseLong(rs.getString("rg")));
            funcionario.setCargo(TipoFuncionario.valueOf(rs.getString("tipo")));
            funcionario.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));

            Contato contato = new Contato();
            contato.setId(Integer.parseInt(rs.getString("contato")));

            Endereco endereco = new Endereco();
            endereco.setId(Integer.parseInt(rs.getString("endereco")));

            funcionario.setContato(contato);
            funcionario.setEndereco(endereco);

            return funcionario;
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
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
