package org.example.findclass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class result = null;
        int c = 0;
        Class<?>[] classes = Collections.class.getDeclaredClasses();
        for (Class clazz : classes) {
            c++;
            if (List.class.isAssignableFrom(clazz)) {
                if (Modifier.isPrivate(clazz.getModifiers())) {
                    if (Modifier.isStatic(clazz.getModifiers())) {
                        Method method = null;
                        try {
                            method = clazz.getDeclaredMethod("get", int.class);
                            method.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            //e.printStackTrace();
                        }
                        Constructor constructor = null;
                        try {
                            constructor = clazz.getDeclaredConstructor();
                            constructor.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            //e.printStackTrace();
                        }
                        try {
                            if (constructor != null)
                                method.invoke(constructor.newInstance(), 0);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            if (e.getCause().toString().contains("IndexOutOfBoundsException"))
                                result = clazz;
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return result;
    }
}
