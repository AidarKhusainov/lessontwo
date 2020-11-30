package part1.lesson11.task01.server;


import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Класс реализует хранение сообщений
 */

class Story {

    private LinkedList<String> story = new LinkedList<>();

    public void addStoryEl(String el) {
        if (story.size() >= 10) {
            story.removeFirst();
        }
        story.add(el);
    }

    /**
     * печать истории сообщений
     */

    public void printStory(BufferedWriter writer) {
        if (story.size() > 0) {
            try {
                writer.write("History messages" + "\n");
                for (String vr : story) {
                    writer.write(vr + "\n");
                }
                writer.write("/...." + "\n");
                writer.flush();
            } catch (IOException ignored) {
            }

        }

    }
}