package ru.clevertec.course.proxy.factory.impl;

import ru.clevertec.course.proxy.factory.ProxyFactory;
import ru.clevertec.course.proxy.handler.ObjectInvocationHandler;
import ru.clevertec.course.proxy.util.ProxyReflectionUtils;

import java.lang.reflect.Proxy;

public class JdkProxyFactory implements ProxyFactory {

    @Override
    public Object createProxy(ObjectInvocationHandler handler) {
        Class<?> aClass = handler.getObject().getClass();
        Class<?>[] interfaces = ProxyReflectionUtils.getAllSuperInterfaces(aClass)
                .toArray(new Class[0]);

        return Proxy.newProxyInstance(
                aClass.getClassLoader(),
                interfaces,
                handler);
    }
}
