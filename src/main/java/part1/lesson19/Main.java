package part1.lesson19;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.start();
    }
}