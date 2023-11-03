package ru.clevertec.course.proxy.domain.model;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CgLibProxyJoinPoint extends DefaultProxyJoinPoint<MethodProxy> {
    public CgLibProxyJoinPoint(MethodProxy proxy, Object object, Method method, Object[] methodArgs) {
        super(proxy, object, method, methodArgs);
    }

    @Override
    public Object proceed() throws Throwable {
        return proxy.invokeSuper(object(), methodArgs());
    }
}
