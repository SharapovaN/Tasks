package org.example.fiboneccinumbers;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.fibonacci(9));     //34
        System.out.println(solution.fibonacci(5));     //5
        System.out.println(solution.fibonacci(2));     //1
        System.out.println(solution.fibonacci(1));     //1
    }

    public int fibonacci(int n) {
        int result = 0;
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n > 1)
           result = fibonacci(n - 1) + fibonacci(n - 2);

        return result;
    }
}
