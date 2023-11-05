package ru.clevertec.course.autumn.context.config.impl;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import ru.clevertec.course.autumn.annotation.BasePackage;
import ru.clevertec.course.autumn.annotation.BeanQualifier;
import ru.clevertec.course.autumn.context.config.AutumnConfiguration;
import ru.clevertec.course.autumn.factory.BeanFactory;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationAutumnConfiguration implements AutumnConfiguration {

    private final Reflections scanner;
    private final Map<String, Class> ifc2ImplClass;

    public AnnotationAutumnConfiguration(Class<?> clazz) {

        this.scanner = new Reflections(new ConfigurationBuilder()
                .forPackages(extractPackages(clazz))
                .forPackage(clazz.getPackage().getName())
                .forPackage(BeanFactory.class.getPackage().getName())
                .addClassLoaders(clazz.getClassLoader()));
        this.ifc2ImplClass = scanImplClass();
    }

    private String[] extractPackages(Class<?> clazz) {
        BasePackage annotation = clazz.getAnnotation(BasePackage.class);
        return annotation == null ? new String[]{} : annotation.value();

    }

    private Map<String, Class> scanImplClass() {
        Map<String, Class> map = new HashMap<>();
        for (Class<?> aClass : scanner.getTypesAnnotatedWith(BeanQualifier.class)) {
            if (!aClass.isInterface() && !Modifier.isAbstract(aClass.getModifiers())) {
                BeanQualifier annotation = aClass.getAnnotation(BeanQualifier.class);
                map.put(annotation == null ? aClass.getName() : annotation.value(), aClass);
            }
        }
        return map;
    }


    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        if (classes.size() != 1) {
            throw new RuntimeException(ifc + " has 0 or more than one impl please add @BeanQualifier");
        }

        return classes.iterator().next();
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc, String name) {
        Class aClass = ifc2ImplClass.get(name);
        if (aClass == null || !Arrays.asList(aClass.getInterfaces()).contains(ifc)) {
            throw new RuntimeException(ifc + " has 0 or more than one impl, Can't find Bean with name " + name);
        }
        return aClass;
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }
}
