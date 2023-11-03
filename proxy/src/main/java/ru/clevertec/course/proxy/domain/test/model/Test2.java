package ru.clevertec.course.proxy.domain.test.model;

import ru.clevertec.course.proxy.annotation.Loggable;
import ru.clevertec.course.proxy.domain.test.SecondInterface;

@Loggable
public class Test2 implements SecondInterface {
    @Override
    public String ivy() {
        return "ivy2";
    }

}
