package org.examples.testframework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {
  private static Class<?> classForTesting;


  public TestRunner(String className) {
    try {
      classForTesting = Class.forName(className);
      startTests();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void startTests() {
    Method[] methods = classForTesting.getMethods();
    Constructor constructor = null;
    List<List<Method>> lists;
    Object testClass = null;
    try {
      constructor = classForTesting.getDeclaredConstructor();

    } catch (Exception e) {
      e.printStackTrace();
    }
    lists = methodsSort(methods);
    for (List<Method> list : lists) {
      try {
        testClass = constructor.newInstance();
      } catch (Exception e) {
        e.printStackTrace();
      }
      for (Method method : list) {
        if (method.isAnnotationPresent(Before.class)) {
          try {
            method.invoke(testClass);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        if (method.isAnnotationPresent(Test.class)) {
          try {
            method.invoke(testClass);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
        if (method.isAnnotationPresent(After.class)) {
          try {
            method.invoke(testClass);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
      }
    }
    Statistic.getStatistic();
  }

  private List<List<Method>> methodsSort(Method[] array) {
    List<Method> list = new ArrayList<>(Arrays.asList(array));
    List<List<Method>> result = new ArrayList<>();
    Map<Integer, ArrayList<Method>> sort = new HashMap<>();
    for (Method method : list) {
      if (method.isAnnotationPresent(Before.class)) {
        Integer key = method.getAnnotation(Before.class).value();
        if (!sort.containsKey(key)) {
          var methodsList = new ArrayList<Method>();
          methodsList.add(method);
          sort.put(key, methodsList);
        } else {
          sort.get(key).add(method);
        }
      }
    }

    for (Method method : list) {
      if (method.isAnnotationPresent(Test.class)) {
        sort.get(method.getAnnotation(Test.class).value()).add(method);
      }
    }

    for (Method method : list) {
      if (method.isAnnotationPresent(After.class)) {
        sort.get(method.getAnnotation(After.class).value()).add(method);
      }
    }

    for (Map.Entry<Integer, ArrayList<Method>> pair : sort.entrySet()) {
      result.add(pair.getValue());

    }
    return result;
  }


}
