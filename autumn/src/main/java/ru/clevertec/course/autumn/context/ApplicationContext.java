package ru.clevertec.course.autumn.context;

import ru.clevertec.course.autumn.context.config.AutumnConfiguration;
import ru.clevertec.course.autumn.factory.BeanFactory;

public interface ApplicationContext {
    <T> T getObject(Class<T> type);
    <T> T getObject(Class<T> type, String name);

    AutumnConfiguration getConfig();

    void setBeanFactory(BeanFactory beanFactory);
}
