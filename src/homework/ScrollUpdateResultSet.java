package homework;

import java.sql.*;

public class ScrollUpdateResultSet {
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        // بارگذاری درایور
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded");

        // اتصال به دیتابیس
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/javabook",
                "postgres", "postgres");
        connection.setAutoCommit(true);
        System.out.println("Database connected");

        // ساخت Statement قابل اسکرول و قابل ویرایش
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        // اجرای کوئری
        ResultSet resultSet = statement.executeQuery(
                "SELECT state, capital FROM StateCapital");

        System.out.println("Before update:");
        displayResultSet(resultSet);

        // --- Update ---
        if (resultSet.absolute(1)) { // رفتن به ردیف اول
            resultSet.updateString("state", "New York");
            resultSet.updateString("capital", "New D");
            resultSet.updateRow();
        }

        // --- Insert ---
        resultSet.moveToInsertRow();
        resultSet.updateString("state", "Florida");
        resultSet.updateString("capital", "Tallahassee");
        resultSet.insertRow();
        resultSet.moveToCurrentRow();

        // --- Delete ---
        if (resultSet.absolute(4)) { // فقط اگر ردیف چهارم وجود داشته باشد
            resultSet.deleteRow();
        }

        // نمایش بعد از تغییرات
        System.out.println("After update:");
        resultSet = statement.executeQuery(
                "SELECT state, capital FROM StateCapital");
        displayResultSet(resultSet);

        // بستن منابع
        resultSet.close();
        statement.close();
        connection.close();
    }

    // متد نمایش ResultSet
    public static void displayResultSet(ResultSet resultSet)
            throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        resultSet.beforeFirst();
        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.printf("%-12s\t", resultSet.getObject(i));
            }
            System.out.println();
        }
    }
}