package part1.lesson10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileHandler {
    private final Path pathFile;
    private List<String> content;

    public FileHandler(Path pathFile) {
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

    public void saveToFile(Path pathToSave, List<String> content) {
        try {
            Files.write(pathToSave, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
