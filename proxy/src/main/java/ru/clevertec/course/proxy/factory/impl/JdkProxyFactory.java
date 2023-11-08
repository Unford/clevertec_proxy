package ru.clevertec.course.proxy.factory.impl;

import ru.clevertec.course.proxy.factory.ProxyFactory;
import ru.clevertec.course.proxy.handler.ObjectInvocationHandler;
import ru.clevertec.course.proxy.util.ProxyReflectionUtils;

import java.lang.reflect.Proxy;

public class JdkProxyFactory implements ProxyFactory {


    @Override
    public <T> T createProxy(Object object, Class<T> tClass, ObjectInvocationHandler handler) {
        Class<?>[] interfaces = ProxyReflectionUtils.getAllSuperInterfaces(tClass)
                .toArray(new Class[0]);

        return (T) Proxy.newProxyInstance(
                tClass.getClassLoader(),
                interfaces,
                ((proxy, method, args) -> handler.invoke(object, proxy, method, args)));
    }
}
