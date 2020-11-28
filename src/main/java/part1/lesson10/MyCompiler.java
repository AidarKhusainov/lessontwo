package part1.lesson10;

import com.sun.deploy.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class MyCompiler {
    private Properties properties;
    private String separator;
    private String jrePath;
    private String javac;
    private String[] srcFilePath;

    public MyCompiler(String[] filePath) {
        properties = System.getProperties();
        separator = properties.getProperty("file.separator");
        jrePath = properties.getProperty("java.home");
        javac = jrePath.substring(0, jrePath.lastIndexOf(separator)) + separator + "bin" + separator + "javac";
        srcFilePath = filePath;
    }

    public void compile() throws IOException, InterruptedException {
        if ("windows".equals(properties.getProperty("sun.desktop"))) javac += ".exe";
        else javac += ".sh";

        File jc = new File(javac);

        if (!jc.isFile()) {
            throw new FileNotFoundException("Компилятор по адресу " + jc.getAbsolutePath() + " недоступен ");
        }

        Process p1 = Runtime.getRuntime()
                .exec(javac + " " + StringUtils.join(Arrays.asList(srcFilePath), " "));

        if (p1.waitFor() != 0) {
            try {
                throw new Exception("Ошибка компиляции");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
