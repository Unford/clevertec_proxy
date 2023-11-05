package ru.clevertec.course.autumn.factory;


import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import ru.clevertec.course.autumn.context.ApplicationContext;
import ru.clevertec.course.autumn.factory.configurator.BeanConfigurator;
import ru.clevertec.course.autumn.factory.configurator.ProxyConfigurator;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanFactory {
    private final ApplicationContext context;
    private final List<BeanConfigurator> configurators = new ArrayList<>();
    private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    @SneakyThrows
    public BeanFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends BeanConfigurator> aClass : context.getConfig()
                .getScanner()
                .getSubTypesOf(BeanConfigurator.class)) {
            if (aClass.isAnnotationPresent(Singleton.class)) {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            }
        }
        for (Class<? extends ProxyConfigurator> aClass : context.getConfig()
                .getScanner()
                .getSubTypesOf(ProxyConfigurator.class)) {
            if (aClass.isAnnotationPresent(Singleton.class)) {
                proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
            }

        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T t = create(implClass);

        configure(t);

        invokeInit(implClass, t);

        t = wrapWithProxyIfNeeded(implClass, t);

        return t;
    }

    private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T t) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = (T) proxyConfigurator.replaceWithProxyIfNeeded(t, implClass);
        }
        return t;
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> T create(Class<T> implClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
