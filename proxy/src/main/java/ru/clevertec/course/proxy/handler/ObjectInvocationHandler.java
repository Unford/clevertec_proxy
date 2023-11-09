package ru.clevertec.course.proxy.handler;

import java.lang.reflect.Method;

public interface ObjectInvocationHandler {
    Object invoke(Class<?> baseClass,
                  Object object,
                  Object proxy,
                  Method method,
                  Object[] args) throws Throwable;
}
