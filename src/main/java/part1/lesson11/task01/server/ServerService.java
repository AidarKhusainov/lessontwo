package part1.lesson11.task01.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс реализует сокет серверной части
 */

class ServerService extends Thread {

    private Socket socket;
    private BufferedReader inputFromSocket;
    private BufferedWriter outputToSocket;
    private String nickname;

    public ServerService(Socket socket) throws IOException {
        this.socket = socket;
        inputFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputToSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ServerRunner.story.printStory(outputToSocket);
        start();
    }

    @Override
    public void run() {
        String word;
        try {
            word = inputFromSocket.readLine();
            try {
                nickname = getNicknameFromMsg(word)[0];
                outputToSocket.write(word + "\n");
                outputToSocket.flush();
            } catch (IOException ignored) {
            }
            try {
                while (true) {
                    word = inputFromSocket.readLine();
                    if (word.equals("quit")) {
                        this.downService();
                        break;
                    }
                    System.out.println("Echoing: " + word);
                    ServerRunner.story.addStoryEl(word);

                    String[] nicknamesToSent = getNicknameFromMsg(word);

                    if (nicknamesToSent.length > 0) {
                        for (String nickname : nicknamesToSent) {
                            Optional<ServerService> user = ServerRunner.serverList
                                    .stream()
                                    .filter(nick -> nick.nickname.equals(nickname)).findFirst();
                            if (user.isPresent()) {
                                user.get().send(word);
                            }
                        }
                    } else {
                        for (ServerService vr : ServerRunner.serverList) {
                            if (vr != this) {
                                vr.send(word);
                            }
                        }
                    }

                }
            } catch (NullPointerException ignored) {
            }


        } catch (IOException e) {
            this.downService();
        }
    }

    /**
     * Получение имени из сообщения
     */
    private String[] getNicknameFromMsg(String str) {
        Pattern pattern = Pattern.compile("@\\w+", Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        List<String> nicknames = new ArrayList<>();
        while (matcher.find()) {
            nicknames.add(matcher.group());
        }
        return nicknames.toArray(new String[0]);
    }

    /**
     * отсылка одного сообщения клиенту по указанному потоку
     */
    private void send(String msg) {
        try {
            outputToSocket.write(msg + "\n");
            outputToSocket.flush();
        } catch (IOException ignored) {
        }

    }

    /**
     * закрытие сервера
     */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                inputFromSocket.close();
                outputToSocket.close();
                for (ServerService vr : ServerRunner.serverList) {
                    if (vr.equals(this)) vr.interrupt();
                    ServerRunner.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {
        }
    }
}
