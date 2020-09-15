package org.example.agregator.model;

import org.example.agregator.view.View;
import org.example.agregator.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (providers == null || view == null || providers.length == 0)
            throw new IllegalArgumentException("Constructor Model: wrong null");
        try {
            this.view = view;
            this.providers = providers;
        } catch (IllegalArgumentException e) {
            System.out.println("Constructor Model: " + e.getMessage());
        }
    }

    public void selectCity(String city) {
        List<Vacancy> list = new ArrayList<>();
        for(Provider provider : providers) {
            list.addAll(provider.getJavaVacancies(city));
        }
        view.update(list);
    }
}
