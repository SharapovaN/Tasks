package org.example.atmdepartment;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationWithStartStateTest {
    @Test
    public void ConfigurationWithStartStateCreationTest() {
        ConfigurationWithStartState configuration = new ConfigurationWithStartState();
        Assert.assertTrue(configuration.params().getClass().toString().contains("DepartmentWithStartState"));
    }

}