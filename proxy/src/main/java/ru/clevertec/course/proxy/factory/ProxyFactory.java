package ru.clevertec.course.proxy.factory;


import ru.clevertec.course.proxy.handler.ObjectInvocationHandler;

public interface ProxyFactory {
    <T> T createProxy(Object object, Class<T> tClass, ObjectInvocationHandler handler);
}
