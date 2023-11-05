package ru.clevertec.course.proxy.handler.impl;

import ru.clevertec.course.proxy.handler.AbstractObjectInvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class LoggerHandler extends AbstractObjectInvocationHandler {


    public LoggerHandler(Object object) {
        super(object);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StringBuilder builder = new StringBuilder("Method: \"")
                .append(method.getName())
                .append('\"')
                .append(", ");

        Parameter[] parameters = method.getParameters();
        StringJoiner stringJoiner = new StringJoiner(", ", "Args {", "}");

        for (int i = 0; i < parameters.length; i++) {
            stringJoiner.add(String.format("%s : %s", parameters[i].toString(), args[i]));
        }
        builder.append(stringJoiner);

        Object result = method.invoke(object, args);
        if (result != null) {
            builder.append(", Result: \"").append(result).append('\"');
        }
        System.out.println(builder);
        return result;
    }
}
