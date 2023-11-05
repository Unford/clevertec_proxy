package ru.clevertec.course.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public interface ObjectInvocationHandler {
    Object invoke(Object object, Object proxy, Method method, Object[] args) throws Throwable;
}
