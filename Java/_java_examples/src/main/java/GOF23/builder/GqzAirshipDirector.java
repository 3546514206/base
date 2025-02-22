package com.gqz.builder;

//装配
public class GqzAirshipDirector implements AirShipDirector {

    private AirShipBuilder builder;

    //构造器
    public GqzAirshipDirector(AirShipBuilder builder) {
        this.builder = builder;
    }

    //装配成飞船对象
    @Override
    public AirShip directAirShip() {
        Engine e = builder.builderEngine();
        OrbitalModule o = builder.builderOrbitalModule();
        EscapeTower et = builder.builderEscapeTower();

        //装配成飞船对象
        AirShip ship = new AirShip();
        ship.setEngine(e);
        ship.setEscapeTower(et);
        ship.setOrbitalModule(o);

        return ship;
    }

}
