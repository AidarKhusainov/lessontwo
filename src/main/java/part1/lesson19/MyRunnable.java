package part1.lesson19;

import java.sql.*;
import java.util.Arrays;

public class MyRunnable {
    private static Connection connection;
    private static String selectAllTables = "SELECT * " +
            " FROM less19.users as u " +
            " JOIN less19.user_role as ur ON u.user_code = ur.ur_user_code " +
            " JOIN less19.role r ON ur.ur_role_code = r.role_code";

    private static String selectAllTablesWithPrepared = "SELECT * " +
            " FROM less19.users as u " +
            " JOIN less19.user_role as ur ON u.user_code = ur.ur_user_code " +
            " JOIN less19.role r ON ur.ur_role_code = r.role_code " +
            "WHERE r.role_name = ?";

    public void start() throws SQLException {
        try {
            connection = JDBCSingleton.getInstance().getConnection();
            DBUtil.renewDatabase(connection);
        } catch (SQLException throwables) {
            System.out.println(String.valueOf(throwables));
        }

        Thread allUsers = new Thread(new SelectAllUsers());
        Thread usersWithPreparedStatement = new Thread(new SelectUsersWithPreparedStatement());
        Thread usersWithBatch = new Thread(new SelectUsersWithBatch());
        Thread selectSavepoint = new Thread(new SelectSavepoint());

        allUsers.start();
        usersWithPreparedStatement.start();
        usersWithBatch.start();
        selectSavepoint.start();

        try {
            allUsers.join();
            usersWithPreparedStatement.join();
            usersWithBatch.join();
            selectSavepoint.join();
        } catch (InterruptedException e) {
            System.out.println(String.valueOf(e));
        }

        connection.close();
        JDBCSingleton.closeConnection();
    }

    synchronized private static void printResultSetAllTables(String methodName, ResultSet resultSet) {
        System.out.println("====================Start " + methodName + "====================");
        try {
            System.out.println("");
            while (resultSet.next()) {
                System.out.print("user_code=" + resultSet.getInt("user_code"));
                System.out.print(" user_name=" + resultSet.getString("user_name"));
                System.out.print(" user_login=" + resultSet.getString("user_login"));
                System.out.print(" role_name=" + resultSet.getString("role_name"));
                System.out.print(" role_action=" + resultSet.getString("role_action"));
                System.out.print("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("====================End " + methodName + "====================");
        System.out.print("\n");
    }

    static class SelectAllUsers implements Runnable {
        @Override
        public void run() {
            try (Statement statement = (connection).createStatement();
                 ResultSet resultSet = statement.executeQuery(selectAllTables)) {

                printResultSetAllTables("SelectAllUsers", resultSet);

            } catch (SQLException throwables) {
                System.out.println(String.valueOf(throwables));
            }
        }
    }

    static class SelectUsersWithPreparedStatement implements Runnable {
        @Override
        public void run() {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllTablesWithPrepared)) {
                preparedStatement.setString(1, "ADMIN");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    printResultSetAllTables("SelectUsersWithPreparedStatement", resultSet);
                }
            } catch (SQLException throwables) {
                System.out.println(String.valueOf(throwables));
            }
        }
    }

    static class SelectUsersWithBatch implements Runnable {
        @Override
        public void run() {
            Integer[] localArgs = new Integer[]{1, 2, 3, 4};
            String sql = "update less19.users set user_login='Aidar' where user_code = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                for (Integer arg : localArgs) {
                    preparedStatement.setInt(1, arg);
                    preparedStatement.addBatch();
                }
                int[] ints = preparedStatement.executeBatch();
                System.out.println("Result batching: " + Arrays.toString(ints));

                try (PreparedStatement preparedState = connection.prepareStatement(selectAllTables);
                     ResultSet resultSet = preparedState.executeQuery()) {
                    printResultSetAllTables("SelectUsersWithBatch", resultSet);
                }
            } catch (SQLException throwables) {
                System.out.println(String.valueOf(throwables));
            }
        }
    }

    static class SelectSavepoint implements Runnable {
        @Override
        public void run() {
            String insertToliks =  "DO $$DECLARE " +
                    "r record; " +
                    "count int default 4; " +
                    "code int;\n" +
                    "BEGIN\n" +
                    "    FOR r IN 1..count\n" +
                    "        LOOP\n" +
                    "            " +
                    "INSERT INTO less19.users (user_name, user_login, user_password)\n" +
                    "            " +
                    "VALUES ('Tolya' || r, 'tolik' || r, 'qwerty') " +
                    "RETURNING user_code INTO code;\n" +
                    "            " +
                    "INSERT INTO less19.user_role (ur_user_code, ur_role_code) " +
                    "VALUES (code, 4);\n" +
                    "        END LOOP;\n" +
                    "END$$;\n";
            String insertGeorgies = "DO $$DECLARE " +
                    "r record; " +
                    "count int default 4; " +
                    "code int;\n" +
                    "BEGIN\n" +
                    "    FOR r IN 1..count\n" +
                    "        LOOP\n" +
                    "            " +
                    "INSERT INTO less19.users (user_name, user_login, user_password)\n" +
                    "            " +
                    "VALUES ('Georgy' || r, 'Georgy' || r, 'qwerty') " +
                    "RETURNING user_code INTO code;\n" +
                    "            " +
                    "INSERT INTO less19.user_role (ur_user_code, ur_role_code) " +
                    "VALUES (code, 4);\n" +
                    "        END LOOP;\n" +
                    "END$$;\n";

            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);

                statement.executeUpdate(insertToliks);

                ResultSet resultSet = connection.prepareStatement(selectAllTables).executeQuery();
                printResultSetAllTables("selectSavepoint 1", resultSet);

                Savepoint savepoint = connection.setSavepoint();

                statement.executeUpdate(insertGeorgies);

                resultSet = connection.prepareStatement(selectAllTables).executeQuery();
                printResultSetAllTables("selectSavepoint 2", resultSet);

                connection.rollback(savepoint);
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}