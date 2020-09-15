package org.example.workingwhithclassloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        Set<? extends Animal> allAnimals = getAllAnimals(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Main.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws Exception {
        String decodeFile = URLDecoder.decode(pathToAnimals, "UTF-8");
        File folder = new File(decodeFile);
        Set<Animal> set = new HashSet<>();
        ClassLoader loader = null;
        for (File file : folder.listFiles()) {
            if (String.valueOf(file).endsWith(".class")) {
                loader = new MyClassLoader();
                Class<?> clazz = loader.loadClass(String.valueOf(file));
            if (Animal.class.isAssignableFrom(clazz)) {
                try {
                    Constructor constructor = clazz.getConstructor();
                    set.add((Animal) constructor.newInstance());
                } catch (NoSuchMethodException e) {
                }
            }
            }
        }
        return set;
    }

    static class MyClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                File file = new File(name);
                byte[] buff = Files.readAllBytes(file.toPath());
                return defineClass(null, buff, 0, buff.length);
            } catch (IOException e) {
                return super.findClass(name);
            }
        }
        }
    }

