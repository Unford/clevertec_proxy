package ru.clevertec.course.proxy.factory;

@FunctionalInterface
public interface ThrowingFunction<T,R> {
    R apply(T t) throws Throwable;
}
