package org.example.rectangles;

public class Main {

    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int counter = 0, temp = -1, jt = a[0].length;
        boolean findRect = false;

        while (temp != counter) {
            temp = counter;

            outerloop:
            for(int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    if (a[i][j] == 1 && !findRect) {
                        counter++;
                        jt = j;
                        findRect = true;
                        a[i][j] = 0;
                    }
                    else if (a[i][j] == 1 && findRect) {
                        a[i][j] = 0;
                    }
                    else if (a[i][j] == 0 && findRect && j == jt) {
                        break outerloop;
                    }
                    else if (a[i][j] == 0 && findRect && j > jt) {
                        break;
                    }
                }
            }
            findRect = false;
        }
        return counter;
    }
}
