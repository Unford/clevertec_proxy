package ru.clevertec.course.proxy;

import org.reflections.Reflections;
import ru.clevertec.course.proxy.annotation.Loggable;
import ru.clevertec.course.proxy.domain.test.FirstInterface;
import ru.clevertec.course.proxy.domain.test.SecondInterface;
import ru.clevertec.course.proxy.domain.test.model.ClassWithoutInterface;
import ru.clevertec.course.proxy.factory.impl.CgLibProxyFactory;
import ru.clevertec.course.proxy.factory.impl.JdkProxyFactory;
import ru.clevertec.course.proxy.handler.LoggerHandler;
import ru.clevertec.course.proxy.util.ProxyReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Set<Class<?>> typesAnnotatedWith = new Reflections(Main.class)
                .getTypesAnnotatedWith(Loggable.class)
                .stream()
                .filter(aClass -> !aClass.isInterface()
                        && !Modifier.isAbstract(aClass.getModifiers()))
                .collect(Collectors.toSet());

        JdkProxyFactory proxyFactory = new JdkProxyFactory();
        CgLibProxyFactory cgLibProxyFactory = new CgLibProxyFactory();
        for (Class<?> c : typesAnnotatedWith) {


            Object object = c.getConstructor(new Class[0]).newInstance();
            Object proxyInstance;
            if (ProxyReflectionUtils.hasAnyInterface(c)) {
                proxyInstance = proxyFactory.createProxy(object, new LoggerHandler<>());
            } else {
                proxyInstance = cgLibProxyFactory.createProxy(object, new LoggerHandler<>());
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


    }


}
