package edu.zjnu.base.base.agent;

/**
 * @description: -javaagent:/Users/SetsunaYang/Documents/learning/learning/agent/src/main/resources/agent-0.0.1-SNAPSHOT.jar
 * @author: 杨海波
 * @date: 2022-06-23 09:53
 **/
public class AgentLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("AgentLoader");
        Class.forName("edu.zjnu.base.base.agent.BeLoader");
    }
}
