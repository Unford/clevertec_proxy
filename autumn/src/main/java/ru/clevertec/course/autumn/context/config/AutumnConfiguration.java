package ru.clevertec.course.autumn.context.config;

import org.reflections.Reflections;

public interface AutumnConfiguration {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    <T> Class<? extends T> getImplClass(Class<T> ifc, String name);

    Reflections getScanner();
}
