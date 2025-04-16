package edu.zjnu;


import java.util.Date;

/**
 * IRuleEvent
 *
 * @Date 2025-04-16 19:13
 * @Author 杨海波
 **/
public interface IRuleEvent {

    /**
     * 获取事件源
     *
     * @return
     */
    Rule getEventSource();

    /**
     * 事件触发事件
     *
     * @return
     */
    Date getTriggerTime();
}
