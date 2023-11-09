package ru.clevertec.course.proxy.factory.impl;

import ru.clevertec.course.proxy.factory.ProxyFactory;
import ru.clevertec.course.proxy.handler.ObjectInvocationHandler;
import ru.clevertec.course.proxy.util.ProxyReflectionUtils;

import java.lang.reflect.Proxy;

public class JdkProxyFactory implements ProxyFactory {


    @Override
    public <T> T createProxy(Object object, Class<T> baseClass, ObjectInvocationHandler handler) {

        Class<?>[] interfaces = ProxyReflectionUtils.getAllSuperInterfaces(baseClass)
                .toArray(new Class[0]);

        return (T) Proxy.newProxyInstance(
                baseClass.getClassLoader(),
                interfaces,
                ((proxy, method, args) -> handler.invoke(baseClass, object, proxy, method, args)));
    }
}
