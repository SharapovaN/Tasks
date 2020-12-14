package org.examples.logger;

public class Demo {
  public static void main(String[] args) {
    TestLogginInterface myClass = IoC.createMyClass();
    myClass.calculation(6, 7);
  }
}
