package ru.clevertec.course.proxy.util;

import org.reflections.ReflectionUtils;

import java.util.Set;

public final class ProxyReflectionUtils {
    private ProxyReflectionUtils() {
    }


    public static boolean hasAnyInterface(Class<?> c) {
        return !ProxyReflectionUtils.getAllSuperInterfaces(c).isEmpty();
    }

    @SuppressWarnings("unchecked")
    public static Set<Class<?>> getAllSuperInterfaces(Class<?> clazz) {
        return ReflectionUtils.getAllSuperTypes(clazz, Class::isInterface);
    }
}
