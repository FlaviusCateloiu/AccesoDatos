package Ejercicio12Repositorio;

import Ejercicio12Repositorio.dao.sql.*;

public class Ejercicio12Repositorio {
    public static void main(String[] args) {

        SQLiteDAOManagerImpl mundial = new SQLiteDAOManagerImpl();

        mundial.setAutoCommit(false);




        mundial.commit();

        mundial.close();
    }
}
