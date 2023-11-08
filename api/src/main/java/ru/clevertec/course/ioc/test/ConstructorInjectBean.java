package ru.clevertec.course.ioc.test;

import jakarta.inject.Inject;
import ru.clevertec.course.autumn.annotation.BeanQualifier;
import ru.clevertec.course.proxy.annotation.Loggable;

@Loggable
public class ConstructorInjectBean {
    @Inject
    private Announcer announcer;

    private Policeman policeman;

    @Inject
    public ConstructorInjectBean(@BeanQualifier("angry") Policeman policeman) {
        this.policeman = policeman;
    }
    public ConstructorInjectBean(){}

    public void start(Room room) {
        announcer.announce("announcer call");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("contrusctor start finish");
    }

    private void desinfect(Room room){
        System.out.println("desinfect");
    }
}
