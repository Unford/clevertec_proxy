package ru.clevertec.course.ioc.test.impl;

import jakarta.inject.Inject;
import ru.clevertec.course.ioc.test.Announcer;
import ru.clevertec.course.ioc.test.Recommendator;

public class ConsoleAnnouncer implements Announcer {
    @Inject
    private Recommendator recommendator;
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
