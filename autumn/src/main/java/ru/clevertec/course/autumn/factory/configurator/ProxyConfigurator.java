package ru.clevertec.course.autumn.factory.configurator;


public interface ProxyConfigurator {
    Object replaceWithProxyIfNeeded(Object t, Class<?> implClass);
}
