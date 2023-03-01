package autowired.services;

import autowired.annotations.Autowired;
import autowired.annotations.Component;
import autowired.model.Student;
import autowired.repository.Repository;

import java.util.List;

@Component
public class Services {

    public List<Student> getStudent(){
        return new Repository().getStudent();
    }
}
