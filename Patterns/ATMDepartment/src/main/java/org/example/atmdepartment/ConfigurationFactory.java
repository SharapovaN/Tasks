package org.example.atmdepartment;

public class ConfigurationFactory {
    static Configuration getConfiguration(String param) {
        if ("WithStartState".equals(param)) {
            return new ConfigurationWithStartState();
        }
        if ("ZeroStartState".equals(param)) {
            return new ConfigurationZeroStartState();
        }
        throw new IllegalArgumentException("unknown param:" + param);
    }
}
