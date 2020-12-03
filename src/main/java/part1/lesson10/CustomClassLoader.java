package part1.lesson10;

import java.io.*;

public class CustomClassLoader extends ClassLoader {
    private String nameOfBinary;

    public CustomClassLoader(String nameOfBinary) {
        this.nameOfBinary = nameOfBinary;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File(name + ".class");

        if (!f.isFile()) throw new ClassNotFoundException("Нет такого класса " + name);

        InputStream ins = null;
        try {
            ins = new BufferedInputStream(new FileInputStream(f));
            byte[] b = new byte[(int) f.length()];
            ins.read(b);
            Class c = defineClass(nameOfBinary, b, 0, b.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException("Проблемы с байт кодом");
        } finally {
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}