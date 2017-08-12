package com.codigo.wellington.node1;

import com.codigo.wellington.shared.Person;
import com.codigo.wellington.shared.Product;
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
public class DaoNode1 {

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    public static void persistPerson(Person person) throws Exception {

        try {
            // 1. Get a connection to database        jdbc:postgresql://host/database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_node_1", "postgres", "123");

            // 2. Prepare statement
            String sql = "insert into person (id,name) values (?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());

            System.out.println("insert into person (id,name) values ("+person.getId()+", " +person.getName()+")");
            preparedStatement.executeUpdate();

            System.out.println("\nCompleted successfully!");

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(connection, preparedStatement);
        }
    }

    public static void persistProduct(Product product) throws Exception {

     try {
            // 1. Get a connection to database        jdbc:postgresql://host/database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_node_1", "postgres", "123");

            // 2. Prepare statement
            String sql = "insert into product (id,name) values (?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,product.getId());
            preparedStatement.setString(2, product.getName());

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
