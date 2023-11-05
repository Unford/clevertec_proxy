package ru.clevertec.course.autumn.factory.configurator;

import ru.clevertec.course.autumn.context.ApplicationContext;

public interface BeanConfigurator {
    void configure(Object t, ApplicationContext context);
}
