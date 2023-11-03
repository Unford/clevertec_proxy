package ru.clevertec.course.proxy.factory;

public interface JoinPointHandler<T> extends ThrowingFunction<ProxyJoinPoint<T>, Object> {
}
