package ru.clevertec.course.ioc.test.impl;

import jakarta.inject.Singleton;
import ru.clevertec.course.ioc.test.Recommendator;

@Singleton
public class RecommendatorImpl implements Recommendator {
    private String alcohol= "as";

    public RecommendatorImpl() {
        System.out.println("recommendator was created");
    }

    @Override
    public void recommend() {
        System.out.println("to protect from covid-2019, drink "+alcohol);
    }
}
