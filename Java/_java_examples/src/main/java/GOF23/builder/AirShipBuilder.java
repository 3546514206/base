package com.gqz.builder;

/**
 * 构建子组件
 *
 * @author ganquanzhong
 * @ClassName: AirShipBuilder
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月22日 下午1:44:12
 */
public interface AirShipBuilder {
    Engine builderEngine();

    OrbitalModule builderOrbitalModule();

    EscapeTower builderEscapeTower();
}
