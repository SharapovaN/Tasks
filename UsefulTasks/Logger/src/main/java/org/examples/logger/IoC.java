package org.examples.logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class IoC {
  static TestLogginInterface createMyClass() {
    InvocationHandler handler = new org.examples.logger.IoC.DemoInvocationHandler(new TestLogginImpl());
    return (TestLogginInterface) Proxy.newProxyInstance(org.examples.logger.IoC.class.getClassLoader(),
        new Class<?>[]{TestLogginInterface.class}, handler);
  }

  static class DemoInvocationHandler implements InvocationHandler {
    private final TestLogginInterface myClass;

    DemoInvocationHandler(TestLogginInterface myClass) {
      this.myClass = myClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (method.isAnnotationPresent(Log.class)) {
        System.out.print("executed method:" + method.getName() + " param: ");
        for (Object i : args) {
          System.out.print(i + " ");
        }
      }
      return method.invoke(myClass, args);
    }

    @Override
    public String toString() {
      return "DemoInvocationHandler{" +
          "myClass=" + myClass +
          '}';
    }
  }
}
