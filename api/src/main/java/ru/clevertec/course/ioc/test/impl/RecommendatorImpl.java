package ru.clevertec.course.ioc.test.impl;

import jakarta.inject.Singleton;
import ru.clevertec.course.ioc.test.Recommendator;
import ru.clevertec.course.proxy.annotation.Loggable;

@Singleton
@Loggable
public class RecommendatorImpl implements Recommendator {
    private String alcohol= "as";

    public RecommendatorImpl() {
    }

    @Override
    public void recommend() {
        System.out.println("to protect from covid-2019, drink "+alcohol);
    }
}
