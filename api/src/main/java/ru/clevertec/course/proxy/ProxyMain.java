package ru.clevertec.course.proxy;

import org.reflections.Reflections;
import ru.clevertec.course.proxy.annotation.Loggable;
import ru.clevertec.course.proxy.domain.FirstInterface;
import ru.clevertec.course.proxy.domain.SecondInterface;
import ru.clevertec.course.proxy.domain.model.ClassWithoutInterface;
import ru.clevertec.course.proxy.domain.model.Test2;
import ru.clevertec.course.proxy.factory.impl.CgLibProxyFactory;
import ru.clevertec.course.proxy.factory.impl.JdkProxyFactory;
import ru.clevertec.course.proxy.handler.impl.LoggerHandler;
import ru.clevertec.course.proxy.util.ProxyReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.stream.Collectors;

public class ProxyMain {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Set<Class<?>> typesAnnotatedWith = new Reflections(ProxyMain.class)
                .getTypesAnnotatedWith(Loggable.class)
                .stream()
                .filter(aClass -> !aClass.isInterface()
                        && !Modifier.isAbstract(aClass.getModifiers()))
                .collect(Collectors.toSet());

        JdkProxyFactory proxyFactory = new JdkProxyFactory();
        CgLibProxyFactory cgLibProxyFactory = new CgLibProxyFactory();
        for (Class<?> c : typesAnnotatedWith) {


            Object object = c.getDeclaredConstructor().newInstance();

            LoggerHandler loggerHandler = new LoggerHandler();
            Object proxyInstance;
            if (ProxyReflectionUtils.hasAnyInterface(c)) {
                proxyInstance = proxyFactory.createProxy(object, loggerHandler);
            } else {
                proxyInstance = cgLibProxyFactory.createProxy(object, loggerHandler);
            }


            if (proxyInstance instanceof ClassWithoutInterface) {
                ((ClassWithoutInterface) proxyInstance).exec("11111");
            }
            if (proxyInstance instanceof FirstInterface) {
                ((FirstInterface) proxyInstance).getInt();
                ((FirstInterface) proxyInstance).print("str", 12);
                ((FirstInterface) proxyInstance).sup();
            }
            if (proxyInstance instanceof SecondInterface) {
                ((SecondInterface) proxyInstance).ivy();

            }
        }

        JdkProxyFactory proxyFactory2 = new JdkProxyFactory();

        SecondInterface proxy = (SecondInterface) proxyFactory2.createProxy(new Test2(), new LoggerHandler());
        proxy.ivy();


    }


}
