package org.example.atmdepartment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AskSumm implements Command {
    private Department department;

    AskSumm(Department department) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Department.class, new InterfaceAdapter());
        Gson gson = builder.create();
        if (department.getClass().toString().contains("DepartmentWithStartState")) {
            DepartmentWithStartState deepCopy = gson.fromJson(gson.toJson(department), DepartmentWithStartState.class);
            this.department = deepCopy;
        } else {
            DepartmentZeroStartState deepCopy = gson.fromJson(gson.toJson(department), DepartmentZeroStartState.class);
            this.department = deepCopy;
        }
    }

    @Override
    public Object execute() {
        int summ = 0;
        for (int i = 0; i < department.size(); i++) {
            summ += department.get(i).summ();
        }
        System.out.println("Сумма остатков во всех банкоматах департамента - " + summ);
        return summ;

    }
}
