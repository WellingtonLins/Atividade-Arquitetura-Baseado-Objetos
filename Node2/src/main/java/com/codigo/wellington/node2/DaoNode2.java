package com.codigo.wellington.node2;

import com.codigo.wellington.shared.Person;
import com.codigo.wellington.shared.Salesman;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Date 11/08/2017 @Time 10:46:09
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class DaoNode2 {

    public static void persistir(Salesman salesman) throws Exception {

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

            System.out.println("insert into salesman (id,name) values (" + salesman.getPerson().getId()
                    + ", " + salesman.getPhone() + ")");

            preparedStatement.executeUpdate();
            System.out.println("\nCompleted successfully!");

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(connection, preparedStatement);
        }
    }

    public static Person consultaPerson(Person person) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1. Get a connection to database        jdbc:postgresql://host/database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_node_1", "postgres", "123");

            // 2. Prepare statement
            //3. Instanteate a person
            Person p = null;
            String sql = "SELECT * FROM person  WHERE id= ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, person.getId());
            ResultSet rs = ps.executeQuery();
            
            System.out.println("SELECT * FROM person  WHERE id = " + person.getId());
            System.out.println("\nCompleted successfully!");

            if (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");

                p = new Person(id, name);
            }
            return p;
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(connection, preparedStatement);
        }

        return null;
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
