package com.yaml.setting.loader;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class SettingsLoaderFactoryTest {

    @Test
    public void testCreateDefaultSettingsLoaderClass() {
        SettingsLoader settingsLoader = SettingsLoaderFactory.createSettingsLoader();
        Assert.assertNotNull(settingsLoader);
        Assert.assertTrue(settingsLoader instanceof YamlSettingsLoader);
    }

    @Test
    public void testConstructor() {
        Object obj = null;
        final Constructor<?>[] constructors = SettingsLoaderFactory.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        constructors[0].setAccessible(true);
        try {
            obj = constructors[0].newInstance((Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(obj);
    }
}
