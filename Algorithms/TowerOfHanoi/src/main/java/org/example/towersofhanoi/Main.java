package org.example.towersofhanoi;

public class Main {
    public static void main(String[] args) {
        int numRings = 3;
        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char a, char b, char c, int numRings) {
        if (numRings > 0) {
            moveRing(a, c, b, numRings - 1);
            System.out.format("from %s to %s", a, b);
            System.out.println();
            moveRing(c, b, a, numRings - 1);
        }
    }
}