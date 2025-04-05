package com.yaml.setting;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ResolveFailedConfigExceptionTest {

    @Test
    public void testInit() {
        ResolveFailedConfigException failedToResolveConfigException = new ResolveFailedConfigException();
        Assert.assertTrue(failedToResolveConfigException instanceof RuntimeException);
        failedToResolveConfigException = new ResolveFailedConfigException("Can't load config file success");
        Assert.assertEquals("Can't load config file success", failedToResolveConfigException.getMessage());
        IOException ioException = new IOException();
        failedToResolveConfigException = new ResolveFailedConfigException("Can't load config file success",ioException);
        Assert.assertEquals(ioException, failedToResolveConfigException.getCause());
        failedToResolveConfigException = new ResolveFailedConfigException(ioException);
        Assert.assertEquals(ioException, failedToResolveConfigException.getCause());
        failedToResolveConfigException = new ResolveFailedConfigException("Can't load config file success",ioException, true, true);
        Assert.assertEquals(ioException, failedToResolveConfigException.getCause());
    }
}
