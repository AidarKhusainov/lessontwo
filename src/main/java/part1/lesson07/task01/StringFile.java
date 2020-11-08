package part1.lesson07.task01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedSet;

/**
 * Класс работает со строковыми файлами:
 * - выдает содержимое файла,
 * - сохраняет в файл
 */
public class StringFile {
    private final Path pathFile;
    private List<String> content;

    public StringFile(Path pathFile) {
        this.pathFile = pathFile;
    }

    public List<String> getFileContent() {
        try {
            content = Files.readAllLines(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void saveToFile(Path pathToSave, SortedSet<String> content) {
        try {
            Files.write(pathToSave, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

}
