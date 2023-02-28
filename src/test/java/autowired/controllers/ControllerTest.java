package autowired.controllers;

import autowired.annotations.Component;
import autowired.model.Student;
import autowired.services.Services;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ControllerTest {
    Services services;

    public ControllerTest(Services services) {
        this.services = services;
    }

    @Test
    public List<Student> getStudent(){
        return services.getStudent();
    }
}