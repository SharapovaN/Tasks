package org.example.agregator;

import org.example.agregator.model.HHStrategy;
import org.example.agregator.model.Model;
import org.example.agregator.model.HabrStrategy;
import org.example.agregator.model.Provider;
import org.example.agregator.view.HtmlView;

public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        Provider[] provider = {new Provider(new HHStrategy()), new Provider(new HabrStrategy())};
        Model model = new Model(view, provider);
        Controller controller = new Controller(model);
        view.setController(controller);

        view.userCitySelectEmulationMethod();
    }
}
