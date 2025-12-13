//package homework;
//
//import javax.sql.RowSet;
//import java.sql.*;
//import com.sun.rowset.*;
//
//public class RowSetPreparedStatement {
//    public static void main(String[] args)
//        throws SQLException,ClassNotFoundException {
//
//        Class.forName("org.postgresql.Driver");
//        System.out.println("Driver loaded");
//
//        RowSet rowSet = new JdbcRowSetImpl();
//
//        rowSet.setUrl("jdbc:postgresql://localhost:5432/javabook");
//        rowSet.setUsername("postgres");
//        rowSet.setPassword("postgres");
//        rowSet.setCommand("select * from Student where lastName = ? " +
//                "and mi = ?");
//        rowSet.setString(1, "Smith");
//        rowSet.setString(2, "R");
//        rowSet.execute();
//
//        ResultSetMetaData resultSetMetaData = rowSet.getMetaData();
//        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
//            System.out.printf("%-12s\t",resultSetMetaData.getColumnName(i));
//        System.out.println();
//
//        while (rowSet.next()) {
//            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
//                System.out.printf("%-12s\t", rowSet.getObject(i));
//            System.out.println();
//        }
//
//        rowSet.close();
//    }
//}
