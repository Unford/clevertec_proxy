package ru.clevertec.course.ioc;

import ru.clevertec.course.autumn.application.AutumnApplication;
import ru.clevertec.course.autumn.context.ApplicationContext;
import ru.clevertec.course.ioc.test.*;

public class Main {





    public static void main(String[] args) throws Exception {
        ApplicationContext context = AutumnApplication.run(Main.class, args);
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        System.out.println("first created");
        CoronaDesinfector desinfector2 = context.getObject(CoronaDesinfector.class);
        System.out.println("second created");

        desinfector.start(new Room());
        desinfector2.start(new Room());
        ConstructorInjectBean desinfector3 = context.getObject(ConstructorInjectBean.class);
        desinfector3.start(new Room());

        Announcer s = desinfector3.getAnnouncer();
        Policeman policeman = desinfector3.getPoliceman();

    }
}
