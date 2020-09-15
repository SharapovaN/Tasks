package org.example.atmdepartment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class State {
    private final Department department;

    State(Department department) {
        this.department = department;
    }

    State(State state) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Department.class, new InterfaceAdapter());
        Gson gson = builder.create();
        State deepCopy = gson.fromJson(gson.toJson(state), State.class);

        this.department=deepCopy.getArray();

    }

   public Department getArray() {
        return department;
    }
}