package part1.lesson07.task02;

import org.junit.jupiter.api.Test;
import part1.lesson07.task02.rules.SentenceRule;
import part1.lesson07.task02.rules.TextRule;
import part1.lesson07.task02.rules.WordRule;

class TextGeneratorTest {

    @Test
    void getFiles() {
        TextGenerator textGenerator = new TextGenerator(
                new SentenceRule(1,
                        15,
                        " ",
                        true,
                        new String[]{". ", "! ", "? "}),
                new WordRule(1, 12),
                new TextRule(1, 20, "\n"));

        textGenerator.getFiles("src/main/resources/lesson07_task02/",
                2,
                2048,
                getArrayWords(),
                50);
    }

    String[] getArrayWords() {
        return new String[]{
                "Продам", "гараж", "почти", "даром"
        };
    }
}