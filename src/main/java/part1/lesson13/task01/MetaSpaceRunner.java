package part1.lesson13.task01;

public class MetaSpaceRunner {
    public static void main(String[] args) {
        MetaSpaceGenerator metaSpaceGenerator = new MetaSpaceGenerator(
                "src/main/java/part1/lesson13/task01/Fake",
                "part1.lesson13.task01.Fake");

        metaSpaceGenerator.generate();
    }
}