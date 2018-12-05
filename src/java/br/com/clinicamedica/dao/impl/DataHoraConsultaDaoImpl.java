package br.com.clinicamedica.dao.impl;

import br.com.clinicamedica.dao.DataHoraConsultaDao;
import br.com.clinicamedica.model.DataHoraConsulta;
import br.com.clinicamedica.util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataHoraConsultaDaoImpl implements DataHoraConsultaDao{

    private static final String SELECT_DATA_HORA_FLAG = "SELECT id, data_consulta, hora_consulta FROM DATA_HORA_CONSULTAS WHERE flag_ativo = 1 ORDER BY id";
    private static final String UPDATE = "UPDATE DATA_HORA_CONSULTAS SET flag_ativo = ? WHERE id = ?";
    private static final String SELECT_DATA_HORA = "SELECT * FROM DATA_HORA_CONSULTAS WHERE id = ?";
    private static final String SELECT_CONSULTA = "SELECT id, data_consulta, hora_consulta FROM DATA_HORA_CONSULTAS WHERE flag_ativo = 1 or id = ? ORDER BY id";
    
    private Connection conexao;
    
    @Override
    public List<DataHoraConsulta> readDataHoraConsultaByFlagAtivo() {
        try {
            List<DataHoraConsulta> dataHoraConsultas = new ArrayList<>();

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_DATA_HORA_FLAG);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DataHoraConsulta dataHoraConsulta = new DataHoraConsulta();

                dataHoraConsulta.setId(Integer.parseInt(rs.getString("id")));
                dataHoraConsulta.setDataConsulta(rs.getString("data_consulta"));
                dataHoraConsulta.setHoraConsulta(rs.getString("hora_consulta"));

                dataHoraConsultas.add(dataHoraConsulta);
            }
            return dataHoraConsultas;
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
    public DataHoraConsulta readDataHoraConsulta(DataHoraConsulta dataHoraConsulta) {
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_DATA_HORA);
            
            pstmt.setInt(1,dataHoraConsulta.getId());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            dataHoraConsulta.setId(Integer.parseInt(rs.getString("id")));
            dataHoraConsulta.setDataConsulta(rs.getString("data_consulta"));
            dataHoraConsulta.setHoraConsulta(rs.getString("hora_consulta"));

            return dataHoraConsulta;
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
    public boolean updateDataHoraConsulta(DataHoraConsulta dataHoraConsulta) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setInt(1, dataHoraConsulta.getFlagAtivo());
            pstmt.setInt(2, dataHoraConsulta.getId());

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
    public List<DataHoraConsulta> readDataHoraById(DataHoraConsulta dataHoraConsulta) {
         try {
            List<DataHoraConsulta> dataHoraConsultas = new ArrayList<>();

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_CONSULTA);
            pstmt.setInt(1, dataHoraConsulta.getId());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DataHoraConsulta dataHora = new DataHoraConsulta();

                dataHora.setId(Integer.parseInt(rs.getString("id")));
                dataHora.setDataConsulta(rs.getString("data_consulta"));
                dataHora.setHoraConsulta(rs.getString("hora_consulta"));

                dataHoraConsultas.add(dataHora);
            }
            return dataHoraConsultas;
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
