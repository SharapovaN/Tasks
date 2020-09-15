package org.example.narcissisisticnumbers;

import java.util.Arrays;
import java.util.TreeSet;

public class Main {
   public static TreeSet<Long> res = new TreeSet<>();
   public static long[][] degreeArray = new long[10][20];

   static {

       for (int i = 0; i < 10; i++){
           for (int j = 0; j < 20; j++) {
               if (j > 10) {
                   long rez = i;
                   int d = j;
                   while (d > 1) {
                       rez *= i;
                       d--;
                   }
                   degreeArray[i][j] = rez;
               } else {
                   degreeArray[i][j] = (long) Math.pow(i,j);
               }
           }
       }
   }

    public static long[] getNumbers(long N) {
        long[] result = null;

        if (N > 0 && N <= Long.MAX_VALUE) {
            for (long i = 1; i < N; i++) {
                if (isUnic(i)) {
                    long armstrongNum = sum(i);
                    if (isArmstrongNumber(armstrongNum))
                        res.add(armstrongNum);
                }
            }

            result = new long[res.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = res.first();
                res.remove(res.first());
            }
        }
            res.clear();
            return result;

        }

    public static boolean isUnic (long num) {
        int last = (int) (num % 10);
        num /= 10;
        while (last == 0 && num > 10) {
            last = (int) (num % 10);
            num /= 10;
        }
        int current;
        while (num > 0) {
            current = (int)(num % 10);
            if (last >= current) {
                last = current;
                num /= 10;
            }
            else
                return false;
        }
        return true;
    }

    public static boolean isArmstrongNumber (long num) {
       if (sum(num) == num) {
           return true;
       }
       else
           return false;
    }

    public static long sum(long num) {
        long sum = 0;
        String n = String.valueOf(num);
        char[] temp = n.toCharArray();
        int[] numArray = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            int c = Character.getNumericValue(temp[i]);
            numArray[i] = c;
        }

        for (int i = 0; i < numArray.length; i++) {
            sum += degreeArray[numArray[i]][numArray.length];
        }

        if (sum > 0 && sum <= Long.MAX_VALUE)
            return sum;
        else
            return 0;
    }


    public static void main(String[] args) {

        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a));

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(100000000000L)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a));

    }
}
