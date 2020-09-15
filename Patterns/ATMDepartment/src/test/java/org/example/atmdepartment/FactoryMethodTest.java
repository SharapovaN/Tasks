package org.example.atmdepartment;

import org.junit.Test;

public class FactoryMethodTest {
    @Test
    public void CreateConfigurationFactoryTest() {
        Configuration config;
        config = ConfigurationFactory.getConfiguration("WithStartState");
        if (config.params().summa() == 0) {
            throw new RuntimeException("Something wrong with CreateConfigurationFactoryTest WithStartState case");
        }

        config = ConfigurationFactory.getConfiguration("ZeroStartState");
        if (config.params().summa() != 0) {
            throw new RuntimeException("Something wrong with CreateConfigurationFactoryTest ZeroStartState case");
        }

    }
}