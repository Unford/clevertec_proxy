package ru.clevertec.course.ioc;

import ru.clevertec.course.autumn.application.AutumnApplication;
import ru.clevertec.course.autumn.context.ApplicationContext;
import ru.clevertec.course.ioc.test.CoronaDesinfector;
import ru.clevertec.course.ioc.test.Room;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = AutumnApplication.run(Main.class, args);
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        System.out.println("first created");
        CoronaDesinfector desinfector2 = context.getObject(CoronaDesinfector.class);
        System.out.println("second created");

        desinfector.start(new Room());
        desinfector2.start(new Room());
    }
}
