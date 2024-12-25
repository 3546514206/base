package com.yaml.setting;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class SettingsExceptionTest {

    @Test
    public void initTest() {
        SettingsException settingsException = new SettingsException();
        Assert.assertNotNull(settingsException);
        IOException ioException = new IOException();
        settingsException = new SettingsException(ioException);
        Assert.assertEquals(ioException, settingsException.getCause());
        settingsException = new SettingsException("test");
        Assert.assertEquals("test", settingsException.getMessage());
        settingsException = new SettingsException("test", ioException, true, true);
        Assert.assertEquals("test", settingsException.getMessage());
    }
}
