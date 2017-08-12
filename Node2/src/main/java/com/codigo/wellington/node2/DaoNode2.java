package com.codigo.wellington.node2;


import com.codigo.wellington.shared.Salesman;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Date 11/08/2017  @Time 10:46:09
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class DaoNode2 {


    public static void  persistir(Salesman salesman) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1. Get a connection to database        jdbc:postgresql://host/database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_node_2", "postgres", "123");

            // 2. Prepare statement
            String sql = "insert into salesman (id,phone) values (?,?)";
          
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, salesman.getPerson().getId());
            preparedStatement.setString(2, salesman.getPhone());

              System.out.println("insert into salesman (id,name) values ("+ salesman.getPerson().getId()
                      +", " +salesman.getPhone()+")");
         
            preparedStatement.executeUpdate();
            System.out.println("\nCompleted successfully!");

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(connection, preparedStatement);
        }
    }

    private static void close(Connection connection, PreparedStatement preparedStatement)
            throws SQLException {

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

}
