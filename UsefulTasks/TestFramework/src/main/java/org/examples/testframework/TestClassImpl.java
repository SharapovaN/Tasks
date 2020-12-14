package org.examples.testframework;

public class TestClassImpl{
  @Before(0)
  public void beforeAddTest() {
    System.out.println("BeforeAddTest method");
  }

  @Test(0)
  public void addTest() {
    System.out.println("AddTest Method run");
    Statistic.testCountPlus();
    if (Calculator.add(2, 4) == 3) {
      Statistic.successfulTestCountPlus();
      System.out.println("AddTest successful");
    } else {
      Statistic.failedTestCountPlus();
      System.out.println("AddTest failed");
    }
  }

  @After(0)
  public void afterAddTest() {
    System.out.println("AfterAddTest method");
    System.out.println("___________________");
  }

  @Before(1)
  public void beforeDivTest() {
    System.out.println("BeforeDivTest method");
  }

  @Test(1)
  public void DivTest() {
    System.out.println("DivTest Method run");
    Statistic.testCountPlus();
    if (Calculator.div(4, 2) == 2) {
      Statistic.successfulTestCountPlus();
      System.out.println("DivTest successful");
    } else {
      Statistic.failedTestCountPlus();
      System.out.println("DivTest failed");
    }
  }

  @After(1)
  public void afterDivTest() {
    System.out.println("AfterDivTest method");
    System.out.println("___________________");
  }


public static void run() {
   new TestRunner("org.examples.testframework.TestClassImpl");
 }
}
