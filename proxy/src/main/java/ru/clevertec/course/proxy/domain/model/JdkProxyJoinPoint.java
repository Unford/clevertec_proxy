package ru.clevertec.course.proxy.domain.model;

import java.lang.reflect.Method;

public class JdkProxyJoinPoint extends DefaultProxyJoinPoint<Object> {
    public JdkProxyJoinPoint(Object proxy, Object object, Method method, Object[] methodArgs) {
        super(proxy, object, method, methodArgs);
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(object, methodArgs);
    }
}
