package edu.zjnu;

/**
 * @description: AgentLoader
 * @author: 杨海波
 * @date: 2022-06-23 09:36
 **/
public class AgentLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("agent test");
        Class.forName("edu.zjnu.BeLoader");
    }
}
