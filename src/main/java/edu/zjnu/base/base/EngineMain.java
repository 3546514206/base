package edu.zjnu.base.base;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.List;

public class EngineMain {

    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = manager.getEngineFactories();
        System.out.println(engineFactories.size());
        engineFactories.forEach(e -> {
            System.out.println(e.toString());
        });
    }
}
