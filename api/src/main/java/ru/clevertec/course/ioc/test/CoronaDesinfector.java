package ru.clevertec.course.ioc.test;

import jakarta.inject.Inject;
import ru.clevertec.course.autumn.annotation.BeanQualifier;

public class CoronaDesinfector {
    @Inject
    private Announcer announcer;
    @Inject
    @BeanQualifier("policeMan")
    private Policeman policeman;


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
