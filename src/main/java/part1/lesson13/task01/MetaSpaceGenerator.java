package part1.lesson13.task01;

import part1.lesson10.CustomClassLoader;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс воспроизводит ошибку java.lang.OutOfMemoryError: Metaspace
 * При запуске необходимо установить VM options: -XX:MaxMetaspaceSize={XXX}m
 * (чем меньше значение, тем быстрее воспроизводится ошибка)
 */
public class MetaSpaceGenerator {
    private String pathFromContentRoot;
    private String nameOfBinary;

    public MetaSpaceGenerator(String pathFromContentRoot, String nameOfBinary) {
        this.pathFromContentRoot = pathFromContentRoot;
        this.nameOfBinary = nameOfBinary;
    }

    public void generate() {
        try {
            ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
            List<Class> classLoaders = new ArrayList<>();


            while (true) {
                CustomClassLoader customClassLoader = new CustomClassLoader(nameOfBinary);

                Class loadedClass = customClassLoader.findClass(pathFromContentRoot);

                classLoaders.add(loadedClass);

                System.out.println("total: " + loadingBean.getTotalLoadedClassCount());
                System.out.println("active: " + loadingBean.getLoadedClassCount());
                System.out.println("unloaded: " + loadingBean.getUnloadedClassCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
