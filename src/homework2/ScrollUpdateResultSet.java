package homework2;

import java.sql.*;


public class ScrollUpdateResultSet {
    public static void main(String[] args)
        throws SQLException,ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/javabook2","postgres","postgres");
        connection.setAutoCommit(true);
        System.out.println("Database connected");

        Statement statement = connection.createStatement
                (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);


    }
}
