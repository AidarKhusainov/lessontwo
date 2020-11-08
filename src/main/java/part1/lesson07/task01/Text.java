package part1.lesson07.task01;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для работы с текстом:
 * - распознает и возвращает слова из текста, игнорируя символы (знаки препинания и тд)
 */
public class Text {
    private final List<String> srcText;
    private SortedSet<String> sortedSrcText;

    public Text(List<String> srcText) {
        this.srcText = srcText;
    }

    public SortedSet<String> getSortedListOfWords() {

        SortedSet<String> words = new TreeSet<>();

        for (String line : srcText) {

            Pattern pattern = Pattern
                    .compile("\\w+",
                            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                words.add(matcher.group());
            }
        }
        return words;
    }

    public SortedSet<String> getSortedSrcText() {
        return sortedSrcText;
    }

    public void setSortedSrcText(SortedSet<String> sortedSrcText) {
        this.sortedSrcText = sortedSrcText;
    }
}
