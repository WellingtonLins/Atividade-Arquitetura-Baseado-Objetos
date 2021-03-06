package com.codigo.wellington.node3;

import com.codigo.wellington.shared.Order;
import com.codigo.wellington.shared.Salesman;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Date 11/08/2017 @Time 10:46:09
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class DaoNode3 {

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    public static void persistOrder(Order order) throws Exception {

        try {
            // 1. Get a connection to database        jdbc:postgresql://host/database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_node_3", "postgres", "123");

            // 2. Prepare statement
            String sql = "insert into ordert (id, salesmanid ,productid ,quantity)"
                    + " values (?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getSalesman().getPerson().getId());
            preparedStatement.setInt(3, order.getProduct().getId());
            preparedStatement.setInt(4, order.getQuantity());

            System.out.println(sql);
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
