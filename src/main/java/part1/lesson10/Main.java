package part1.lesson10;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {

        Properties p = System.getProperties();
        String sep = p.getProperty("file.separator");
        String jrePath = p.getProperty("java.home");
        int lastIndex = jrePath.lastIndexOf(sep);
        String javac = jrePath.substring(0, lastIndex) + sep + "bin" + sep + "javac";

        if (p.getProperty("sun.desktop").equals("windows"))
            javac += ".exe";
        else javac += ".sh";

        File jc = new File(javac);

        if (!jc.isFile())
            throw new FileNotFoundException("Компилятор по адресу " + jc.getAbsolutePath() + " недоступен ");

        File file = new File("src/main/java/part1/lesson10/SomeClass.java");
        File fileInterface = new File("src/main/java/part1/lesson10/Worker.java");

        System.out.println("Компилируем " + jc.getAbsolutePath() + " " + file.getAbsolutePath() + " " + fileInterface.getAbsolutePath());

        Process p1 = Runtime.getRuntime().exec(javac + " " + file.getAbsolutePath() + " " + fileInterface.getAbsolutePath());

        if (p1.waitFor() != 0) {
            try {
                throw new Exception("Ошибка компиляции");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println("Компиляция завершена");

        String absolutePatch = "part1.lesson10.SomeClass";

        CustomClassLoader loader = new CustomClassLoader();
        Class cl = loader.loadClass(absolutePatch);

        System.out.println("Класс загружен: " + cl);

        Method method = cl.getMethod("doWork", null);

        method.invoke(cl.newInstance(), null);
    }
}
