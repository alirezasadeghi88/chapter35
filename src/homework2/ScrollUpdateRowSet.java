package homework2;

import javax.sql.RowSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ScrollUpdateRowSet {
    public static void main(String[] args)
        throws SQLException,ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded");
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
