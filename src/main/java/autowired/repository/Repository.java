package autowired.repository;

import autowired.model.Student;
import autowired.annotations.Component;

import java.util.List;

@Component
public class Repository {

    public List<Student> getStudent(){
        return List.of(new Student("Suman",11), new Student("susmita",11));
    }
}
