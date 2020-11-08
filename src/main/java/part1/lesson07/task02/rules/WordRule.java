package part1.lesson07.task02.rules;

/**
 * Класс содержит правила составления слов
 */
public class WordRule {
    private final int minLengthOfWords;
    private final int maxLengthOfWords;

    public WordRule(int minLengthOfWords, int maxLengthOfWords) {
        this.minLengthOfWords = minLengthOfWords;
        this.maxLengthOfWords = maxLengthOfWords;
    }

    public int getMinLengthOfWords() {
        return minLengthOfWords;
    }

    public int getMaxLengthOfWords() {
        return maxLengthOfWords;
    }
}
