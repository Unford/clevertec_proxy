package ru.clevertec.course.proxy.handler;

public abstract class AbstractObjectInvocationHandler implements ObjectInvocationHandler {
    protected final Object object;

    protected AbstractObjectInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object getObject() {
        return object;
    }
}
