package edu.zjnu.base.base.java8interface.functioninterface;

/**
 * @description: todo
 * @author:
 * @date: 2022-01-28
 **/
public abstract class Case {

    private void useFunctionInterface(IFunction function) {
        System.out.println("test case");
    }

    public void loadCase() {
        useFunctionInterface(this::doSomeThing);
    }

    protected abstract String doSomeThing();
}
