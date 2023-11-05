package ru.clevertec.course.proxy.domain.model;

import ru.clevertec.course.proxy.annotation.Loggable;
import ru.clevertec.course.proxy.domain.SecondInterface;

@Loggable
public class Test2 implements SecondInterface {
    @Override
    public String ivy() {
        return "ivy2";
    }

}
