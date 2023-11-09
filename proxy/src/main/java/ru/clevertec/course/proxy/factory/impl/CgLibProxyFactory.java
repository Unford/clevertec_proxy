package ru.clevertec.course.proxy.factory.impl;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import ru.clevertec.course.proxy.factory.ProxyFactory;
import ru.clevertec.course.proxy.handler.ObjectInvocationHandler;
import ru.clevertec.course.proxy.util.Pair;
import ru.clevertec.course.proxy.util.ProxyReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CgLibProxyFactory implements ProxyFactory {


    @Override
    public <T> T createProxy(Object object, Class<T> tClass, ObjectInvocationHandler handler) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tClass);
        enhancer.setCallback((InvocationHandler) (Object proxy, Method method, Object[] args) ->
                handler.invoke(tClass, object, proxy, method, args));

        Constructor<?> constructor = ProxyReflectionUtils.getMinParamaterConstructor(tClass);
        Object proxy;
        if (constructor.getParameterCount() == 0) {
            proxy = enhancer.create();
        } else {
            Pair<Class<?>[], Object[]> parameters = ProxyReflectionUtils.extractParametersWithDefaultValue(constructor);
            proxy = enhancer.create(parameters.getFst(), parameters.getSnd());
        }
        return (T) proxy;
    }
}
