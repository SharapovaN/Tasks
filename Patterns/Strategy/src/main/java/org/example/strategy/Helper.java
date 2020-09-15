package org.example.strategy;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {

    //Метод генерирует рандомную строку
    public static String generateRandomString() {
        return new BigInteger(130, new SecureRandom()).toString(36);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
