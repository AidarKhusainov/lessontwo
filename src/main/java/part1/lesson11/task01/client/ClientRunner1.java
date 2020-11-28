package part1.lesson11.task01.client;

public class ClientRunner1 {

    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        new ClientService(ipAddr, port);
    }
}