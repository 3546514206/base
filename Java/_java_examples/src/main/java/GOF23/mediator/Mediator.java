package com.gqz.mediator;

public interface Mediator {

    void register(String dname, Department d);

    void command(String dname);

}
