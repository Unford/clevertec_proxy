package ru.clevertec.course.proxy.factory;


public interface ProxyFactory<T> {
    Object createProxy(Object object, JoinPointHandler<T> handler);
}
