package org.example.ClassLoaderAndReflection;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Main(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) {
        Main solution = new Main(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "org/example/ClassLoaderAndReflection/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() {
        File folder;
        MyClassLoader loader;
        try {
            folder = new File(packageName);
            for (File file : folder.listFiles()) {
                if (String.valueOf(file).endsWith(".class")) {
                    loader = new MyClassLoader();
                    Class<?> clazz = loader.loadClass(String.valueOf(file));
                    hiddenClasses.add(clazz);
                }
            }
        }catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        HiddenClass result = null;
        for (Class clazz : hiddenClasses) {
            if (clazz.getName().toLowerCase().contains(key.toLowerCase())) {
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    result = (HiddenClass) constructor.newInstance();
                    return result;
                } catch (Exception e ) {
                    System.out.println(e.getMessage());
                }

            }
        }
        return result;
    }

    protected class MyClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                File file = new File(name);
                byte[] buffer = Files.readAllBytes(file.toPath());
                return defineClass(null, buffer, 0, buffer.length);
            } catch (IOException e) {
                return super.findClass(name);
            }
        }
    }
}

