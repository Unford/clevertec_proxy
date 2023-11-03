package ru.clevertec.course.proxy.domain.model;

import ru.clevertec.course.proxy.factory.ProxyJoinPoint;

import java.lang.reflect.Method;

public abstract class DefaultProxyJoinPoint<T> implements ProxyJoinPoint<T> {
    protected final T proxy;
    protected final Object object;
    protected final Method method;
    protected final Object[] methodArgs;

    protected DefaultProxyJoinPoint(T proxy, Object object, Method method,
                          Object[] methodArgs) {
        this.proxy = proxy;
        this.object = object;
        this.method = method;
        this.methodArgs = methodArgs;
    }

    public T proxy() {
        return proxy;
    }

    public Object object() {
        return object;
    }

    public Method method() {
        return method;
    }

    public Object[] methodArgs() {
        return methodArgs;
    }



}
