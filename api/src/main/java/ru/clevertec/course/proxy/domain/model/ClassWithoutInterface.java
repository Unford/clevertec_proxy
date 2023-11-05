package ru.clevertec.course.proxy.domain.model;

import ru.clevertec.course.proxy.annotation.Loggable;

@Loggable
public class ClassWithoutInterface {
    public String exec(String a){
        return a;
    }
}
