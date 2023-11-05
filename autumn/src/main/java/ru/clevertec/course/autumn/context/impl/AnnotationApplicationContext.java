package ru.clevertec.course.autumn.context.impl;

import jakarta.inject.Singleton;
import ru.clevertec.course.autumn.context.ApplicationContext;
import ru.clevertec.course.autumn.context.config.AutumnConfiguration;
import ru.clevertec.course.autumn.factory.BeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@SuppressWarnings("unchecked")
public class AnnotationApplicationContext implements ApplicationContext {

    private BeanFactory beanFactory;
    private final Map<Class<?>, Object> cache = new ConcurrentHashMap<>();

    private final AutumnConfiguration config;

    public AnnotationApplicationContext(AutumnConfiguration config) {
        this.config = config;
    }

    @Override
    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = beanFactory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;
    }

    @Override
    public <T> T getObject(Class<T> type, String name) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = config.getImplClass(type, name);
        }
        T t = beanFactory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;
    }

    @Override
    public AutumnConfiguration getConfig() {
        return config;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
