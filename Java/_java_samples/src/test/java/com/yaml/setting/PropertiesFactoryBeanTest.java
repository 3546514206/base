package com.yaml.setting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/com/yaml/setting/spring.xml"})
public class PropertiesFactoryBeanTest {

    @Autowired
    private Settings settings;

    @Value("${module01.testStringKey}")
    private String StringValue;

    @Value("${module02.testIntKey}")
    private int intValue;

    @Test
    public void testGetObject() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setSettings(settings);
        Properties properties = propertiesFactoryBean.getObject();
        Assert.assertNotNull(properties);
        Assert.assertEquals("testValue",properties.get("module01.testStringKey"));
        Assert.assertTrue(propertiesFactoryBean.isSingleton());
        Class<Properties> clazz = propertiesFactoryBean.getObjectType();
        Assert.assertNotNull(clazz);

        Assert.assertEquals("testValue",StringValue);

        Assert.assertTrue( 9 == intValue);
    }
}
