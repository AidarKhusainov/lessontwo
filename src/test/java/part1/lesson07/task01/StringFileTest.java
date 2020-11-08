package part1.lesson07.task01;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringFileTest {

    @Test
    void getFileContent() {
        Path pathToGet = Paths.get("src\\main\\resources\\lesson07_task01.txt");
        StringFile stringFile = new StringFile(pathToGet);
        stringFile.setContent(stringFile.getFileContent());

        assertEquals(getListOfFileContent(), stringFile.getFileContent());
    }

    @Test
    void saveToFile() {
        Path pathToGet = Paths.get("src\\main\\resources\\lesson07_task01.txt");
        StringFile stringFile = new StringFile(pathToGet);
        stringFile.setContent(stringFile.getFileContent());

        Text text = new Text(stringFile.getContent());
        text.setSortedSrcText(text.getSortedListOfWords());

        Path pathToSave = Paths.get("src\\main\\resources\\result_lesson07_task01.txt");
        stringFile.saveToFile(pathToSave, text.getSortedSrcText());

        StringFile stringSaveFile = new StringFile(pathToSave);
        stringSaveFile.setContent(stringSaveFile.getFileContent());

        assertEquals(getContentOfSaveFile(), stringSaveFile.getContent());
    }

    private List<String> getListOfFileContent() {
        return Arrays.asList("В этой лекции завершается описание ключевых особенностей Java.",
                "Последняя тема раскрывает особенности создания многопоточных приложений -",
                "такая возможность присутствует в языке, начиная с самых первых версий.",
                "Первый вопрос - как на много- и, самое интересное.");
    }

    private List<String> getContentOfSaveFile() {
        return Arrays.asList("Java",
                "В",
                "Первый",
                "Последняя",
                "в",
                "версий",
                "возможность",
                "вопрос",
                "завершается",
                "и",
                "интересное",
                "как",
                "ключевых",
                "лекции",
                "много",
                "многопоточных",
                "на",
                "начиная",
                "описание",
                "особенностей",
                "особенности",
                "первых",
                "приложений",
                "присутствует",
                "раскрывает",
                "с",
                "самое",
                "самых",
                "создания",
                "такая",
                "тема",
                "этой",
                "языке");
    }
}