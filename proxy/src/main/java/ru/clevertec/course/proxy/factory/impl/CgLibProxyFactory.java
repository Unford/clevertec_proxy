package ru.clevertec.course.proxy.factory.impl;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import ru.clevertec.course.proxy.domain.model.CgLibProxyJoinPoint;
import ru.clevertec.course.proxy.factory.JoinPointHandler;
import ru.clevertec.course.proxy.factory.ProxyFactory;

public class CgLibProxyFactory implements ProxyFactory<MethodProxy> {



    @Override
    public Object createProxy(Object object, JoinPointHandler<MethodProxy> handler) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback((MethodInterceptor)(obj, method, args, proxy) ->
                handler.apply(new CgLibProxyJoinPoint(proxy, obj, method, args)));
        return enhancer.create();
    }
}
