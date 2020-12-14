package org.examples.testframework;

public class Statistic {
  private static int testsCount = 0;
  private static int successfulTestsCount = 0;
  private static int failedTestsCount = 0;

  public static int getTestsCount() {
    return testsCount;
  }

  public static int getSuccessfulTestsCount() {
    return successfulTestsCount;
  }

  public static int getFailedTestsCount() {
    return failedTestsCount;
  }

  public static void testCountPlus() {
    testsCount++;
  }

  public static void successfulTestCountPlus() {
    successfulTestsCount++;
  }

  public static void failedTestCountPlus() {
    failedTestsCount++;
  }

  public static void getStatistic() {
    System.out.println("General count of tests: " + testsCount);
    System.out.println("Successful tests count of tests: " + successfulTestsCount);
    System.out.println("Failed tests count of tests: " + failedTestsCount);
  }
}
