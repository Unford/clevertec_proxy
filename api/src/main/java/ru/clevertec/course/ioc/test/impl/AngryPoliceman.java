package ru.clevertec.course.ioc.test.impl;

import ru.clevertec.course.ioc.test.Policeman;

public class AngryPoliceman implements Policeman {
    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("Всех убью! Вон пошли");
    }
}
