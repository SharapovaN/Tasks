package org.examples.logger;

public class TestLogginImpl implements TestLogginInterface {

  @Override
  public void calculation(int param, int param1) {
    int sum = param + param1;
  }

  @Override
  public String toString() {
    return "TestLoggingImpl{}";
  }
}
