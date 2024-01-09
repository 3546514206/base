package com.gqz.builder;


public class GqzAirShipBuilder implements AirShipBuilder {
    //使用建造者模式
    //StringBuilder, 以后学习XML解析中，JDOM库中的类：DomBuilder,SaxBuilder
    @Override
    public Engine builderEngine() {
        System.out.println("构建发动机！");
        return new Engine("发动机！");
    }

    @Override
    public EscapeTower builderEscapeTower() {

        System.out.println("构建逃逸塔");
        return new EscapeTower("逃逸塔");
    }

    @Override
    public OrbitalModule builderOrbitalModule() {
        System.out.println("构建轨道舱");
        return new OrbitalModule("轨道舱");
    }

}
