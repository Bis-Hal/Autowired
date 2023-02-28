package autowired;

import autowired.annotations.Autowired;
import autowired.annotations.Component;
import autowired.controllers.Controller;

@Component
public class AutowiredExample {

    static Controller controller;

    @Autowired
    public AutowiredExample(Controller controller) {
        AutowiredExample.controller = controller;
    }

    public static void main(String[] args) throws Exception {
       new BeansFactory().beanFactory();
        System.out.println(controller.getStudent());
    }

}
