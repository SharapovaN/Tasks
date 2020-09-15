package org.example.agregator;

import org.example.agregator.model.Model;

public class Controller {
    private Model model;

    public Controller(Model model) {
        if (model == null)
            throw new IllegalArgumentException("Constructor Controller: wrong null");
        this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }

}
