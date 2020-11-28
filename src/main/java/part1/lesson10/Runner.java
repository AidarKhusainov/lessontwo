package part1.lesson10;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class Runner {
    String SOME_CLASS_PATH = "src/main/java/part1/lesson10/SomeClass.java";
    String WORKER_PATH = "src/main/java/part1/lesson10/Worker.java";
    private String bodyMethod;
    private String[] fileContent;


    public void start() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, InterruptedException {
        System.out.println("Введите тело метода:");
        InputConsole inputConsole = new InputConsole();
        bodyMethod = inputConsole.getContentFromConsole();

        FileHandler fileHandler = new FileHandler(Paths.get(SOME_CLASS_PATH));
        fileContent = fileHandler.getFileContent().toArray(new String[0]);
        addBodyToMethod();

        fileHandler.saveToFile(Paths.get(SOME_CLASS_PATH), Arrays.asList(fileContent));

        System.out.println("В метод добавлено тело");
        System.out.println("Компиляция файла");

        String[] fileToCompile = new String[]{SOME_CLASS_PATH, WORKER_PATH};
        MyCompiler myCompiler = new MyCompiler(fileToCompile);
        myCompiler.compile();

        System.out.println("Компиляция завершена. \nВыполнение загрузки класса и вызов метода");

        loadAndStart();
    }

    private void loadAndStart() throws ClassNotFoundException, IllegalAccessException,
            InstantiationException {
        String absolutePatch = "src/main/java/part1/lesson10/SomeClass";
        CustomClassLoader customClassLoader = new CustomClassLoader("part1.lesson10.SomeClass");

        Class<?> loadedClass = customClassLoader.findClass(absolutePatch);

        Worker object = (Worker) loadedClass.newInstance();
        object.doWork();
    }

    private void addBodyToMethod() {
        for (int i = 0; i < fileContent.length; i++) {
            if ("public void doWork() {  }".trim().equals(fileContent[i].trim())) {
                fileContent[i] = "public void doWork() {\n" + bodyMethod + "}";
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Runner runner = (Runner) o;
        return Objects.equals(SOME_CLASS_PATH, runner.SOME_CLASS_PATH) &&
                Objects.equals(WORKER_PATH, runner.WORKER_PATH) &&
                Objects.equals(bodyMethod, runner.bodyMethod) &&
                Arrays.equals(fileContent, runner.fileContent);
    }
}
