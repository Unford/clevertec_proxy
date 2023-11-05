package ru.clevertec.course.proxy.domain.model;

import ru.clevertec.course.proxy.annotation.Loggable;
import ru.clevertec.course.proxy.domain.SecondInterface;
import ru.clevertec.course.proxy.domain.FirstInterface;

@Loggable
public class Test1 implements FirstInterface, SecondInterface {

    @Override
    public int getInt() {
        return 110;
    }

    @Override
    public void print(String a, int v) {
        System.out.println(a + v);
    }

    @Override
    public double sup() {
        return 0;
    }

    public int notInterface(){
        return 1;
    }

    @Override
    public String ivy() {
        return "ive";
    }
}
