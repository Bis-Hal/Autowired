package autowired.controllers;

import autowired.annotations.Autowired;
import autowired.annotations.Component;
import autowired.model.Student;
import autowired.services.Services;

import java.util.List;

@Component
public class Controller {

    Services services;

    @Autowired
    public Controller(Services services) {
        this.services = services;
    }

    public List<Student> getStudent() {
        return services.getStudent();
    }

}
