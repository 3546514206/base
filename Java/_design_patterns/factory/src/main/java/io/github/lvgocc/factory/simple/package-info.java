/**
 * 简单工厂模式
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 22:38
 * @since 1.0.0
 */
package io.github.lvgocc.factory.simple;
/*
 * 简单工厂模式只做了解，该实现方式为常规编码创建对象，
 * 同时其创建过程使用的是静态方法，又称其为静态工厂。不在 GOF23 设计模式之列。
 * <p>
 * 当所要管理的对象个数少，创建对象过程复杂时，可以考虑使用这种方式来管理对象
 * <p>
 * 如果管理的对象创建过程不复杂，可以考虑直接 new 的方式创建。
 */
