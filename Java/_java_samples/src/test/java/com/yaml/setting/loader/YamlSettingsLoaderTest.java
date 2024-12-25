package com.yaml.setting.loader;

import com.yaml.setting.util.StreamUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class YamlSettingsLoaderTest {

    @Test
    public void testConfigLoading() throws IOException {
        InputStream is = loadConfigURL("classpath*:config/test.yml").openStream();
        YamlSettingsLoader settingsLoader = new YamlSettingsLoader();
        Map<String, String> loadedSettings = settingsLoader.load(StreamUtil.copyToString(new InputStreamReader(is, "UTF-8")));
        Assert.assertNotNull(loadedSettings);
        Assert.assertEquals("testValue", ((HashMap) loadedSettings).get("module01.testStringKey"));
    }

    private URL loadConfigURL(String file) {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = patternResolver.getResources(file);
            return resources[0].getURL();
        } catch (IOException e) {
            //just ignored.
        }
        return null;
    }
}
