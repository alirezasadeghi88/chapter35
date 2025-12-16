package homework;

import java.sql.SQLException;
import javax.sql.RowSet;
import com.sun.rowset.*;


public class SimpleRowSet {
    public static void main(String[] args)
        throws SQLException,ClassNotFoundException{

        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded");

        RowSet rowSet = new JdbcRowSetImpl();

        rowSet.setUrl("jdbc:postgresql://localhost:5432/javabook");
        rowSet.setUsername("postgres");
        rowSet.setPassword("postgres");
        rowSet.setCommand("select firstName, mi, lastName " +
                "from Student where lastName = 'Smith'");
        rowSet.execute();

        while (rowSet.next())
            System.out.println(rowSet.getString(1) + " \t " +
                    rowSet.getString(2) + " \t " + rowSet.getString(3));

        rowSet.close();
    }
}
