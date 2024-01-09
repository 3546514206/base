package com.gqz.builder;

/**
 * 飞船
 * \
 *
 * @author ganquanzhong
 * @ClassName: AirShip
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月22日 下午1:42:08
 */
public class AirShip {

    private OrbitalModule orbitalModule;  //轨道舱
    private Engine engine; //发动机
    private EscapeTower escapeTower;  //逃逸塔

    public void launch() {
        System.out.println("发射！");
    }

    public OrbitalModule getOrbitalModule() {
        return orbitalModule;
    }

    public void setOrbitalModule(OrbitalModule orbitalModule) {
        this.orbitalModule = orbitalModule;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public EscapeTower getEscapeTower() {
        return escapeTower;
    }

    public void setEscapeTower(EscapeTower escapeTower) {
        this.escapeTower = escapeTower;
    }


}

//轨道舱
class OrbitalModule {
    private String name;

    public OrbitalModule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

//引擎
class Engine {
    private String name;

    public Engine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

//逃逸塔
class EscapeTower {
    private String name;

    public EscapeTower(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


