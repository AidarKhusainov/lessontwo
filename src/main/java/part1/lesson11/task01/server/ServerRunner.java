package part1.lesson11.task01.server;

import netscape.security.UserTarget;

import java.io.*;
import java.net.*;
import java.util.LinkedList;


public class ServerRunner {

    public static final int PORT = 8080;
    public static LinkedList<ServerService> serverList = new LinkedList<>();
    public static Story story;

    private static void start() throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            story = new Story();
            System.out.println("Server Started");
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerService(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        start();
    }
}
