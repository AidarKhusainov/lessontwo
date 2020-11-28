package part1.lesson11.task01.client;

import java.io.*;
import java.net.Socket;

/**
 * Класс реализует сокет клиентской части
 */
class ClientService {

    private Socket socket;
    private BufferedReader inputFromSocket;
    private BufferedWriter outputToSocket;
    private BufferedReader inputUser;
    private String ipAddress;
    private int port;
    private String nickname;

    public ClientService(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        try {
            this.socket = new Socket(ipAddress, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
            return;
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            inputFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputToSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname();
            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            ClientService.this.downService();
        }
    }

    /**
     * Ввод имени
     */

    private void pressNickname() {
        System.out.print("Press your nick: ");
        try {
            nickname = inputUser.readLine();
            outputToSocket.write("Hello @" + nickname + "\n");
            outputToSocket.flush();
        } catch (IOException ignored) {
        }
    }

    /**
     * закрытие сокета
     */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                inputFromSocket.close();
                outputToSocket.close();
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * Чтение сообщений из потока
     */
    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = inputFromSocket.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                ClientService.this.downService();
            }
        }
    }

    /**
     * Отправка сообщений
     */
    public class WriteMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    userWord = inputUser.readLine();
                    if (userWord.equals("quit")) {
                        outputToSocket.write("quit" + "\n");
                        outputToSocket.flush();
                        ClientService.this.downService();
                        break;
                    } else {
                        outputToSocket.write(nickname + ": " + userWord + "\n");
                    }
                    outputToSocket.flush();
                } catch (IOException e) {
                    ClientService.this.downService();
                }
            }
        }
    }
}

