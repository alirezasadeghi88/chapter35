package homework2;

import java.sql.*;
import javax.sql.RowSet;
import com.sun.rowset.JdbcRowSetImpl;

public class ScrollUpdateRowSet {
    public static void main(String[] args)
        throws SQLException,ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded");

        RowSet rowSet = new JdbcRowSetImpl();

        rowSet.setUrl("jdbc:postgresql://localhost:5432/javabook2");
        rowSet.setUsername("postgres");
        rowSet.setPassword("postgres");
        rowSet.setCommand("select state, capital from StateCapital");
        rowSet.execute();

        System.out.println("Before update ");
        displayRowSet(rowSet);

        rowSet.absolute(2);
        rowSet.updateString("state", "New S");
        rowSet.updateString("capital", "New C");
        rowSet.updateRow();

        rowSet.last();
        rowSet.moveToInsertRow();
        rowSet.updateString("state", "Florida");
        rowSet.updateString("capital", "Tallahassee");
        rowSet.insertRow();
        rowSet.moveToCurrentRow();

        rowSet.absolute(4);
        rowSet.deleteRow();
        System.out.println("After update ");
        displayRowSet(rowSet);

        rowSet.close();
    }

    private static void displayRowSet(RowSet rowSet)throws SQLException {
        ResultSetMetaData resultSetMetaData = rowSet.getMetaData();
        rowSet.beforeFirst();
        while (rowSet.next()) {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
                System.out.printf("%-12s\t",rowSet.getObject(i));
            System.out.println();
        }
    }
}
