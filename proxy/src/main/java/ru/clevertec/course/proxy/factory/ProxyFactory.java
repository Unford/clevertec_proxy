package ru.clevertec.course.proxy.factory;


import ru.clevertec.course.proxy.handler.ObjectInvocationHandler;

public interface ProxyFactory {
    Object createProxy(ObjectInvocationHandler handler);
}
