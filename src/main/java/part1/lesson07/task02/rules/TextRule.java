package part1.lesson07.task02.rules;

/**
 * Класс содержит правила составления текста, содержащего абзацы
 */
public class TextRule {
    private final int minNumberOfSentencesInParagraph;
    private final int maxNumberOfSentencesInParagraph;
    private final String delimiterParagraph;

    public TextRule(int minNumberOfSentencesInParagraph, int maxNumberOfSentencesInParagraph, String delimiterParagraph) {
        this.maxNumberOfSentencesInParagraph = maxNumberOfSentencesInParagraph;
        this.minNumberOfSentencesInParagraph = minNumberOfSentencesInParagraph;
        this.delimiterParagraph = delimiterParagraph;
    }

    public String getDelimiterParagraph() {
        return delimiterParagraph;
    }

    public int getMaxNumberOfSentencesInParagraph() {
        return maxNumberOfSentencesInParagraph;
    }

    public int getMinNumberOfSentencesInParagraph() {
        return minNumberOfSentencesInParagraph;
    }
}
