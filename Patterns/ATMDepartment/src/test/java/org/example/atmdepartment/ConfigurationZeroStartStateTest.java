package org.example.atmdepartment;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationZeroStartStateTest {
    @Test
    public void ConfigurationZeroStartStateCreationTest() {
        ConfigurationZeroStartState configuration = new ConfigurationZeroStartState();
        Assert.assertTrue(configuration.params().getClass().toString().contains("DepartmentZeroStartState"));
    }

}