package com.gqz.builder;

public class Client {
    public static void main(String[] args) {

        AirShipDirector director = new GqzAirshipDirector(new GqzAirShipBuilder());

        AirShip ship = director.directAirShip();

        System.out.println(ship.getEngine().getName());

        ship.launch();

    }
}
