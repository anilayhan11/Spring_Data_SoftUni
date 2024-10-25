package com.jdbc_demo;

import java.sql.*;

public class PreparedMain {
    public static void main(String[] args) throws SQLException {
        String host = "localhost"; //or 127.0.0.1
        String port = "3306";
        String user = "root";
        String pass = "11011994";

        String url = String.format("jdbc:mysql://%s:%s", host, port);

        Connection connection = DriverManager.getConnection(url, user, pass);

        String query = "SELECT COUNT(*) FROM soft_uni.employees";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int employeeCount = resultSet.getInt(1);
        System.out.println(employeeCount);


        PreparedStatement manyColumns =
                connection.prepareStatement("SELECT employee_id, first_name, salary FROM soft_uni.employees LIMIT 1");
        ResultSet manyColumnsResult = manyColumns.executeQuery();
        manyColumnsResult.next();
        int id = manyColumnsResult.getInt(1);
        String name = manyColumnsResult.getString(2);
        float salary = manyColumnsResult.getFloat(3);
        System.out.printf("%d, %s, %.2f", id, name, salary);


        PreparedStatement manyRows =
                connection.prepareStatement("SELECT * FROM soft_uni.employees LIMIT 10");
        ResultSet manyRowsResult = manyRows.executeQuery();
        System.out.print(System.lineSeparator());
        while (manyRowsResult.next()) {
            System.out.printf("%d, %s, %.2f\n",
                    manyRowsResult.getInt("employee_id"),
                    manyRowsResult.getString("first_name"),
                    manyRowsResult.getFloat("salary")
            );
        }

        String query2 = "SELECT * FROM soft_uni.employees WHERE first_name LIKE ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
        preparedStatement2.setString(1, "%gu%");
        ResultSet resultSet2 = preparedStatement2.executeQuery();

        while (resultSet2.next()) {
            System.out.printf("%d -> %s",
                    resultSet2.getInt("employee_id"),
                    resultSet2.getString("first_name")
            );
        }

    }
}
