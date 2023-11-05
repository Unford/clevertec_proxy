package ru.clevertec.course.proxy.factory.impl;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import ru.clevertec.course.proxy.factory.ProxyFactory;
import ru.clevertec.course.proxy.handler.ObjectInvocationHandler;

public class CgLibProxyFactory implements ProxyFactory {


    @Override
    public Object createProxy(ObjectInvocationHandler handler) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(handler.getObject().getClass());
        enhancer.setCallback((InvocationHandler) handler::invoke);
        return enhancer.create();
    }
}
