package part1.lesson07.task02;

import part1.lesson07.task02.rules.SentenceRule;
import part1.lesson07.task02.rules.TextRule;
import part1.lesson07.task02.rules.WordRule;

import static part1.lesson02.task02.Main.getRandNumber;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Класс генерирует текст по правилам - из слов, предложений и абзацев
 */
public class TextGenerator {
    private final SentenceRule sentenceRule;
    private final WordRule wordRule;
    private final TextRule textRule;
    private Map<Integer, String> sentence = new HashMap<>();

    public TextGenerator(SentenceRule sentenceRule, WordRule wordRule, TextRule textRule) {
        this.sentenceRule = sentenceRule;
        this.wordRule = wordRule;
        this.textRule = textRule;
    }

    /**
     * Метод добавляет текст по пути
     */
    private void appendUsingBufferedWriter(String filePath, String text) {
        File file = new File(filePath);

        try (BufferedWriter br = new BufferedWriter(new FileWriter(file, true))) {
            if (text.contains(textRule.getDelimiterParagraph())) {
                br.newLine();
            } else {
                br.write(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод генерирует абзацы и заполняет файлы
     *
     * @param numOfFiles  - число генерируемых файлов
     * @param sizeInByte  - размер генерируемых файлов в байтах
     * @param words       - массив слов по умолчанию
     * @param probability - вероятность
     */
    public void getFiles(String path, int numOfFiles, int sizeInByte, String[] words, int probability) {
        sentenceRule.setProbability(probability);
        sentenceRule.setWords(words);

        File files;
        String tmpPath;

        for (int i = 0; i < numOfFiles; i++) {
            tmpPath = path + "file_" + i + ".txt";
            files = new File(tmpPath);
            while (files.length() < sizeInByte) {
                for (String paragraph : generateParagraph()) {
                    if (files.length() < sizeInByte)
                        appendUsingBufferedWriter(tmpPath, paragraph);
                }
            }
        }
    }

    private List<String> generateParagraph() {
        int numberOfSentencesInParagraph = getRandNumber(
                textRule.getMinNumberOfSentencesInParagraph(),
                textRule.getMaxNumberOfSentencesInParagraph());
        List<String> result = new ArrayList<>();

        for (int i = 0; i < numberOfSentencesInParagraph; i++) {
            result.add(i, generateSentence());
            sentence.clear();
        }
        result.add(numberOfSentencesInParagraph, textRule.getDelimiterParagraph());
        return result;
    }

    private String generateSentence() {
        boolean isWordIncludedInSentence = (int) (Math.random() * 100) < sentenceRule.getProbability();
        int numberOfWords = getRandNumber(sentenceRule.getMinNumberOfWords(), sentenceRule.getMaxNumberOfWords());
        int currentInd = 0;
        int wordCounter = 0;

        if (isWordIncludedInSentence) {
            appendToSentence(
                    getRandNumber(0, numberOfWords),
                    sentenceRule.getWords()[getRandNumber(0, sentenceRule.getWords().length - 1)]);
            wordCounter++;
        }

        if (numberOfWords > 0) {
            appendToSentence(
                    currentInd++,
                    generateWord(sentenceRule.getStartsWithCapitalLetter(),
                            wordRule.getMinLengthOfWords(),
                            wordRule.getMaxLengthOfWords()));
            wordCounter++;
            appendToSentence(currentInd++, sentenceRule.getWordSeparator());
        }

        while (wordCounter < numberOfWords) {
            appendToSentence(currentInd++,
                    generateWord(false,
                            wordRule.getMinLengthOfWords(),
                            wordRule.getMaxLengthOfWords()));
            wordCounter++;
            if ((getRandNumber(0, 1) == 1)) {
                appendToSentence(currentInd++, ",");
            }
            appendToSentence(currentInd++, sentenceRule.getWordSeparator());
        }

        if (numberOfWords > 0) {
            deleteFromSentence(--currentInd);
            appendToSentence(++currentInd,
                    sentenceRule.getSentenceEndsWith()[
                            getRandNumber(0,
                                    sentenceRule.getSentenceEndsWith().length - 1)]);
        }
        StringBuilder res = new StringBuilder();
        for (Map.Entry<Integer, String> map : getSentence().entrySet())
            res.append(map.getValue());

        return res.toString();
    }

    private String generateWord(boolean withCapitalLetter, int rangeFrom, int rangeTo) {
        final String upperCaseLexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lowerCaseLexicon = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder builder = new StringBuilder();

        while (builder.toString().length() == 0) {
            int length = getRandNumber(rangeFrom, rangeTo);

            if (withCapitalLetter) {
                builder.append(upperCaseLexicon.charAt(getRandNumber(
                        1,
                        upperCaseLexicon.length() - 1)));
                length--;
            }

            for (int i = 0; i < length; i++) {
                builder.append(lowerCaseLexicon.charAt(getRandNumber(
                        0,
                        lowerCaseLexicon.length() - 1)));
            }
        }
        return String.valueOf(builder);
    }

    private Map<Integer, String> getSentence() {
        return sentence;
    }

    private void appendToSentence(int index, String str) {
        this.sentence.putIfAbsent(index, str);
    }

    private void deleteFromSentence(int index) {
        if (this.sentence.get(index) != null)
            this.sentence.remove(index);
    }

}
