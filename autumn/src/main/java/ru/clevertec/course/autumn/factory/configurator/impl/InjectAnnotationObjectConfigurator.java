package ru.clevertec.course.autumn.factory.configurator.impl;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import ru.clevertec.course.autumn.annotation.BeanQualifier;
import ru.clevertec.course.autumn.context.ApplicationContext;
import ru.clevertec.course.autumn.factory.configurator.BeanConfigurator;

import java.lang.reflect.Field;

@Singleton
public class InjectAnnotationObjectConfigurator implements BeanConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                BeanQualifier qualifier = field.getAnnotation(BeanQualifier.class);
                field.setAccessible(true);
                Object object;
                if (qualifier == null) {
                    object = context.getObject(field.getType());
                } else {
                    object = context.getObject(field.getType(), qualifier.value());
                }
                field.set(t, object);
            }
        }
    }
}
