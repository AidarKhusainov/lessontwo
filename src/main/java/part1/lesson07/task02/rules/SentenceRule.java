package part1.lesson07.task02.rules;

/**
 * Класс содержит правила составления предложений
 */
public class SentenceRule {
    private final int minNumberOfWords;
    private final int maxNumberOfWords;
    private final String wordSeparator;

    /**
     * Предложение заканчивается одним из символов
     */
    private final String[] sentenceEndsWith;

    /**
     * если true, то предложение начинается с заглавной буквы,
     * иначе false
     */
    private final Boolean startsWithCapitalLetter;

    /**
     * вероятность вхождения одного из слов @params words в предложение
     */
    private int probability;

    /**
     * массив слов по умолчанию
     */
    private String[] words;

    public SentenceRule(int minNumberOfWords,
                        int maxNumberOfWords,
                        String wordSeparator,
                        Boolean startsWithCapitalLetter,
                        String[] sentenceEndsWith) {
        this.minNumberOfWords = minNumberOfWords;
        this.maxNumberOfWords = maxNumberOfWords;
        this.wordSeparator = wordSeparator;
        this.startsWithCapitalLetter = startsWithCapitalLetter;
        this.sentenceEndsWith = sentenceEndsWith;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public int getMinNumberOfWords() {
        return minNumberOfWords;
    }

    public int getMaxNumberOfWords() {
        return maxNumberOfWords;
    }

    public String getWordSeparator() {
        return wordSeparator;
    }

    public Boolean getStartsWithCapitalLetter() {
        return startsWithCapitalLetter;
    }

    public String[] getSentenceEndsWith() {
        return sentenceEndsWith;
    }
}
