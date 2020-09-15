package org.example.factory;

import org.example.factory.female.FemaleFactory;
import org.example.factory.male.MaleFactory;

public class FactoryProducer {

    public enum HumanFactoryType {
        MALE,
        FEMALE;
    }

    public static AbstractFactory getFactory(HumanFactoryType humanFactoryType) {
        AbstractFactory factory = null;
       switch (humanFactoryType) {
           case MALE:
               factory = new MaleFactory();
               break;
           case FEMALE:
               factory = new FemaleFactory();
               break;
       }
       return factory;
    }
}
