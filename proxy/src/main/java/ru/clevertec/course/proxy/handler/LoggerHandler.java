package ru.clevertec.course.proxy.handler;

import ru.clevertec.course.proxy.factory.JoinPointHandler;
import ru.clevertec.course.proxy.factory.ProxyJoinPoint;

import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class LoggerHandler<T> implements JoinPointHandler<T> {
    @Override
    public Object apply(ProxyJoinPoint<T> joinPoint) throws Throwable {
        StringBuilder builder = new StringBuilder("Method: \"")
                .append(joinPoint.method().getName())
                .append('\"')
                .append(", ");

        Parameter[] parameters = joinPoint.method().getParameters();
        StringJoiner stringJoiner = new StringJoiner(", ", "Args {", "}");

        for (int i = 0; i < parameters.length; i++) {
            stringJoiner.add(String.format("%s : %s", parameters[i].toString(), joinPoint.methodArgs()[i]));
        }
        builder.append(stringJoiner);

        Object result = joinPoint.proceed();
        if (result != null) {
            builder.append(", Result: \"").append(result).append('\"');
        }
        System.out.println(builder);
        return result;
    }
}
