package org.example.factory.female;

import org.example.factory.AbstractFactory;
import org.example.factory.Human;

public class FemaleFactory implements AbstractFactory {

    public Human getPerson(int age) {
        if (age > TeenGirl.MAX_AGE)
            return new Woman();
        if (age <= KidGirl.MAX_AGE)
            return new KidGirl();
        return new TeenGirl();
    }
}
