package ru.clevertec.course.ioc.test;

import jakarta.inject.Inject;
import ru.clevertec.course.autumn.annotation.BeanQualifier;
import ru.clevertec.course.proxy.annotation.Loggable;

public class CoronaDesinfector {
    @Inject
    private Announcer announcer;
    @Inject
    @BeanQualifier("policeMan")
    private Policeman policeman;

    @Loggable
    public void start(Room room) {
        announcer.announce("Начинаем дезинфекцию, всё вон!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Рискните зайти обратно");
    }
    private void desinfect(Room room){
        System.out.println("зачитывается молитва: 'корона изыди!' - молитва прочитана, вирус низвергнут в ад");
    }
}
