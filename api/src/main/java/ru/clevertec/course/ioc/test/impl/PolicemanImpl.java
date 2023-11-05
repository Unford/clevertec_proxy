package ru.clevertec.course.ioc.test.impl;

import jakarta.inject.Inject;
import ru.clevertec.course.autumn.annotation.BeanQualifier;
import ru.clevertec.course.ioc.test.Policeman;
import ru.clevertec.course.ioc.test.Recommendator;
import ru.clevertec.course.proxy.annotation.Loggable;

import javax.annotation.PostConstruct;

@BeanQualifier("policeMan")
@Loggable
public class PolicemanImpl implements Policeman {

    @Inject
    private Recommendator recommendator;

    @PostConstruct
    public void init() {
        System.out.println("Post Construct - " + recommendator);
    }

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("пиф паф, бах бах, кыш, кыш!");
    }
}