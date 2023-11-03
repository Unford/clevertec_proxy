package ru.clevertec.course.proxy.factory;

import java.lang.reflect.Method;

public interface ProxyJoinPoint<T>  {
    T proxy();
    Object object();

    Method method();
    Object[] methodArgs();

    Object proceed() throws Throwable;
}
