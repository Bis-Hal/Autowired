package autowired.services;

import autowired.annotations.Autowired;
import autowired.annotations.Component;
import autowired.model.Student;
import autowired.repository.Repository;

import java.util.List;

@Component
public class Services {
    Repository repository;
    @Autowired
    public Services(Repository repository) {
        this.repository = repository;
    }

    public List<Student> getStudent(){
        return repository.getStudent();
    }
}
