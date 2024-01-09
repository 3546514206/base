package com.yaml.setting;

import org.junit.Assert;
import org.junit.Test;

public class SettingsFileConfigTest {

    @Test
    public void testDefaultConfigPath() {
        SettingsFileConfig settingsFileConfig = new SettingsFileConfig();
        Assert.assertEquals("classpath*:config/*.yml", settingsFileConfig.getConfigPath());
        settingsFileConfig.setConfigPath("/data/xhf/config/");
        Assert.assertEquals("/data/xhf/config/", settingsFileConfig.getConfigPath());
    }
}
