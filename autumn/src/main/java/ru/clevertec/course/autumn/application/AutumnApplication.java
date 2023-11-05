package ru.clevertec.course.autumn.application;

import ru.clevertec.course.autumn.context.ApplicationContext;
import ru.clevertec.course.autumn.context.config.AutumnConfiguration;
import ru.clevertec.course.autumn.context.config.impl.AnnotationAutumnConfiguration;
import ru.clevertec.course.autumn.context.impl.AnnotationApplicationContext;
import ru.clevertec.course.autumn.factory.BeanFactory;

public final class AutumnApplication {
    private AutumnApplication() {
    }

    public static ApplicationContext run(Class<?> clazz, String[] args) {
        AutumnConfiguration config = new AnnotationAutumnConfiguration(clazz);
        ApplicationContext context = new AnnotationApplicationContext(config);
        BeanFactory beanFactory = new BeanFactory(context);
        context.setBeanFactory(beanFactory);
        return context;

    }
}
