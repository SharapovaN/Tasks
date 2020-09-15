package org.example.atmdepartment;

public class ConfigurationWithStartState implements Configuration {
    @Override
    public Department params() {
        return new DepartmentWithStartState();
    }
}
