package part1.lesson19;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private DBUtil() {
    }

    public static void renewDatabase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
        ) {
            statement.execute("-- Database: jdbcDB\n"
                    + "DROP TABLE IF EXISTS less19.user_role, less19.role, less19.users;"
                    + "\n"
                    + "CREATE TABLE less19.users (\n"
                    + "    user_code bigserial primary key,\n"
                    + "    user_name varchar(100) NOT NULL,\n"
                    + "    user_login varchar(100) NOT NULL,\n"
                    + "    user_password varchar(100) NOT NULL);"
                    + "\n"
                    + "INSERT INTO less19.users(user_name, user_login, user_password)\n"
                    + "VALUES\n"
                    + "   ('Petr', 'petr', '1234'),\n"
                    + "   ('Ivan', 'ivan', 'qwerty'),\n"
                    + "   ('Oleg', 'olega', 'ytrewq'),\n"
                    + "   ('Alex', 'lexa', 'asd123');"
                    + "\n"
                    + "CREATE TABLE less19.role (\n"
                    + "    role_code bigserial primary key,\n"
                    + "    role_name varchar(100) NOT NULL,\n"
                    + "    role_action varchar(100) NOT NULL);"
                    + "\n"
                    + "INSERT INTO less19.role (role_name, role_action)\n"
                    + "VALUES\n"
                    + "   ('USER', 'CREATE'),\n"
                    + "   ('USER', 'SELECT'),\n"
                    + "   ('USER', 'UPDATE'),\n"
                    + "   ('GUEST', 'SELECT'),\n"
                    + "   ('ADMIN', 'CREATE'),\n"
                    + "   ('ADMIN', 'UPDATE'),\n"
                    + "   ('ADMIN', 'DELETE'),\n"
                    + "   ('ADMIN', 'SELECT');"
                    + "\n"
                    + "CREATE TABLE less19.user_role (\n"
                    + "    ur_code bigserial primary key,\n"
                    + "    ur_user_code bigserial REFERENCES less19.users(user_code),\n"
                    + "    ur_role_code bigserial REFERENCES less19.role(role_code));"
                    + "\n"
                    + "INSERT INTO less19.user_role (ur_user_code, ur_role_code)\n"
                    + "VALUES\n"
                    + "   ('1', 1),\n"
                    + "   ('1', 2),\n"
                    + "   ('1', 3),\n"
                    + "   ('2', 2),\n"
                    + "   ('3', 4),\n"
                    + "   ('4', 7),\n"
                    + "   ('4', 8),\n"
                    + "   ('4', 6),\n"
                    + "   ('4', 5);"
                    + "\n"
            );
        }
    }
}
