package org.example.factory.male;

import org.example.factory.AbstractFactory;
import org.example.factory.Human;

public class MaleFactory implements AbstractFactory {

    public Human getPerson(int age) {
        if (age > TeenBoy.MAX_AGE)
            return new Man();
        if (age <= KidBoy.MAX_AGE)
            return new KidBoy();
        return new TeenBoy();
    }
}
