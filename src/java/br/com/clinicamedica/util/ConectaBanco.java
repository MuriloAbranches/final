package br.com.clinicamedica.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {

    public static Connection getConexao() throws ClassNotFoundException {
        Connection connect = null;
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinik", "postgres", "postgres");

        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
        return connect;
    }
}
