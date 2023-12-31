package ru.clevertec.course.autumn.factory.configurator.impl;

import jakarta.inject.Singleton;
import ru.clevertec.course.autumn.factory.configurator.ProxyConfigurator;
import ru.clevertec.course.proxy.annotation.Loggable;
import ru.clevertec.course.proxy.factory.impl.CgLibProxyFactory;
import ru.clevertec.course.proxy.factory.impl.JdkProxyFactory;
import ru.clevertec.course.proxy.handler.impl.LoggerHandler;
import ru.clevertec.course.proxy.util.ProxyReflectionUtils;

import java.util.Arrays;

@Singleton
public class LoggableProxyConfigurator implements ProxyConfigurator {
    CgLibProxyFactory cgLibProxyFactory = new CgLibProxyFactory();
    JdkProxyFactory jdkProxyFactory = new JdkProxyFactory();

    @Override
    public Object replaceWithProxyIfNeeded(Object t, Class<?> implClass) {
        if (implClass.isAnnotationPresent(Loggable.class) ||
                Arrays.stream(implClass.getMethods()).anyMatch(method -> method.isAnnotationPresent(Loggable.class))) {
            LoggerHandler loggerHandler = new LoggerHandler();
            Object proxyInstance;
            if (ProxyReflectionUtils.hasAnyInterface(implClass)) {
                proxyInstance = jdkProxyFactory.createProxy(t, implClass, loggerHandler);
            } else {
                proxyInstance = cgLibProxyFactory.createProxy(t, implClass, loggerHandler);
            }
            return proxyInstance;
        }else {
            return t;
        }


    }
}
