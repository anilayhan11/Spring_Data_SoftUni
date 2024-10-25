package com.jdbc_demo;

import java.sql.*;

public class UpdateMain {
    public static void main(String[] args) throws SQLException {

        String user = "root";
        String pass = "11011994";

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/soft_uni", user, pass);

        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE employees SET salary = salary * 2 WHERE employee_id = 1");

        int resultSet = preparedStatement.executeUpdate();

        System.out.println(resultSet);
    }
}
