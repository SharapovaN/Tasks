package org.example.logparser.passwordgenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ArrayList<Character> temp = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        temp.add((char)(48 + (int) (Math.random()*10)));
        temp.add((char)(65 + (int) (Math.random()*26)));
        temp.add((char)(97 + (int) (Math.random()*26)));

        for (int i = 0; i < 5; i++) {
            int k = (int) (1+(Math.random()*3));
            switch (k) {
                case 1 : temp.add((char)(48 + (int) (Math.random()*10)));
                    break;
                case 2 : temp.add((char)(65 + (int) (Math.random()*26)));
                    break;
                case 3 : temp.add((char)(97 + (int) (Math.random()*26)));
                    break;
            }
        }
        Collections.shuffle(temp);

        for (Character ch : temp) {
            result.append(ch);
        }
        while (!isUnic(result)) {
            getPassword();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(result.toString().getBytes());
        return byteArrayOutputStream;
    }

    public static boolean isUnic(StringBuilder s) {
        Set<String> passwordSet = new HashSet<>();
        if (passwordSet.contains(s.toString())) {
            return false;
        } else {
            passwordSet.add(s.toString());
            return true;
        }
    }
}