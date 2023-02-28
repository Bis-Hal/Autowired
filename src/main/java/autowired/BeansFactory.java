package autowired;

import autowired.annotations.Autowired;
import autowired.annotations.Component;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BeansFactory {
    HashMap<Class<?>, Constructor<?>> constructorWithAutowired = new HashMap<>();
    HashMap<Class<?>, Constructor<?>> parameterizedConstructors = new HashMap<>();
    HashMap<Class<?>, List<Constructor<?>>> availableConstructors = new HashMap<>();
    public void beanFactory() {

        Reflections reflections = new Reflections("autowired");


        Set<Class<?>> classesWithComponentAnnotations = reflections.getTypesAnnotatedWith(Component.class);

        classesWithComponentAnnotations.forEach(aClass -> availableConstructors.put(aClass, List.of(aClass.getConstructors())));

        availableConstructors.forEach((aClass, constructors) -> constructors
                .forEach(constructor -> {
                    if (constructor.getParameterCount() > 0) {
                        parameterizedConstructors.put(aClass,constructor);
                    }
                    if (constructor.isAnnotationPresent(Autowired.class)) {
                        constructorWithAutowired.put(aClass, constructor);
                    }
                }));

        constructorWithAutowired.forEach((aClass, constructor) -> {
            if (parameterizedConstructors.containsValue(constructor)) {
                try {
                    getInstance(constructor);
                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                    System.err.println("Exception " + e.getCause());
                }
            } else {
                try {
                    System.out.println("Yes");
                    constructor.newInstance();
                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                    System.err.println("Exception " + e.getCause());
                }
            }
        });
    }

    public Object getInstance(Constructor<?> constructor) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Parameter parameter : constructor.getParameters()) {
            if(parameterizedConstructors.containsKey(parameter.getType())){
                return constructor.newInstance(getInstance(parameterizedConstructors.get(parameter.getType())));
            }
            else {
                return constructor.newInstance(parameter.getType().newInstance());
            }
        }
        return null;
    }
}








