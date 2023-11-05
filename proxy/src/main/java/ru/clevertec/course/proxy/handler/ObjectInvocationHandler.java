package ru.clevertec.course.proxy.handler;

import java.lang.reflect.InvocationHandler;

public interface ObjectInvocationHandler extends InvocationHandler {
    Object getObject();
}
