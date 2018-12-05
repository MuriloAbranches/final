package br.com.clinicamedica.dao.impl;

import br.com.clinicamedica.dao.PacienteDao;
import br.com.clinicamedica.model.Contato;
import br.com.clinicamedica.model.Endereco;
import br.com.clinicamedica.model.Paciente;
import br.com.clinicamedica.util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoImpl implements PacienteDao {

    private static final String INSERT = "INSERT INTO PACIENTES values (nextval('pacientes_seq'),?,?,?,?,?,?)";
    private static final String DELETE = "UPDATE PACIENTES SET flag_ativo = ? WHERE cpf = ?";
    private static final String SELECT_ALL = "SELECT * FROM PACIENTES WHERE flag_ativo = 1";
    private static final String SELECT = "SELECT * FROM PACIENTES WHERE cpf = ?";
    private static final String UPDATE = "UPDATE PACIENTES SET nome = ? WHERE cpf = ?";
    private static final String ACTIVATE = "UPDATE PACIENTES SET flag_ativo = ? WHERE cpf = ?";
    private static final String SELECT_ID = "SELECT * FROM PACIENTES WHERE id = ?";

    private Connection conexao;

    @Override
    public boolean createPaciente(Paciente paciente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, paciente.getNomeCompleto());
            pstmt.setLong(2, paciente.getCpf());
            pstmt.setLong(3, paciente.getRg());
            pstmt.setInt(4, paciente.getEndereco().getId());
            pstmt.setInt(5, paciente.getContato().getId());
            pstmt.setInt(6, paciente.getFlagAtivo());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(PacienteDaoC): " + e.getMessage());
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
    public boolean deletePaciente(Paciente paciente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, paciente.getFlagAtivo());
            pstmt.setLong(2, paciente.getCpf());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(PacienteDaoD): " + e.getMessage());
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
    public Paciente readPaciente(Paciente paciente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT);

            pstmt.setLong(1, paciente.getCpf());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            paciente.setId(Integer.parseInt(rs.getString("id")));
            paciente.setNomeCompleto(rs.getString("nome"));
            paciente.setCpf(Long.parseLong(rs.getString("cpf")));
            paciente.setRg(Long.parseLong(rs.getString("rg")));

            Endereco endereco = new Endereco();
            endereco.setId(Integer.parseInt(rs.getString("endereco")));

            Contato contato = new Contato();
            contato.setId(Integer.parseInt(rs.getString("contato")));

            paciente.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));
            paciente.setEndereco(endereco);
            paciente.setContato(contato);

            return paciente;

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO(PacienteDaoR): " + e.getMessage());
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
    public boolean updatePaciente(Paciente paciente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, paciente.getNomeCompleto());
            pstmt.setLong(2, paciente.getCpf());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(PacienteDaoU): " + e.getMessage());
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
    public List<Paciente> listPaciente() {
        try {
            List<Paciente> pacientes = new ArrayList<>();

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();

                paciente.setId(Integer.parseInt(rs.getString("id")));
                paciente.setNomeCompleto(rs.getString("nome"));
                paciente.setCpf(Long.parseLong(rs.getString("cpf")));
                paciente.setRg(Long.parseLong(rs.getString("rg")));

                Endereco endereco = new Endereco();
                endereco.setId(Integer.parseInt(rs.getString("endereco")));

                Contato contato = new Contato();
                contato.setId(Integer.parseInt(rs.getString("contato")));

                paciente.setEndereco(endereco);
                paciente.setContato(contato);

                pacientes.add(paciente);
            }

            return pacientes;
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO(PacienteDaoL): " + e.getMessage());
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
    public boolean activatePaciente(Paciente paciente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(ACTIVATE);

            pstmt.setInt(1, paciente.getFlagAtivo());
            pstmt.setLong(2, paciente.getCpf());

            pstmt.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO(PacienteDaoA): " + e.getMessage());
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
    public Paciente readPacienteById(Paciente paciente) {
       try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ID);

            pstmt.setLong(1, paciente.getId());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            paciente.setId(Integer.parseInt(rs.getString("id")));
            paciente.setNomeCompleto(rs.getString("nome"));
            paciente.setCpf(Long.parseLong(rs.getString("cpf")));
            paciente.setRg(Long.parseLong(rs.getString("rg")));

            Endereco endereco = new Endereco();
            endereco.setId(Integer.parseInt(rs.getString("endereco")));

            Contato contato = new Contato();
            contato.setId(Integer.parseInt(rs.getString("contato")));

            paciente.setFlagAtivo(Integer.parseInt(rs.getString("flag_ativo")));
            paciente.setEndereco(endereco);
            paciente.setContato(contato);

            return paciente;

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("ERRO(PacienteDaoR): " + e.getMessage());
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
