package org.example.agregator.view;


import org.example.agregator.Controller;
import org.example.agregator.vo.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
