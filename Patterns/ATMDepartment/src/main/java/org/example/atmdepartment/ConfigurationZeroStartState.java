package org.example.atmdepartment;

public class ConfigurationZeroStartState implements Configuration {
    @Override
    public Department params() {
        return new DepartmentZeroStartState();
    }
}
