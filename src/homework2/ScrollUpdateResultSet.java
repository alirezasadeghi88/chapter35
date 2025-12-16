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

        ResultSet resultSet = statement.executeQuery
                ("select state, capital from StateCapital");

        System.out.println("Before update ");
        displayResultSet(resultSet);

    }

    public static void displayResultSet(ResultSet resultSet)throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        resultSet.beforeFirst();
        while (resultSet.next())
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
                System.out.printf("%-12s\t",resultSet.getObject(i));
        System.out.println();
    }
}
