package ru.clevertec.course.proxy.factory.impl;

import ru.clevertec.course.proxy.domain.model.JdkProxyJoinPoint;
import ru.clevertec.course.proxy.factory.JoinPointHandler;
import ru.clevertec.course.proxy.factory.ProxyFactory;
import ru.clevertec.course.proxy.util.ProxyReflectionUtils;

import java.lang.reflect.Proxy;

public class JdkProxyFactory implements ProxyFactory<Object> {


    @Override
    public Object createProxy(Object object,
                             JoinPointHandler<Object> function) {
        Class<?>[] interfaces = ProxyReflectionUtils.getAllSuperInterfaces(object.getClass())
                .toArray(new Class[0]);

        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                interfaces,
                (proxy, method, methodArgs) ->
                        function.apply(new JdkProxyJoinPoint(proxy, object, method, methodArgs)));
    }


}
