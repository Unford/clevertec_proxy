package ru.clevertec.course.proxy.util;

import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
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

    public static Object getDefaultValueFor(Class<?> type) {
        if (type.isPrimitive()) {
            if (type == byte.class) return (byte) 0;
            if (type == short.class) return (short) 0;
            if (type == int.class) return 0;
            if (type == long.class) return 0L;
            if (type == float.class) return 0.0f;
            if (type == double.class) return 0.0;
            if (type == char.class) return '\u0000';
            if (type == boolean.class) return false;
        }
        return null;
    }

    public static Constructor<?> getMinParamaterConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        return Arrays.stream(constructors)
                .min(Comparator.comparingInt(Constructor::getParameterCount))
                .orElseThrow(() -> new IllegalArgumentException("Class has no constructors"));
    }

    public static Pair<Class<?>[], Object[]> extractParametersWithDefaultValue(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        Class<?>[] types = new Class<?>[parameters.length];
        Object[] values = new Object[parameters.length];


        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i].getType();
            types[i] = type;
            values[i] = getDefaultValueFor(type);
        }
        return Pair.of(types, values);
    }
}
