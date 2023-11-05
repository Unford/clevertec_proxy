package ru.clevertec.course.proxy.factory.impl;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import ru.clevertec.course.proxy.factory.ProxyFactory;
import ru.clevertec.course.proxy.handler.ObjectInvocationHandler;

import java.lang.reflect.Method;

public class CgLibProxyFactory implements ProxyFactory {


    @Override
    public Object createProxy(Object object, ObjectInvocationHandler handler) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback((InvocationHandler) (Object proxy, Method method, Object[] args) ->
                handler.invoke(object, proxy, method, args));
        return enhancer.create();
    }
}
