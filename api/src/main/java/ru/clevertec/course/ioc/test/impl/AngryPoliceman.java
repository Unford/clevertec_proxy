package ru.clevertec.course.ioc.test.impl;

import ru.clevertec.course.autumn.annotation.BeanQualifier;
import ru.clevertec.course.ioc.test.Policeman;
import ru.clevertec.course.proxy.annotation.Loggable;

@BeanQualifier("angry")

public class AngryPoliceman implements Policeman {
    @Override
    @Loggable
    public void makePeopleLeaveRoom() {
        System.out.println("Всех убью! Вон пошли");
    }
}
